package cn.peopleinvest.model;

public class WxCard {

    private String id;
    private String ownerID;
    private String name;
    private String title;
    private String mobile;
    private String companyName;
    private String avatarUrl;
    private String email;
    private String loglat;
    private String address;
    private String need;
    private String project;
    private String intro;

    public WxCard() {

    }

    public WxCard(String id, String ownerID, String name, String title, String mobile, String companyName, String avatarUrl, String email, String loglat, String address, String need, String project, String intro) {
        this.id = id;
        this.ownerID = ownerID;
        this.name = name;
        this.title = title;
        this.mobile = mobile;
        this.companyName = companyName;
        this.avatarUrl = avatarUrl;
        this.email = email;
        this.loglat = loglat;
        this.address = address;
        this.need = need;
        this.project = project;
        this.intro = intro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoglat() {
        return loglat;
    }

    public void setLoglat(String loglat) {
        this.loglat = loglat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "WxCard{" +
                "id='" + id + '\'' +
                ", ownerID='" + ownerID + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", mobile='" + mobile + '\'' +
                ", companyName='" + companyName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", email='" + email + '\'' +
                ", loglat='" + loglat + '\'' +
                ", address='" + address + '\'' +
                ", need='" + need + '\'' +
                ", project='" + project + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
