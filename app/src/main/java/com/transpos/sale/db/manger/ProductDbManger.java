package com.transpos.sale.db.manger;

import com.transpos.sale.base.BaseDBManager;
import com.transpos.sale.base.BaseDao;
import com.transpos.sale.db.AppDatabase;
import com.transpos.sale.db.dao.ProductDao;
import com.transpos.sale.entity.Product;

import java.util.List;

public class ProductDbManger extends BaseDBManager<Product> {
    private ProductDbManger(){

    }

    @Override
    protected BaseDao<Product> getDataBaseDao() {
        return AppDatabase.getDatabase().getProductDao();
    }
    public static ProductDbManger getInstance(){
        return SingleHolder.INSTANCE;
    }

    private static class SingleHolder{
        static ProductDbManger INSTANCE = new ProductDbManger();
    }

    @Override
    public int deleteAll() {
        ProductDao dao = (ProductDao) baseDao;
        return dao.deleteAll();
    }

    @Override
    public List<Product> loadAll() {
        ProductDao dao = (ProductDao) baseDao;
        return dao.loadAll();
    }
}
