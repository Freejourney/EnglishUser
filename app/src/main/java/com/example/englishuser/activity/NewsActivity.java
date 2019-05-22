package com.example.englishuser.activity;
/*
 * 文件名：NewsActivity
 * 作者：created by admin on 2019 五月
 * 描述：
 *
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.englishuser.R;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebView;

public class NewsActivity extends Activity {
    AgentWeb mAgentWeb;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        linearLayout = findViewById(R.id.llayoutnews);

        String url = getIntent().getStringExtra("url");

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(linearLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);
    }
}
