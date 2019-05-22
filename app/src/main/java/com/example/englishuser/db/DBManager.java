package com.example.englishuser.db;
/*
 * 文件名：DBManager
 * 作者：created by admin on 2019 五月
 * 描述：
 *
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.example.englishuser.AitemDao;
import com.example.englishuser.Bean.Aitem;
import com.example.englishuser.DaoMaster;
import com.example.englishuser.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class DBManager {
    private final static String dbName = "EGAitem_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入一条记录
     *
     * @param aitem
     */
    public void insertAitem(Aitem aitem) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        AitemDao AitemDao = daoSession.getAitemDao();
        AitemDao.insert(aitem);
    }

    /**
     * 插入用户集合
     *
     * @param Aitems
     */
    public void insertAitemList(List<Aitem> Aitems) {
        if (Aitems == null || Aitems.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        AitemDao AitemDao = daoSession.getAitemDao();
        AitemDao.insertInTx(Aitems);
    }

    /**
     * 删除一条记录
     *
     * @param Aitem
     */
    public void deleteAitem(Aitem Aitem) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        AitemDao AitemDao = daoSession.getAitemDao();
        AitemDao.delete(Aitem);
    }

    /**
     * 更新一条记录
     *
     * @param Aitem
     */
    public void updateAitem(Aitem Aitem) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        AitemDao AitemDao = daoSession.getAitemDao();
        AitemDao.update(Aitem);
    }

    /**
     * 查询用户列表
     */
    public List<Aitem> queryAitemList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        AitemDao AitemDao = daoSession.getAitemDao();
        QueryBuilder<Aitem> qb = AitemDao.queryBuilder();
        List<Aitem> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public List<Aitem> queryAitemList(int age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        AitemDao AitemDao = daoSession.getAitemDao();
        QueryBuilder<Aitem> qb = AitemDao.queryBuilder();
        List<Aitem> list = qb.list();
        return list;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }
}
