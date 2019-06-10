package net.watcherapp.smallopen.watcher;

/**
 * Created by aaa on 2019-04-15.
 */

public class ReviewItem extends PcListItem {
    private ReviewOfJson reviewOfJson;

    public ReviewItem(ReviewOfJson reviewOfJson) {
        this.reviewOfJson = reviewOfJson;
    }

    public ReviewOfJson getReviewOfJson() {
        return reviewOfJson;
    }

    public void setReviewOfJson(ReviewOfJson reviewOfJson) {
        this.reviewOfJson = reviewOfJson;
    }

    @Override
    public int getType() {
        return TYPE_REVIEW;
    }


}