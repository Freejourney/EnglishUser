package com.example.englishuser.activity;
/*
 * 文件名：DetailActivity
 * 作者：created by admin on 2019 五月
 * 描述：
 *
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishuser.Bean.Aitem;
import com.example.englishuser.R;
import com.example.englishuser.db.DBManager;
import com.zwy.xlog.XLog;

import java.util.List;

import static com.example.englishuser.util.Util.showToast;

public class DetailActivity extends Activity {

    private EditText editText;
    private FloatingActionButton Savebtn;
    private int position;
    List<Aitem> items;
    private DBManager dbManager = new DBManager(DetailActivity.this);
    private int index;
    private TextView tv_Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        position = getIntent().getIntExtra("Id", 0);
        String title = getIntent().getStringExtra("title");
        editText = findViewById(R.id.txt_detail_et);
        Savebtn = findViewById(R.id.fab_save);
        tv_Title = findViewById(R.id.tv_title);
        tv_Title.setText(Html.fromHtml("<p style=\"text-align:justify; text-justify:inter-ideograph;\">"+title+"</p>"));
        initView();

        Thread thread = new Timerutil();
        thread.start();

        Savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aitem aitem = new Aitem();
                aitem.setTxt(editText.getText().toString());
                aitem.setId(position);
                aitem.set_id((long) position);
                if (isExist()) {
                    dbManager.updateAitem(aitem);
                } else {
                    dbManager.insertAitem(aitem);
                }
                showToast(DetailActivity.this, "保存成功", Toast.LENGTH_SHORT);
            }
        });
    }

    private void GetData() {
        items = dbManager.queryAitemList();
    }

    private boolean isExist() {
        GetData();
        for (int i = 0; i< items.size(); i++) {
            if (items.get(i).get_id() == position) {
                index = i;
                return true;
            }
        }
        return false;
    }

    private void initView() {
        GetData();
        if (isExist()) {
            editText.setText(items.get(index).getTxt());
        } else {
            editText.setHint("you can write something ...");
        }
    }

    public class Timerutil extends Thread {

        public void run() {
            while (true) {
                String str = editText.getText().toString();
                try {
                    //间隔时间10秒
                    Thread.sleep(10000);
                    if (!editText.getText().toString().equals(str)) {
                        Aitem aitem = new Aitem();
                        aitem.setTxt(editText.getText().toString());
                        aitem.setId(position);
                        aitem.set_id((long) position);
                        dbManager.updateAitem(aitem);
                        XLog.i("updatesuccess",aitem.getTxt());
                        showToast(DetailActivity.this, "自动保存成功", Toast.LENGTH_SHORT);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


