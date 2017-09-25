package cn.peopleinvest.model;

public class WxUser {
    private String id;
    private String nickName;

    public WxUser(String id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public WxUser() {

    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WxUser{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
