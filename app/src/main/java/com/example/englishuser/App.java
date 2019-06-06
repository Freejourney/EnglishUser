package com.example.englishuser;

import android.app.Application;

import cn.bmob.v3.Bmob;

/*
 * 文件名：App
 * 作者：created by admin on 2019 六月
 * 描述：
 *
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "0764d7dc10986a48895d94e45f2664ae");
    }
}
