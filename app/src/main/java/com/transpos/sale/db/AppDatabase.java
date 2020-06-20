package com.transpos.sale.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.os.Environment;

import com.transpos.sale.base.BaseApp;

//entities表示要包含哪些表；version为数据库的版本，数据库升级时更改；exportSchema是否导出数据库结构，默认为true
@Database(entities = {Student.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract StudentDao getStudentDao();
    private static final String db_path= Environment.getExternalStorageDirectory().getPath()+"/"+"dbtest/my.db";

    //单例
    public static AppDatabase getDatabase(){
        return Holder.instance;
    }

    private static class Holder{
        private static final AppDatabase instance= Room.databaseBuilder(BaseApp.getApplication(), AppDatabase.class, db_path)
                 .allowMainThreadQueries()   //设置允许在主线程进行数据库操作，默认不允许，建议都设置为默认
                // .fallbackToDestructiveMigration()  //设置数据库升级的时候清除之前的所有数据
                .addMigrations(MIGRATION_1_2,MIGRATION_2_3)
                .build();
    }

    /**
     * 数据库升级  version1 -> version2
     */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //向student表中添加一个sport字段
            database.execSQL("ALTER TABLE student ADD COLUMN sport INTEGER"); //这种方式不支持一次添加多个字段，可以多写几条sql语句分别执行
        }
    };

    /**
     * 数据库升级  version2 -> version3
     */
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //向student表中添加一个physical字段
            database.execSQL("ALTER TABLE student ADD COLUMN physical INTEGER");
        }
    };
}

