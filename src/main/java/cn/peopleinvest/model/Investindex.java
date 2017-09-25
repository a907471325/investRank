package cn.peopleinvest.model;

public class Investindex {
    private Integer indexId;

    private String areaName;

    private String recordDate;

    private Float investIndex;

    private Integer localRank;

    private Integer rankTrend;

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public Float getInvestIndex() {
        return investIndex;
    }

    public void setInvestIndex(Float investIndex) {
        this.investIndex = investIndex;
    }

    public Integer getLocalRank() {
        return localRank;
    }

    public void setLocalRank(Integer localRank) {
        this.localRank = localRank;
    }

    public Integer getRankTrend() {
        return rankTrend;
    }

    public void setRankTrend(Integer rankTrend) {
        this.rankTrend = rankTrend;
    }

    public Investindex(Integer indexId, String areaName, String recordDate, Float investIndex, Integer localRank, Integer rankTrend) {
        this.indexId = indexId;
        this.areaName = areaName;
        this.recordDate = recordDate;
        this.investIndex = investIndex;
        this.localRank = localRank;
        this.rankTrend = rankTrend;
    }

    public Investindex() {
    }

    @Override
    public String toString() {
        return "Investindex{" +
                "indexId=" + indexId +
                ", areaName='" + areaName + '\'' +
                ", recordDate='" + recordDate + '\'' +
                ", investIndex=" + investIndex +
                ", localRank=" + localRank +
                ", rankTrend=" + rankTrend +
                '}';
    }
}