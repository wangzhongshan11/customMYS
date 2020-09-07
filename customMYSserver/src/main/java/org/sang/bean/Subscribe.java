package org.sang.bean;

import java.sql.Timestamp;

/**
 * Created by wzs on 2020/08/01.
 */
public class Subscribe {
    private Long id;
    private Long aid;
    private Long bid;
    private String username;
    private String userface;
    private String nickname;
    private String userSignature;
    private Integer userSex;
    private Timestamp subscribeDate;

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

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
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

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Timestamp getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(Timestamp subscribeDate) {
        this.subscribeDate = subscribeDate;
    }
}
