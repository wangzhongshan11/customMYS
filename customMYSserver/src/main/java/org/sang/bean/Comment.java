package org.sang.bean;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by wzs on 2020/08/01.
 */
public class Comment {
    private Long id;
    private Long aid;
    private String content;
    private String articleTitle;
    private String forumname;
    private Timestamp publishDate;
    private Long parentId;
    private Long uid;
    private String username;
    private String userface;
    private String nickname;
    private Integer userSex;
    private List<Comment> subCommentList;
    private Long subCommentListCount;
    private List<CSubscribe> cSubscribeList;
    private Long cSubscribeListCount;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getForumname() {
        return forumname;
    }

    public void setForumname(String forumname) {
        this.forumname = forumname;
    }

    public List<CSubscribe> getcSubscribeList() {
        return cSubscribeList;
    }

    public void setcSubscribeList(List<CSubscribe> cSubscribeList) {
        this.cSubscribeList = cSubscribeList;
    }

    public Long getcSubscribeListCount() {
        return cSubscribeListCount;
    }

    public void setcSubscribeListCount(Long cSubscribeListCount) {
        this.cSubscribeListCount = cSubscribeListCount;
    }

    public Long getSubCommentListCount() {
        return subCommentListCount;
    }

    public void setSubCommentListCount(Long subCommentListCount) {
        this.subCommentListCount = subCommentListCount;
    }

    public List<Comment> getSubCommentList() {
        return subCommentList;
    }

    public void setSubCommentList(List<Comment> subCommentList) {
        this.subCommentList = subCommentList;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
