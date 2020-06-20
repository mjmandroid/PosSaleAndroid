package com.transpos.sale.db;

public class DbManager {

    private DbManager(){}

    private static class SingletonHolder{
        static DbManager INSTANCE = new DbManager();
    }

    public static DbManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public long insert(Student student){
        Long amount = AppDatabase.getDatabase().getStudentDao().insert(student);
        return amount;
    }
}
