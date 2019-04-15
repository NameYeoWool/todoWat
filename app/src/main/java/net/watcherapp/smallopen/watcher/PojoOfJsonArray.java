package net.watcherapp.smallopen.watcher;

/**
 * Created by aaa on 2019-04-15.
 */

public class PojoOfJsonArray  {
    private String name,date;
    private String address;
    private int cnt_emptySeat;

    public PojoOfJsonArray(String name, String date, String address, int cnt_emptySeat) {
        this.name = name;
        this.date = date;
        this.address = address;
        this.cnt_emptySeat = cnt_emptySeat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCnt_emptySeat() {
        return cnt_emptySeat;
    }

    public void setCnt_emptySeat(int cnt_emptySeat) {
        this.cnt_emptySeat = cnt_emptySeat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}