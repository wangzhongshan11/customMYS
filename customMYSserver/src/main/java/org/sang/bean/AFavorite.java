package org.sang.bean;

import java.sql.Timestamp;

/**
 * Created by wzs on 2020/08/01.
 */
public class AFavorite {
    private Long id;
    private Long aid;
    private Timestamp aFavoriteDate;
    private Long uid;
    private String username;
    private String userface;
    private String nickname;
    private Integer userSex;

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

    public Timestamp getaFavoriteDate() {
        return aFavoriteDate;
    }

    public void setaFavoriteDate(Timestamp aFavoriteDate) {
        this.aFavoriteDate = aFavoriteDate;
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

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }
}
