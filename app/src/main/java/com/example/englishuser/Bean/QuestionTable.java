package com.example.englishuser.Bean;
/*
 * 文件名：QuestionTable
 * 作者：created by admin on 2019 六月
 * 描述：
 *
 */

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

public class QuestionTable extends BmobObject {
    private List<Question> questions = new ArrayList<>();   // 问题列表
    private int QuestionType;  // 问题类型

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getQuestionType() {
        return QuestionType;
    }

    public void setQuestionType(int questionType) {
        QuestionType = questionType;
    }
}
