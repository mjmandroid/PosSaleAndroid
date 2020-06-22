package com.transpos.sale.db.manger;

import com.transpos.sale.base.BaseDBManager;
import com.transpos.sale.base.BaseDao;
import com.transpos.sale.db.AppDatabase;
import com.transpos.sale.db.dao.WorkerDao;
import com.transpos.sale.entity.Worker;

import java.util.List;

public class WorkerDbManger extends BaseDBManager<Worker> {

    private WorkerDbManger(){}

    @Override
    protected BaseDao<Worker> getDataBaseDao() {
        return AppDatabase.getDatabase().getWorkerDao();
    }

    public static WorkerDbManger getInstance(){
        return SingleHolder.INSTANCE;
    }

    @Override
    public int deleteAll() {
        WorkerDao dao = (WorkerDao) baseDao;
        return dao.deleteAll();
    }

    @Override
    public List<Worker> loadAll() {
        WorkerDao dao = (WorkerDao) baseDao;
        return dao.loadAll();
    }

    private static class SingleHolder{
        static WorkerDbManger INSTANCE = new WorkerDbManger();
    }
}
