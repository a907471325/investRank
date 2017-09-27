package cn.peopleinvest.model;

import org.springframework.data.annotation.Id;


public class WxOrder {

    @Id
    private String orderId;
    private String userId;
    private String code;
    private String dealTime;
    private String name;
    private String num;
    private String price;
    private String showImg;
    private String amount;
    private String buyer;
    private String phone;
    private String address;
    private String loglat;
    private String status;

    public WxOrder() {

    }

    public WxOrder(String orderId, String userId, String code, String dealTime, String name, String num, String price, String showImg, String amount, String buyer, String phone, String address, String loglat, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.code = code;
        this.dealTime = dealTime;
        this.name = name;
        this.num = num;
        this.price = price;
        this.showImg = showImg;
        this.amount = amount;
        this.buyer = buyer;
        this.phone = phone;
        this.address = address;
        this.loglat = loglat;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLoglat() {
        return loglat;
    }

    public void setLoglat(String loglat) {
        this.loglat = loglat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

