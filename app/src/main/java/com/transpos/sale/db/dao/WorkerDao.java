package com.transpos.sale.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.transpos.sale.base.BaseDao;
import com.transpos.sale.entity.Worker;

import java.util.List;

@Dao
public interface WorkerDao extends BaseDao<Worker> {

    @Query("DELETE FROM pos_worker")
    int deleteAll();

    @Query("SELECT * FROM pos_worker")
    List<Worker> loadAll();
}
