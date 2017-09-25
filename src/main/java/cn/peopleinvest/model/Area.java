package cn.peopleinvest.model;

public class Area {
    private String areaName;

    private Integer areaType;

    private String areaSpell;

    public Area(String areaName, Integer areaType, String areaSpell) {
        this.areaName = areaName;
        this.areaType = areaType;
        this.areaSpell = areaSpell;
    }

    public Area() {
        super();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public String getAreaSpell() {
        return areaSpell;
    }

    public void setAreaSpell(String areaSpell) {
        this.areaSpell = areaSpell == null ? null : areaSpell.trim();
    }
}