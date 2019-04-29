package net.watcherapp.smallopen.watcher;

import org.json.JSONObject;

/**
 * Created by aaa on 2019-04-15.
 */

public class PcInfoOfJson {
    private String name,address;
    private Float latitude, longitude;
    private String notice = "";
    private String spec = "";
    private int cnt_empty;



    public PcInfoOfJson(JSONObject jOb) {
        this.name = jOb.optString("name", "non");
        this.address = jOb.optString("address", "non");
        this.latitude = (float)jOb.optDouble("latitude", 0);
        this.longitude = (float)jOb.optDouble("longitude", 0);
        this.spec = jOb.optString("spec","non");
        this.cnt_empty = jOb.optInt("cnt_empty",0);
    }

    public int getCnt_empty() {
        return cnt_empty;
    }

    public void setCnt_empty(int cnt_empty) {
        this.cnt_empty = cnt_empty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}