package com.apexsoft.ysprj.application.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by go2zo on 2014. 8. 16..
 */
public class School {
    private String type;
    private String name;
    private String entrance;
    private String graduation;
    private Map<String, String> graduationType;

    public School() {
        this.graduationType = new HashMap<String, String>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public Map<String, String> getGraduationType() {
        return graduationType;
    }

    public void setGraduationType(Map<String, String> graduationType) {
        this.graduationType = graduationType;
    }
}
