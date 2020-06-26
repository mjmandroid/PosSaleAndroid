package com.transpos.sale.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.os.Environment;

import com.transpos.sale.base.BaseApp;
import com.transpos.sale.db.dao.LineSalesSettingDao;
import com.transpos.sale.db.dao.LineSystemSetDao;
import com.transpos.sale.db.dao.MemberLevelCategoryDiscountDao;
import com.transpos.sale.db.dao.MemberLevelDao;
import com.transpos.sale.db.dao.MemberPointRuleBrandDao;
import com.transpos.sale.db.dao.MemberPointRuleCategoryDao;
import com.transpos.sale.db.dao.MemberPointRuleDao;
import com.transpos.sale.db.dao.PayModeDao;
import com.transpos.sale.db.dao.PaymentParameterDao;
import com.transpos.sale.db.dao.ProductBrandDao;
import com.transpos.sale.db.dao.ProductCategroyDao;
import com.transpos.sale.db.dao.ProductCodeDao;
import com.transpos.sale.db.dao.ProductContactDao;
import com.transpos.sale.db.dao.ProductDao;
import com.transpos.sale.db.dao.ProductSpecDao;
import com.transpos.sale.db.dao.ProductUnitDao;
import com.transpos.sale.db.dao.StoreInfoDao;
import com.transpos.sale.db.dao.StoreProductDao;
import com.transpos.sale.db.dao.SupplierDao;
import com.transpos.sale.db.dao.WorkerDao;
import com.transpos.sale.entity.LineSalesSetting;
import com.transpos.sale.entity.LineSystemSet;
import com.transpos.sale.entity.MemberLevel;
import com.transpos.sale.entity.MemberLevelCategoryDiscount;
import com.transpos.sale.entity.MemberPointRule;
import com.transpos.sale.entity.MemberPointRuleBrand;
import com.transpos.sale.entity.MemberPointRuleCategory;
import com.transpos.sale.entity.PayMode;
import com.transpos.sale.entity.PaymentParameter;
import com.transpos.sale.entity.Product;
import com.transpos.sale.entity.ProductBrand;
import com.transpos.sale.entity.ProductCategory;
import com.transpos.sale.entity.ProductCode;
import com.transpos.sale.entity.ProductContact;
import com.transpos.sale.entity.ProductSpec;
import com.transpos.sale.entity.ProductUnit;
import com.transpos.sale.entity.StoreInfo;
import com.transpos.sale.entity.StoreProduct;
import com.transpos.sale.entity.Supplier;
import com.transpos.sale.entity.Worker;

import java.io.File;

//entities表示要包含哪些表；version为数据库的版本，数据库升级时更改；exportSchema是否导出数据库结构，默认为true
@Database(entities = {Student.class, ProductBrand.class, ProductCategory.class, ProductUnit.class,
        Product.class, ProductCode.class, ProductContact.class, ProductSpec.class, StoreProduct.class,
        Worker.class, Supplier.class, PayMode.class, StoreInfo.class, LineSystemSet.class,
        MemberLevel.class, MemberLevelCategoryDiscount.class, MemberPointRule.class,
        MemberPointRuleCategory.class, MemberPointRuleBrand.class, PaymentParameter.class,
        LineSalesSetting.class},
        version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


//    外部存储 应用卸载数据也丢弃
    private static final String db_path = BaseApp.getApplication().getExternalFilesDir(null).getPath()+ File.separator+"db_store/pos.db";

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
    public abstract StudentDao getStudentDao();

    public abstract ProductBrandDao getProductBrandDao();

    public abstract ProductCategroyDao getProductCategroyDao();

    public abstract ProductUnitDao getProductUnitDao();

    public abstract ProductDao getProductDao();

    public abstract ProductCodeDao getProductCodeDao();

    public abstract ProductContactDao getProcuctContractDao();

    public abstract ProductSpecDao getProductSpecDao();

    public abstract StoreProductDao getStoreProductDao();

    public abstract WorkerDao getWorkerDao();

    public abstract SupplierDao getSupplierDao();

    public abstract PayModeDao getPayModeDao();

    public abstract StoreInfoDao getStoreInfoDao();

    public abstract LineSystemSetDao getLineSystemSetDao();

    public abstract MemberLevelDao getMemberLevelDao();

    public abstract MemberLevelCategoryDiscountDao getMemberLevelCategoryDiscountDao();

    public abstract MemberPointRuleDao getMemberPointRuleDao();

    public abstract MemberPointRuleCategoryDao getMemberPointRuleCategoryDao();

    public abstract MemberPointRuleBrandDao getMemberPointRuleBrandDao();

    public abstract PaymentParameterDao getPaymentParamterDao();

    public abstract LineSalesSettingDao getLineSalesSettingDao();

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

