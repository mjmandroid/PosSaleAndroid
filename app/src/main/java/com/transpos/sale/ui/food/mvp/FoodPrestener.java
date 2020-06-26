package com.transpos.sale.ui.food.mvp;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.trans.network.callback.JsonObjectCallback;
import com.trans.network.callback.StringCallback;
import com.trans.network.model.Response;
import com.trans.network.utils.GsonHelper;
import com.transpos.sale.base.mvp.BasePresenter;
import com.transpos.sale.entity.BaseListResponse;
import com.transpos.sale.entity.BaseResponse;
import com.transpos.sale.entity.DownloadCacheName;
import com.transpos.sale.entity.DownloadNotify;
import com.transpos.sale.entity.Tuple2;
import com.transpos.sale.thread.ThreadDispatcher;
import com.transpos.sale.utils.DateUtil;
import com.transpos.sale.utils.FoodConstant;
import com.transpos.sale.utils.Global;
import com.transpos.sale.utils.KeyConstrant;
import com.transpos.sale.utils.LogUtil;
import com.transpos.tools.tputils.TPUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FoodPrestener extends BasePresenter<FoodContract.Model, FoodContract.View> implements FoodContract.Prestenter {
    @Override
    protected FoodContract.Model createModule() {
        return new FoodModel();
    }

    private static final String TAG = FoodPrestener.class.getSimpleName();
    private Object mLock = new Object();
    private volatile int threadCount = 0;

    @Override
    public void startDownload() {
        getView().showLoading();
        getModule().obtainServerDataVersion(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                BaseListResponse<Map<String, String>> resp = GsonHelper.fromJson(response.body(), new TypeToken<BaseListResponse<Map<String, String>>>() {
                }.getType());
                if(resp.getCode() == BaseResponse.SUCCESS){
                    if (resp.getData() == null || resp.getData().size() == 0) {
                        LogUtil.e(LogUtil.TAG, String.format("未发现服务端数据版本信息"));
                        return;
                    }
                    compareVersion(resp.getData());
                } else {
                    LogUtil.e(LogUtil.TAG, String.format("发掘新数据失败:%s", response.message()));
                }
            }

            @Override
            public void onError(Response<String> response) {
                getView().dismissLoading();
                LogUtil.e(LogUtil.TAG, String.format("发掘新数据error失败:%s", response.message()));
            }


        });
    }

    private void compareVersion(final List<Map<String, String>> datas) {
        Log.e(TAG, "run: compareVersion");
        ThreadDispatcher.getDispatcher().post(new Runnable() {
            @Override
            public void run() {
                List<Map<String,String>> localList = TPUtils.getObject(getContext(),KeyConstrant.KEY_DATA_VERSION,List.class);
                List<String> needDownload = Arrays.asList(Global.downloadDataType);
                final List<Tuple2<String, String>> vLists = new ArrayList<>();
                for (Map<String, String> map : datas) {
                    String stype = map.get("dataType");
                    String sversion = map.get("dataVersion");
                    boolean isNeedUpdate = false;
                    if (needDownload.contains(stype)) {
                        if(localList != null){
                            for (Map<String, String> localMap : localList) {
                                if(stype.equals(localMap.get("dataType"))){
                                    if(!sversion.equals(localMap.get("dataVersion"))){
                                        isNeedUpdate = true;
                                    }
                                    break;
                                }
                            }
                        } else {
                            isNeedUpdate = true;
                        }
                    }
                    if (isNeedUpdate)
                        vLists.add(new Tuple2<String, String>(stype, sversion));
                }
                if (vLists.size() == 0) {
                    completeDownload();
                    Log.e(TAG, "run: "+"本地为最新数据");
                    return;
                }
                Log.e(TAG, "run: "+DateUtil.getNowMillSecondDateStr());
                threadCount = 0;
                for (Tuple2<String, String> list : vLists) {
                    String dataType = list.first;
                    final DownloadCacheName downloadCacheName = DownloadCacheName.valueOf(dataType);
                    ThreadDispatcher.getDispatcher().post(new Runnable() {
                        @Override
                        public void run() {
                            realDownload(downloadCacheName,vLists.size());
                        }
                    });

                }
                TPUtils.putObject(getContext(), KeyConstrant.KEY_DATA_VERSION,datas);
            }
        });

    }

    /**
     * 下载完成
     */
    private void completeDownload() {
        Log.e(TAG, "run: "+DateUtil.getNowMillSecondDateStr());
        ThreadDispatcher.getDispatcher().postOnMain(new Runnable() {
            @Override
            public void run() {
                getView().dismissLoading();
            }
        });
    }

    private void realDownload(DownloadCacheName type, int size) {
        switch (type) {
            case PRODUCT_BRAND: {
                downloadProductBrand(1,FoodConstant.DEFAULT_PAGESIZE);
            }
            break;
            case PRODUCT_CATEGORY: {
                downloadProductCategory(1,FoodConstant.DEFAULT_PAGESIZE);
            }
            break;
            case PRODUCT_UNIT: {
                downloadProductUnit(1,FoodConstant.DEFAULT_PAGESIZE);
            }
            break;
            case PRODUCT: {
                downloadProduct(1,FoodConstant.DEFAULT_PAGESIZE);
                downloadProductCode(1,FoodConstant.DEFAULT_PAGESIZE);
                downloadProductContact(1,FoodConstant.DEFAULT_PAGESIZE);
                downloadProductSpec(1,FoodConstant.DEFAULT_PAGESIZE);
                downloadStoreProduct(1,FoodConstant.DEFAULT_PAGESIZE);
            }
            break;
            case WORKER: {
                downloadWorker(1,FoodConstant.DEFAULT_PAGESIZE);
            }
            break;
            case SUPPLIER: {
                downloadSupplier(1,FoodConstant.DEFAULT_PAGESIZE);
            }
            break;
            case PAYMODE: {
                downloadPayMode(1,FoodConstant.DEFAULT_PAGESIZE);
            }
            break;
            case STORE_INFO: {
                downloadStoreInfo(1,FoodConstant.DEFAULT_PAGESIZE);
            }
            break;
            case MEMBER_SETTING: {
                //会员设置
                downloadMemberSetting();
            }
            break;
            case MEMBER_LEVEL: {
                //会员等级
                downloadMemberLevel();
                downloadMemberLevelCategoryDiscount();
            }
            break;
            case MEMBER_POINT_RULE: {
                downloadMemberPointRule();
                downloadMemberPointRuleCategory();
                downloadMemberPointRuleBrand();
            }
            break;
            case PAY_SETTING: {
                downloadPaymentParameter();
            }
            break;
            case LINE_SALES_SETTING: {
                downloadSalesSetting();
            }
            break;
            default: {

            }
            break;
        }
        synchronized (mLock){
            threadCount ++;
            if(threadCount >= size){
                completeDownload();
            }
        }
    }

    private void downloadSalesSetting() {
        DownloadNotify notify = getModule().fetchSalesSetting();
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadPaymentParameter() {
        DownloadNotify notify = getModule().fetchPaymentParameter();
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadMemberPointRuleBrand() {
        DownloadNotify notify = getModule().fetchMemberPointRuleBrand();
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadMemberPointRuleCategory() {
        DownloadNotify notify = getModule().fetchMemberPointRuleCategory();
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadMemberPointRule() {
        DownloadNotify notify = getModule().fetchMemberPointRule();
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadMemberLevelCategoryDiscount() {
        DownloadNotify notify = getModule().fetchMemberLevelCategoryDiscount();
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadMemberLevel() {
        DownloadNotify notify = getModule().fetchMemberLevel();
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadMemberSetting() {
        DownloadNotify notify = getModule().fetchMemberSetting();
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadStoreInfo(int pageIndex, int defaultPagesize) {
        DownloadNotify notify = getModule().fetchStoreInfo(pageIndex, defaultPagesize);
        if(notify.isSuccess()){
            if(notify.isPager()){
                int pageNum = notify.getPageNumber();
                int pageSize = notify.getPageSize();
                int pageCount = notify.getPageCount();
                for (int page = pageNum + 1; page < pageCount + 1; page++){
                    getModule().fetchStoreInfo(page,pageSize);
                }
            }
        }
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadPayMode(int pageIndex, int defaultPagesize) {
        DownloadNotify notify = getModule().fetchPayMode(pageIndex, defaultPagesize);
        if(notify.isSuccess()){
            if(notify.isPager()){
                int pageNum = notify.getPageNumber();
                int pageSize = notify.getPageSize();
                int pageCount = notify.getPageCount();
                for (int page = pageNum + 1; page < pageCount + 1; page++){
                    getModule().fetchPayMode(page,pageSize);
                }
            }
        }
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadSupplier(int pageIndex, int defaultPagesize) {
        DownloadNotify notify = getModule().fetchSupplier(pageIndex, defaultPagesize);
        if(notify.isSuccess()){
            if(notify.isPager()){
                int pageNum = notify.getPageNumber();
                int pageSize = notify.getPageSize();
                int pageCount = notify.getPageCount();
                for (int page = pageNum + 1; page < pageCount + 1; page++){
                    getModule().fetchSupplier(page,pageSize);
                }
            }
        }
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadWorker(int pageIndex, int defaultPagesize) {
        DownloadNotify notify = getModule().fetchWorker(pageIndex, defaultPagesize);
        if(notify.isSuccess()){
            if(notify.isPager()){
                int pageNum = notify.getPageNumber();
                int pageSize = notify.getPageSize();
                int pageCount = notify.getPageCount();
                for (int page = pageNum + 1; page < pageCount + 1; page++){
                    getModule().fetchWorker(page,pageSize);
                }
            }
        }
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadStoreProduct(int pageIndex, int defaultPagesize) {
        DownloadNotify notify = getModule().fetchStoreProduct(pageIndex, defaultPagesize);
        if(notify.isSuccess()){
            if(notify.isPager()){
                int pageNum = notify.getPageNumber();
                int pageSize = notify.getPageSize();
                int pageCount = notify.getPageCount();
                for (int page = pageNum + 1; page < pageCount + 1; page++){
                    getModule().fetchStoreProduct(page,pageSize);
                }
            }
        }
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadProductSpec(int pageIndex, int defaultPagesize) {
        DownloadNotify notify = getModule().fetchProductSpec(pageIndex, defaultPagesize);
        if(notify.isSuccess()){
            if(notify.isPager()){
                int pageNum = notify.getPageNumber();
                int pageSize = notify.getPageSize();
                int pageCount = notify.getPageCount();
                for (int page = pageNum + 1; page < pageCount + 1; page++){
                    getModule().fetchProductSpec(page,pageSize);
                }
            }
        }
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadProductContact(int pageIndex, int defaultPagesize) {
        DownloadNotify notify = getModule().fetchProductContact(pageIndex, defaultPagesize);
        if(notify.isSuccess()){
            if(notify.isPager()){
                int pageNum = notify.getPageNumber();
                int pageSize = notify.getPageSize();
                int pageCount = notify.getPageCount();
                for (int page = pageNum + 1; page < pageCount + 1; page++){
                    getModule().fetchProductContact(page,pageSize);
                }
            }
        }
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadProductCode(int pageIndex, int defaultPagesize) {
        DownloadNotify notify = getModule().fetchProductCode(pageIndex, defaultPagesize);
        if(notify.isSuccess()){
            if(notify.isPager()){
                int pageNum = notify.getPageNumber();
                int pageSize = notify.getPageSize();
                int pageCount = notify.getPageCount();
                for (int page = pageNum + 1; page < pageCount + 1; page++){
                    getModule().fetchProductCode(page,pageSize);
                }
            }
        }
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadProduct(int pageIndex, int defaultPagesize) {
        DownloadNotify notify = getModule().fetchProduct(pageIndex, defaultPagesize);
        if(notify.isSuccess()){
            if(notify.isPager()){
                int pageNum = notify.getPageNumber();
                int pageSize = notify.getPageSize();
                int pageCount = notify.getPageCount();
                for (int page = pageNum + 1; page < pageCount + 1; page++){
                    getModule().fetchProduct(page,pageSize);
                }
            }
        }

    }

    private void downloadProductUnit(int pageIndex, int defaultPagesize) {
        DownloadNotify notify = getModule().fetchProductUnit(pageIndex, defaultPagesize);
        if(notify.isSuccess()){
            if(notify.isPager()){
                int pageNum = notify.getPageNumber();
                int pageSize = notify.getPageSize();
                int pageCount = notify.getPageCount();
                for (int page = pageNum + 1; page < pageCount + 1; page++){
                    getModule().fetchProductUnit(page,pageSize);
                }
            }
        }
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadProductCategory(int pageIndex, int defaultPagesize) {
        DownloadNotify notify = getModule().fetchProductCategroy(pageIndex, defaultPagesize);
        if(notify.isSuccess()){
            if(notify.isPager()){
                int pageNum = notify.getPageNumber();
                int pageSize = notify.getPageSize();
                int pageCount = notify.getPageCount();
                for (int page = pageNum + 1; page < pageCount + 1; page++){
                    getModule().fetchProductCategroy(page,pageSize);
                }
            }
        }
        LogUtil.d(TAG,notify.getMessage());
    }

    private void downloadProductBrand(int pageIndex,int size) {
        DownloadNotify notify = getModule().fetchProductBrand(pageIndex, size);
        if(notify.isSuccess()){
            if(notify.isPager()){
                int pageNum = notify.getPageNumber();
                int pageSize = notify.getPageSize();
                int pageCount = notify.getPageCount();
                for (int page = pageNum + 1; page < pageCount + 1; page++){
                    getModule().fetchProductBrand(page,pageSize);
                }
            }
        }
        LogUtil.d(TAG,notify.getMessage());
    }
}
