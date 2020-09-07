package org.sang.bean;

/**
 * Created by wzs on 2020/08/01.
 */
public class ForumGradeExp {
    private Integer lv;
    private Integer exp;
    private String lvColor;

    public ForumGradeExp() {
    }

    public ForumGradeExp(Integer lv, Integer exp, String lvColor) {
        this.lv = lv;
        this.exp = exp;
        this.lvColor = lvColor;
    }

    public Integer getLv() {
        return lv;
    }

    public void setLv(Integer lv) {
        this.lv = lv;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getLvColor() {
        return lvColor;
    }

    public void setLvColor(String lvColor) {
        this.lvColor = lvColor;
    }
}
