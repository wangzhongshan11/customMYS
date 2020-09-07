package org.sang.bean;

import java.util.List;

/**
 * Created by wzs on 2020/08/01.
 */
public class Forum {
    private Long id;
    private String forumname;
    private String forumface;
    private List<Plate> plateList;

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

    public String getForumface() {
        return forumface;
    }

    public void setForumface(String forumface) {
        this.forumface = forumface;
    }

    public List<Plate> getPlateList() {
        return plateList;
    }

    public void setPlateList(List<Plate> plateList) {
        this.plateList = plateList;
    }
}
