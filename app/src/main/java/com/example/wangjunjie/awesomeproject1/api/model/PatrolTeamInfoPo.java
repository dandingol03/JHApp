package com.example.wangjunjie.awesomeproject1.api.model;



public class PatrolTeamInfoPo {
    private String id;          //id
    private String name;	    //队伍名称
    private String charger;		//负责人
    private String telephone;   //联系方式
    private String orgId;       //机构
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
