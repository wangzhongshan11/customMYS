package org.sang.bean;


/**
 * Created by wzs on 2020/08/01.
 */
public class UserForum {
    private Long id;
    private String forumname;
    private Long uForumGrade;
    private Long uForumExperience;
    private String forumface;

    public UserForum() {
    }

    public UserForum(Long id, String forumname, Long uForumGrade, Long uForumExperience, String forumface) {
        this.id = id;
        this.forumname = forumname;
        this.uForumGrade = uForumGrade;
        this.uForumExperience = uForumExperience;
        this.forumface = forumface;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getForumname() {
        return forumname;
    }

    public void setForumname(String forumname) {
        this.forumname = forumname;
    }

    public Long getuForumGrade() {
        return uForumGrade;
    }

    public void setuForumGrade(Long uForumGrade) {
        this.uForumGrade = uForumGrade;
    }

    public Long getuForumExperience() {
        return uForumExperience;
    }

    public void setuForumExperience(Long uForumExperience) {
        this.uForumExperience = uForumExperience;
    }

    public String getForumface() {
        return forumface;
    }

    public void setForumface(String forumface) {
        this.forumface = forumface;
    }
}
