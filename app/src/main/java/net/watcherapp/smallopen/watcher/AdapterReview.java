package net.watcherapp.smallopen.watcher;

import android.content.Context;
import android.media.Rating;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static net.watcherapp.smallopen.watcher.PcinfoActivity.pcName;

public class AdapterReview extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(PcInfoOfJson item);
    }

    private Context mContext;
    List<PcListItem> consolidatedList = new ArrayList<>();

    private final OnItemClickListener listener;

    public AdapterReview(Context context, List<PcListItem> consolidatedList){
        this.consolidatedList = consolidatedList;
        this.mContext = context;
        this.listener = null;
    }


    public AdapterReview(Context context, List<PcListItem> consolidatedList, OnItemClickListener listener)
    {
        this.consolidatedList = consolidatedList;
        this.mContext = context;
        this.listener = listener;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v1 = inflater.inflate(R.layout.item_review, parent, false);
        viewHolder = new ReviewHolder(v1);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        ReviewItem reviewItem= (ReviewItem) consolidatedList.get(position);
        ReviewHolder reviewHolder = (ReviewHolder) viewHolder;
        reviewHolder.reviewTitle.setText(reviewItem.getReviewOfJson().getTitle());
        reviewHolder.reviewTime.setText(reviewItem.getReviewOfJson().getTime());
        reviewHolder.reviewContent.setText(reviewItem.getReviewOfJson().getContent());
        reviewHolder.reviewRating.setRating(reviewItem.getReviewOfJson().getRating());

    }

    class ReviewHolder extends RecyclerView.ViewHolder{
        protected  TextView reviewTitle;
        protected  TextView reviewTime;
        protected  TextView reviewContent;
        protected  RatingBar reviewRating;

        public ReviewHolder(View v) {
            super(v);
            this.reviewTitle = (TextView) v.findViewById(R.id.review_title);
            this.reviewTime = (TextView) v.findViewById(R.id.review_time);
            this.reviewContent = (TextView) v.findViewById(R.id.review_content);
            this.reviewRating = (RatingBar) v.findViewById(R.id.review_ratingbar);
        }

    }


    @Override
    public int getItemViewType(int position) {
        Log.d("get item view type",String.valueOf(position));
        return consolidatedList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return consolidatedList != null ? consolidatedList.size() : 0;
    }

}