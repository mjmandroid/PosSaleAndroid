package com.transpos.sale.ui.food.mvp;

import com.trans.network.callback.BaseCallback;
import com.trans.network.callback.StringCallback;
import com.transpos.sale.base.mvp.IBaseModel;
import com.transpos.sale.base.mvp.IBaseView;
import com.transpos.sale.entity.DownloadNotify;

public interface FoodContract {

    interface Model extends IBaseModel{
        void obtainServerDataVersion(StringCallback callback);
        DownloadNotify fetchProductBrand(int pageNum, int pageSize);
        DownloadNotify fetchProductCategroy(int pageNum, int pageSize);
        DownloadNotify fetchProductUnit(int pageNum, int pageSize);
        DownloadNotify fetchProduct(int pageNum, int pageSize);
        DownloadNotify fetchProductCode(int pageNum, int pageSize);
        DownloadNotify fetchProductContact(int pageNum, int pageSize);
        DownloadNotify fetchProductSpec(int pageNum, int pageSize);
        DownloadNotify fetchStoreProduct(int pageNum, int pageSize);
        DownloadNotify fetchWorker(int pageNum, int pageSize);
        DownloadNotify fetchSupplier(int pageNum, int pageSize);
        DownloadNotify fetchPayMode(int pageNum, int pageSize);
        DownloadNotify fetchStoreInfo(int pageNum, int pageSize);
        DownloadNotify fetchMemberSetting();
        DownloadNotify fetchMemberLevel();
        DownloadNotify fetchMemberLevelCategoryDiscount();
        DownloadNotify fetchMemberPointRule();
        DownloadNotify fetchMemberPointRuleCategory();
        DownloadNotify fetchMemberPointRuleBrand();
        DownloadNotify fetchPaymentParameter();
        DownloadNotify fetchSalesSetting();
    }

    interface View extends IBaseView{

    }

    interface Prestenter{
        void startDownload();
    }
}
