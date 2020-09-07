package org.sang.bean;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wzs on 2020/08/01.
 */
public class Article {
    private Long id;
    private String title;
    private String mdContent;
    private String htmlContent;
    private String summary;
    private Long cid;
    private Long uid;
    private Long pid;
    private String platename;
    private String forumname;
    private Timestamp publishDate;
    private Integer state;
    private Integer pageView;
    private Timestamp editTime;
    private String[] dynamicTags;
    private String nickname;
    private String userface;
    private String cateName;
    private List<Tags> tags;
    private List<ArticleTopic> articleTopics;
    private String stateStr;
    private List<Comment> commentList;
    private Long aCommentCount;
    private List<ASubscribe> aSubscribeList;
    private Long aSubscribeCount;
    private List<AFavorite> aFavoriteList;
    private Long aFavoriateCount;
    private Long viewCount;

    public Long getaCommentCount() {
        return aCommentCount;
    }

    public void setaCommentCount(Long aCommentCount) {
        this.aCommentCount = aCommentCount;
    }

    public Long getaSubscribeCount() {
        return aSubscribeCount;
    }

    public void setaSubscribeCount(Long aSubscribeCount) {
        this.aSubscribeCount = aSubscribeCount;
    }

    public Long getaFavoriateCount() {
        return aFavoriateCount;
    }

    public void setaFavoriateCount(Long aFavoriateCount) {
        this.aFavoriateCount = aFavoriateCount;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", mdContent='" + mdContent + '\'' +
                ", htmlContent='" + htmlContent + '\'' +
                ", summary='" + summary + '\'' +
                ", cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", platename='" + platename + '\'' +
                ", forumname='" + forumname + '\'' +
                ", publishDate=" + publishDate +
                ", state=" + state +
                ", pageView=" + pageView +
                ", editTime=" + editTime +
                ", dynamicTags=" + Arrays.toString(dynamicTags) +
                ", nickname='" + nickname + '\'' +
                ", userface='" + userface + '\'' +
                ", cateName='" + cateName + '\'' +
                ", tags=" + tags +
                ", articleTopics=" + articleTopics +
                ", stateStr='" + stateStr + '\'' +
                ", commentList=" + commentList +
                ", aSubscribeList=" + aSubscribeList +
                ", aFavoriteList=" + aFavoriteList +
                ", viewCount=" + viewCount +
                '}';
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public String getForumname() {
        return forumname;
    }

    public void setForumname(String forumname) {
        this.forumname = forumname;
    }

    public List<ASubscribe> getaSubscribeList() {
        return aSubscribeList;
    }

    public void setaSubscribeList(List<ASubscribe> aSubscribeList) {
        this.aSubscribeList = aSubscribeList;
    }

    public List<AFavorite> getaFavoriteList() {
        return aFavoriteList;
    }

    public void setaFavoriteList(List<AFavorite> aFavoriteList) {
        this.aFavoriteList = aFavoriteList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<ArticleTopic> getArticleTopics() {
        return articleTopics;
    }

    public void setArticleTopics(List<ArticleTopic> articleTopics) {
        this.articleTopics = articleTopics;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public String[] getDynamicTags() {
        return dynamicTags;
    }

    public void setDynamicTags(String[] dynamicTags) {
        this.dynamicTags = dynamicTags;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }
}
