package com.example.englishuser.Bean;
/*
 * 文件名：Question
 * 作者：created by admin on 2019 六月
 * 描述：
 *
 */

import cn.bmob.v3.BmobObject;

public class Question {

    String Question;    // 问题
    String Contents;    // 附带的内容
    String Answer;      // 问题的答案

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}
