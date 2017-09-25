package cn.peopleinvest.model;

public class Syslog {
    private Integer id;

    private String userId;

    private Integer optType;

    private String optDetail;

    public Syslog(Integer id, String userId, Integer optType, String optDetail) {
        this.id = id;
        this.userId = userId;
        this.optType = optType;
        this.optDetail = optDetail;
    }

    public Syslog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getOptType() {
        return optType;
    }

    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    public String getOptDetail() {
        return optDetail;
    }

    public void setOptDetail(String optDetail) {
        this.optDetail = optDetail == null ? null : optDetail.trim();
    }
}