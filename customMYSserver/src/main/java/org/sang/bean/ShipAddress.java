package org.sang.bean;

/**
 * Created by wzs on 2020/08/01.
 */
public class ShipAddress {
    private Long id;
    private Long uid;
    private String uRealname;
    private String shipAddress;
    private String detailShipAddress;
    private String uAreaCode;
    private String uPhone;
    private Integer isDefaultShipAddr;

    public String getDetailShipAddress() {
        return detailShipAddress;
    }

    public void setDetailShipAddress(String detailShipAddress) {
        this.detailShipAddress = detailShipAddress;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public ShipAddress(Long id, Long uid, String uRealname, String shipAddress, String detailShipAddress, String uAreaCode, String uPhone, Integer isDefaultShipAddr) {
        this.id = id;
        this.uid = uid;
        this.uRealname = uRealname;
        this.shipAddress = shipAddress;
        this.detailShipAddress = detailShipAddress;
        this.uAreaCode = uAreaCode;
        this.uPhone = uPhone;
        this.isDefaultShipAddr = isDefaultShipAddr;
    }

    public ShipAddress() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuRealname() {
        return uRealname;
    }

    public void setuRealname(String uRealname) {
        this.uRealname = uRealname;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getuAreaCode() {
        return uAreaCode;
    }

    public void setuAreaCode(String uAreaCode) {
        this.uAreaCode = uAreaCode;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public Integer getIsDefaultShipAddr() {
        return isDefaultShipAddr;
    }

    public void setIsDefaultShipAddr(Integer isDefaultShipAddr) {
        this.isDefaultShipAddr = isDefaultShipAddr;
    }
}
