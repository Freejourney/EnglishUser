package com.example.englishuser.activity;
/*
 * 文件名：NewsActivity
 * 作者：created by admin on 2019 五月
 * 描述：
 *
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.englishuser.Bean.Question;
import com.example.englishuser.Bean.QuestionTable;
import com.example.englishuser.R;
import com.example.englishuser.util.Config;
import com.just.agentweb.AgentWeb;
import com.zwy.xlog.XLog;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class NewsActivity extends Activity {
    AgentWeb mAgentWeb;
    private LinearLayout linearLayout;
    private Button btn_upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        linearLayout = findViewById(R.id.llayoutnews);

        btn_upload = findViewById(R.id.btn_upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Question> questions = new ArrayList<>();
                String txt = Config.txt;
                String[] txts = txt.split("\n");
                for (String item : txts) {
                    Question question = new Question();
                    question.setQuestion(item);
                    questions.add(question);
                }
                QuestionTable questionTable = new QuestionTable();
                questionTable.setQuestions(questions);
                questionTable.setQuestionType(Config.Type_Independent_Writing);
                questionTable.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            XLog.i("upload", "success");
                        } else {
                            XLog.e("upload", e.toString());
                        }
                    }
                });
            }
        });

        String url = getIntent().getStringExtra("url");

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(linearLayout, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);
    }
}
