package com.example.englishuser;
/*
 * 文件名：Aitem
 * 作者：created by admin on 2019 五月
 * 描述：
 *
 */

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Aitem {

    @Id(autoincrement = true)
    private Long _id;
    private int id;
    private String txt;

    @Generated(hash = 666378665)
    public Aitem(Long _id, int id, String txt) {
        this._id = _id;
        this.id = id;
        this.txt = txt;
    }

    @Generated(hash = 1986432759)
    public Aitem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }
}
