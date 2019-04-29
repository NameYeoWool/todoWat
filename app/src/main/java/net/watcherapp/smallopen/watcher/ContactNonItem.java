package net.watcherapp.smallopen.watcher;

/**
 * Created by aaa on 2019-04-15.
 */

public class ContactNonItem extends PcListItem {
    private PcInfoOfJson pcInfoOfJson;

    public ContactNonItem(PcInfoOfJson pcInfoOfJson) {
        this.pcInfoOfJson = pcInfoOfJson;
    }

    public PcInfoOfJson getPcInfoOfJson() {
        return pcInfoOfJson;
    }

    public void setPcListOfJsonArray(PcInfoOfJson pcInfoOfJson) {
        this.pcInfoOfJson = pcInfoOfJson;
    }

    @Override
    public int getType() {
        return TYPE_CONTACT_NON;
    }


}