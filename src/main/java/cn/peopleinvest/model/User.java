package cn.peopleinvest.model;

import java.util.Date;

public class User {
    private String id;

    private String role;

    private Integer isPush;

    private Date joinTime;

    public User(String id, String role, Integer isPush, Date joinTime) {
        this.id = id;
        this.role = role;
        this.isPush = isPush;
        this.joinTime = joinTime;
    }

    public User() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Integer getIsPush() {
        return isPush;
    }

    public void setIsPush(Integer isPush) {
        this.isPush = isPush;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}