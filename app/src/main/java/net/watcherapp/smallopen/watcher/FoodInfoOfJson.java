package net.watcherapp.smallopen.watcher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by aaa on 2019-04-15.
 */

public class FoodInfoOfJson {
    @SerializedName("foodName")
    @Expose
    private String foodName;
    @SerializedName("rank")
    @Expose
    private String rank;


    public FoodInfoOfJson(JSONObject jOb) {
        this.foodName = jOb.optString("foodName", "non");
        this.rank = jOb.optString("rank", "non");
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}