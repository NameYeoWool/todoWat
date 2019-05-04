package net.watcherapp.smallopen.watcher;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Adapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(PcInfoOfJson item);
    }

    private Context mContext;
    List<PcListItem> consolidatedList = new ArrayList<>();

    private final OnItemClickListener listener;


    public Adapter2(Context context, List<PcListItem> consolidatedList,OnItemClickListener listener)
    {
        this.consolidatedList = consolidatedList;
        this.mContext = context;
        this.listener = listener;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case PcListItem.TYPE_CONTACT:
                View v1 = inflater.inflate(R.layout.item, parent,
                        false);
                viewHolder = new ContactViewHolder(v1);
                break;

            case PcListItem.TYPE_CONTACT_NON:
                View v2 = inflater.inflate(R.layout.item_non, parent, false);
                viewHolder = new ContactNonViewHolder(v2);
                break;

            case PcListItem.TYPE_SECTION:
                View v3 = inflater.inflate(R.layout.section, parent, false);
                viewHolder = new SectionViewHolder(v3);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {

            case PcListItem.TYPE_CONTACT:

                ContactItem contactItem= (ContactItem) consolidatedList.get(position);
                ContactViewHolder contactViewHolder= (ContactViewHolder) viewHolder;
                contactViewHolder.tvName.setText(contactItem.getPcInfoOfJson().getName());

                Log.d("ConataatItem getName",contactItem.getPcInfoOfJson().getName());

                contactViewHolder.tvAddress.setText(contactItem.getPcInfoOfJson().getAddress());
                contactViewHolder.tvItemCnt.setText(String.valueOf( contactItem.getPcInfoOfJson().getCnt_empty() ) );
                Log.d("item_cnt : ", String.valueOf(contactItem.getPcInfoOfJson().getCnt_empty()));

                contactViewHolder.bind(contactItem.getPcInfoOfJson(),listener);

                break;

            case PcListItem.TYPE_CONTACT_NON:

//                contactItem= (ContactItem) consolidatedList.get(position);
//                contactViewHolder= (ContactViewHolder) viewHolder;
//                Log.d("ConataatItem getName",contactItem.getPcInfoOfJson().getName());

                ContactNonItem contactNonItem= (ContactNonItem) consolidatedList.get(position);
                ContactNonViewHolder contactNonViewHolder= (ContactNonViewHolder) viewHolder;
                contactNonViewHolder.tvName.setText(contactNonItem.getPcInfoOfJson().getName());
                contactNonViewHolder.tvAddress.setText(contactNonItem.getPcInfoOfJson().getAddress());

                contactNonViewHolder.bind(null,listener);
                break;

            case PcListItem.TYPE_SECTION:
                SectionItem sectionItem = (SectionItem) consolidatedList.get(position);
                SectionViewHolder sectionViewHolder = (SectionViewHolder) viewHolder;
                sectionViewHolder.tvTitle.setText(sectionItem.getTitle());

                break;

        }
    }


    // sectionViewHolder for date row item
    class SectionViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvTitle;


        public SectionViewHolder (View v) {
            super(v);
            this.tvTitle = (TextView) v.findViewById(R.id.section_text);
        }
    }


    // ViewHolder for date row item
    class ContactNonViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvName;
        protected TextView tvAddress;
        protected RatingBar ratingBar;

        public ContactNonViewHolder (View v) {
            super(v);
            this.tvName = (TextView) v.findViewById(R.id.item_non_name);
            this.tvAddress = (TextView) v.findViewById(R.id.item_non_address);
            this.ratingBar = (RatingBar) v.findViewById(R.id.item_ratingbar);
        }
        public void bind(final PcInfoOfJson item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }

    // View holder for general row item
    class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvName;
        protected TextView tvAddress;
        protected RatingBar ratingBar;
        protected TextView tvItemCnt;

        public ContactViewHolder(View v) {
            super(v);
            this.tvName = (TextView) v.findViewById(R.id.item_name);
            this.tvAddress = (TextView) v.findViewById(R.id.item_address);
            this.ratingBar = (RatingBar) v.findViewById(R.id.item_ratingbar);
            this.tvItemCnt = (TextView) v.findViewById(R.id.item_cnt);

        }

        public void bind(final PcInfoOfJson item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
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