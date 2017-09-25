package cn.peopleinvest.model;

import java.util.List;

public class WxItem {

    private String id;
    private String type;
    private String name;
    private String pressorauthor;
    private String pagesornum;
    private String sales;
    private Double price;
    private String intro;
    private List<Object> detailSound;
    private List<String> goods;
    private String detailBook;

    public WxItem() {
    }

    public WxItem(String id, String type, String name, String pressorauthor, String pagesornum, String sales, Double price, String intro, List<Object> detailSound, List<String> goods, String detailBook) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.pressorauthor = pressorauthor;
        this.pagesornum = pagesornum;
        this.sales = sales;
        this.price = price;
        this.intro = intro;
        this.detailSound = detailSound;
        this.goods = goods;
        this.detailBook = detailBook;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPressorauthor() {
        return pressorauthor;
    }

    public void setPressorauthor(String pressorauthor) {
        this.pressorauthor = pressorauthor;
    }

    public String getPagesornum() {
        return pagesornum;
    }

    public void setPagesornum(String pagesornum) {
        this.pagesornum = pagesornum;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public List<Object> getDetailSound() {
        return detailSound;
    }

    public void setDetailSound(List<Object> detailSound) {
        this.detailSound = detailSound;
    }

    public List<String> getGoods() {
        return goods;
    }

    public void setGoods(List<String> goods) {
        this.goods = goods;
    }

    public String getDetailBook() {
        return detailBook;
    }

    public void setDetailBook(String detailBook) {
        this.detailBook = detailBook;
    }
}
