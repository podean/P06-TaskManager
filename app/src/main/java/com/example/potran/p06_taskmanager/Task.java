package com.example.potran.p06_taskmanager;

import java.io.Serializable;

/**
 * Created by 15017167 on 30/5/2017.
 */

public class Task implements Serializable {
    private int id;
    private String name;
    private String des;

    public Task(int id, String name, String des) {
        this.id = id;
        this.name = name;
        this.des = des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
