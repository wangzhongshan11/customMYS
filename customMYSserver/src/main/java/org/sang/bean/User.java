package org.sang.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzs on 2020/08/01.
 */
public class User implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private boolean enabled;
    private List<Role> roles;
    private String email;
    private String userface;
    private Timestamp regTime;
    private Integer userSex;
    private String userSignature;
    private Integer miyoubiCount;
    private String userPhone;

    private List<Subscribe> subscribes;
    private List<Subscribe> fans;
    private List<UserForum> userForums;
    private List<GameCharacter> gameCharacters;
    private List<ShipAddress> shipAddresses;
    private List<Topic> subscribeTopics;
    private List<Article> articleList;
    private List<Comment> commentList;
    private List<Article> aSubscribeList;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Article> getaSubscribeList() {
        return aSubscribeList;
    }

    public void setaSubscribeList(List<Article> aSubscribeList) {
        this.aSubscribeList = aSubscribeList;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public List<Topic> getSubscribeTopics() {
        return subscribeTopics;
    }

    public void setSubscribeTopics(List<Topic> subscribeTopics) {
        this.subscribeTopics = subscribeTopics;
    }

    public List<GameCharacter> getGameCharacters() {
        return gameCharacters;
    }

    public void setGameCharacters(List<GameCharacter> gameCharacters) {
        this.gameCharacters = gameCharacters;
    }

    public List<ShipAddress> getShipAddresses() {
        return shipAddresses;
    }

    public void setShipAddresses(List<ShipAddress> shipAddresses) {
        this.shipAddresses = shipAddresses;
    }

    public List<UserForum> getUserForums() {
        return userForums;
    }

    public void setUserForums(List<UserForum> userForums) {
        this.userForums = userForums;
    }

    public List<Subscribe> getSubscribes() {
        return subscribes;
    }

    public void setSubscribes(List<Subscribe> subscribes) {
        this.subscribes = subscribes;
    }

    public List<Subscribe> getFans() {
        return fans;
    }

    public void setFans(List<Subscribe> fans) {
        this.fans = fans;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public Integer getMiyoubiCount() {
        return miyoubiCount;
    }

    public void setMiyoubiCount(Integer miyoubiCount) {
        this.miyoubiCount = miyoubiCount;
    }

    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    @JsonIgnore
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
