package com.rushro2m.greendao_master;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.rushro2m.greendao_master.bean.DaoMaster;
import com.rushro2m.greendao_master.bean.DaoSession;

/**
 * 作者：Rushro2m on 2017/8/15.
 * 邮箱：haoxujie1993@gmail.com
 * 版本：v1.0
 */


public class MyApp extends Application {
    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        //实例化一个OpenHelper实例
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "movie-db");

        //获取一个SQLiteDatabase
        SQLiteDatabase database = helper.getWritableDatabase();

        //使用数据库对象构造一个DaoMaster
        DaoMaster daoMaster = new DaoMaster(database);

        //开启DoaSession
        daoSession = daoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
}


