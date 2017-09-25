package cn.peopleinvest.model;

public class LoginuserKey {
    private String type;

    private String userId;

    public LoginuserKey(String type, String userId) {
        this.type = type;
        this.userId = userId;
    }

    public LoginuserKey() {
        super();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}