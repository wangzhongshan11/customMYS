package org.sang.bean;

/**
 * Created by wzs on 2020/08/01.
 */
public class ArticleTopic {
    private Long id;
    private Long aid;
    private String topicname;
    private Long pid;
    private String platename;
    private String topicface;
    private Long viewCount;
    private String topicSignature;

    public String getTopicSignature() {
        return topicSignature;
    }

    public void setTopicSignature(String topicSignature) {
        this.topicSignature = topicSignature;
    }

    public String getTopicface() {
        return topicface;
    }

    public void setTopicface(String topicface) {
        this.topicface = topicface;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public String getPlatename() {
        return platename;
    }

    public void setPlatename(String platename) {
        this.platename = platename;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }



    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }
}
