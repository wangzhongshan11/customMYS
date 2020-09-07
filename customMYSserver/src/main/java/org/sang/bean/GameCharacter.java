package org.sang.bean;

/**
 * Created by wzs on 2020/08/01.
 */
public class GameCharacter {
    private Long id;
    private Long uid;
    private Long gid;
    private String gamename;
    private String gameface;
    private Integer uCharaGrade;
    private Integer uCharaUid;
    private String uCharaName;
    private Integer isDefaultChara;
    private String uChannel;
    private Integer verCode;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public GameCharacter(Long id, Long uid, Long gid, String gamename, String gameface, Integer uCharaGrade, Integer uCharaUid, String uCharaName, Integer isDefaultChara, String uChannel, Integer verCode) {
        this.id = id;
        this.uid = uid;
        this.gid = gid;
        this.gamename = gamename;
        this.gameface = gameface;
        this.uCharaGrade = uCharaGrade;
        this.uCharaUid = uCharaUid;
        this.uCharaName = uCharaName;
        this.isDefaultChara = isDefaultChara;
        this.uChannel = uChannel;
        this.verCode = verCode;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getuChannel() {
        return uChannel;
    }

    public void setuChannel(String uChannel) {
        this.uChannel = uChannel;
    }

    public Integer getVerCode() {
        return verCode;
    }

    public void setVerCode(Integer verCode) {
        this.verCode = verCode;
    }



    public GameCharacter() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public String getGameface() {
        return gameface;
    }

    public void setGameface(String gameface) {
        this.gameface = gameface;
    }

    public Integer getuCharaGrade() {
        return uCharaGrade;
    }

    public void setuCharaGrade(Integer uCharaGrade) {
        this.uCharaGrade = uCharaGrade;
    }

    public Integer getuCharaUid() {
        return uCharaUid;
    }

    public void setuCharaUid(Integer uCharaUid) {
        this.uCharaUid = uCharaUid;
    }

    public String getuCharaName() {
        return uCharaName;
    }

    public void setuCharaName(String uCharaName) {
        this.uCharaName = uCharaName;
    }

    public Integer getIsDefaultChara() {
        return isDefaultChara;
    }

    public void setIsDefaultChara(Integer isDefaultChara) {
        this.isDefaultChara = isDefaultChara;
    }
}
