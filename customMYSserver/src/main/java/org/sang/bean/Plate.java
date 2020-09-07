package org.sang.bean;

import java.util.List;

/**
 * Created by wzs on 2020/08/01.
 */
public class Plate {
    private Long id;
    private String platename;
    private Long fid;
    private String forumname;
    private Long isArticle;
    private String plateface;
    private List<Topic> topicList;

    public String getPlateface() {
        return plateface;
    }

    public void setPlateface(String plateface) {
        this.plateface = plateface;
    }

    public String getForumname() {
        return forumname;
    }

    public void setForumname(String forumname) {
        this.forumname = forumname;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public Long getIsArticle() {
        return isArticle;
    }

    public void setIsArticle(Long isArticle) {
        this.isArticle = isArticle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatename() {
        return platename;
    }

    public void setPlatename(String platename) {
        this.platename = platename;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }
}
