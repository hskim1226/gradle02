package com.apexsoft.ysprj.user.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by go2zo on 2014. 8. 16..
 */
public class Academy {
    private String type;
    private String name;
    private String entrance;
    private String graduation;
    private String graduationType;

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

    public String getGraduationType() {
        return graduationType;
    }

    public void setGraduationType(String graduationType) {
        this.graduationType = graduationType;
    }
}
