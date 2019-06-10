package net.watcherapp.smallopen.watcher;

/**
 * Created by aaa on 2019-04-15.
 */

public class FoodItem extends PcListItem {
    private FoodInfoOfJson foodInfoOfJson;

    public FoodItem(FoodInfoOfJson foodInfoOfJson) {
        this.foodInfoOfJson = foodInfoOfJson;

    }

    public FoodInfoOfJson getFoodInfoOfJson() {
        return foodInfoOfJson;
    }

    public void setFoodInfoOfJson(FoodInfoOfJson foodInfoOfJson) {
        this.foodInfoOfJson = foodInfoOfJson;
    }
    @Override
    public int getType() {
        return TYPE_FOOD;
    }
}