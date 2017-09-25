package cn.peopleinvest.model;

public class WxCardId {

    private String holderId;
    private String otherId;

    public WxCardId() {
    }

    public WxCardId(String holderId, String otherId) {
        this.holderId = holderId;
        this.otherId = otherId;
    }

    public String getOtherId() {
        return otherId;
    }

    public String getHolderId() {
        return holderId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WxCardId wxCardId = (WxCardId) o;

        if (holderId != null ? !holderId.equals(wxCardId.holderId) : wxCardId.holderId != null) return false;
        return otherId != null ? otherId.equals(wxCardId.otherId) : wxCardId.otherId == null;
    }

    @Override
    public int hashCode() {
        int result = holderId != null ? holderId.hashCode() : 0;
        result = 31 * result + (otherId != null ? otherId.hashCode() : 0);
        return result;
    }
}
