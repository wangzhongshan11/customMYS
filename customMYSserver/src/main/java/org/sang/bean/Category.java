package org.sang.bean;

import java.sql.Timestamp;

/**
 * Created by wzs on 2020/08/01.
 */
public class Category {
    private Long id;
    private String cateName;
    private Timestamp date;

    public Category() {
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
