package cn.peopleinvest.model;

public class UserInfo {

    private String username;
    private String oriPassword;
    private String newPassword;
    private String oriPhone;
    private String newPhone;
    private String oriEmail;
    private String newEmail;
    private String isPush;

    public UserInfo(String username, String oriPassword, String newPassword, String oriPhone, String newPhone, String oriEmail, String newEmail, String isPush) {
        this.username = username;
        this.oriPassword = oriPassword;
        this.newPassword = newPassword;
        this.oriPhone = oriPhone;
        this.newPhone = newPhone;
        this.oriEmail = oriEmail;
        this.newEmail = newEmail;
        this.isPush = isPush;
    }

    public UserInfo() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOriPassword() {
        return oriPassword;
    }

    public void setOriPassword(String oriPassword) {
        this.oriPassword = oriPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOriPhone() {
        return oriPhone;
    }

    public void setOriPhone(String oriPhone) {
        this.oriPhone = oriPhone;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public String getOriEmail() {
        return oriEmail;
    }

    public void setOriEmail(String oriEmail) {
        this.oriEmail = oriEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getIsPush() {
        return isPush;
    }

    public void setIsPush(String isPush) {
        this.isPush = isPush;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", oriPassword='" + oriPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", oriPhone='" + oriPhone + '\'' +
                ", newPhone='" + newPhone + '\'' +
                ", oriEmail='" + oriEmail + '\'' +
                ", newEmail='" + newEmail + '\'' +
                ", isPush='" + isPush + '\'' +
                '}';
    }
}
