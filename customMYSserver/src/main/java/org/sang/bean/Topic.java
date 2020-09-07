package org.sang.bean;

import java.util.List;

/**
 * Created by wzs on 2020/08/01.
 */
public class Topic {
    private Long id;
    private String topicname;
    private Long pid;
    private String platename;
    private String topicface;
    private String topicSignature;
    private Long viewCount;
    private List<Article> topicArticleList;

    public List<Article> getTopicArticleList() {
        return topicArticleList;
    }

    public void setTopicArticleList(List<Article> topicArticleList) {
        this.topicArticleList = topicArticleList;
    }

    public String getTopicface() {
        return topicface;
    }

    public void setTopicface(String topicface) {
        this.topicface = topicface;
    }

    public String getTopicSignature() {
        return topicSignature;
    }

    public void setTopicSignature(String topicSignature) {
        this.topicSignature = topicSignature;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPlatename() {
        return platename;
    }

    public void setPlatename(String platename) {
        this.platename = platename;
    }
}
