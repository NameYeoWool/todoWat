package net.watcherapp.smallopen.watcher;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    List<ListItem> consolidatedList = new ArrayList<>();

    public Adapter(Context context, List<ListItem> consolidatedList) {
        this.consolidatedList = consolidatedList;
        this.mContext = context;


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case ListItem.TYPE_GENERAL:
                View v1 = inflater.inflate(R.layout.item, parent,
                        false);
                viewHolder = new GeneralViewHolder(v1);
                break;

            case ListItem.TYPE_DATE:
                View v2 = inflater.inflate(R.layout.item_non, parent, false);
                viewHolder = new DateViewHolder(v2);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {

            case ListItem.TYPE_GENERAL:

                GeneralItem generalItem   = (GeneralItem) consolidatedList.get(position);
                GeneralViewHolder generalViewHolder= (GeneralViewHolder) viewHolder;
                generalViewHolder.txtTitle.setText(generalItem.getPojoOfJsonArray().getName());

                break;

            case ListItem.TYPE_DATE:
                DateItem dateItem = (DateItem) consolidatedList.get(position);
                DateViewHolder dateViewHolder = (DateViewHolder) viewHolder;

                dateViewHolder.txtTitle.setText(dateItem.getDate());
                // Populate date item data here

                break;
        }
    }





    // ViewHolder for date row item
    class DateViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtTitle;

        public DateViewHolder(View v) {
            super(v);
            this.txtTitle = (TextView) v.findViewById(R.id.item_non_name);

        }
    }

    // View holder for general row item
    class GeneralViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtTitle;

        public GeneralViewHolder(View v) {
            super(v);
            this.txtTitle = (TextView) v.findViewById(R.id.item_name);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return consolidatedList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return consolidatedList != null ? consolidatedList.size() : 0;
    }

}