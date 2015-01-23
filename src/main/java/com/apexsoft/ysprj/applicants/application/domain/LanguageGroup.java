package com.apexsoft.ysprj.applicants.application.domain;

public class LanguageGroup extends ApplicationLanguage {

    private String langExamName;//추가 항목


    public String getLangExamName() {
        return langExamName;
    }

    public void setLangExamName(String langExamName) {
        this.langExamName = langExamName;
    }
}