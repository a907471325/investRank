package cn.peopleinvest.model;

public class Loginuser extends LoginuserKey {

    private String loginname;

    private String password;

    private Integer state;

    private String remark;

    public Loginuser(String type, String userId, String loginname, String password, Integer state, String remark) {
        super(type, userId);
        this.loginname = loginname;
        this.password = password;
        this.state = state;
        this.remark = remark;
    }

    public Loginuser() {
        super();
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return this.getLoginname()+'-'+this.getPassword()+"-"+this.getRemark()+"-"+this.getState();
    }
}