package net.watcherapp.smallopen.watcher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by aaa on 2019-04-15.
 */

public class ReviewOfJson {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("rating")
    @Expose
    private float rating;



    public ReviewOfJson(JSONObject jOb) {
        this.title = jOb.optString("title", "none");
        this.time = jOb.optString("time", "none");
        this.content = jOb.optString("content", "none");
        this.rating = (float) jOb.optDouble("rating", 0);
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}