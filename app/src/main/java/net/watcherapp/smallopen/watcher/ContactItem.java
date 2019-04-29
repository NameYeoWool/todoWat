package net.watcherapp.smallopen.watcher;

/**
 * Created by aaa on 2019-04-15.
 */

public class ContactItem extends PcListItem {
    private PcInfoOfJson pcInfoOfJson;

    public ContactItem(PcInfoOfJson pcInfoOfJson) {
        this.pcInfoOfJson = pcInfoOfJson;
    }

    public PcInfoOfJson getPcInfoOfJson() {
        return pcInfoOfJson;
    }

    public void setPcInfoOfJson(PcInfoOfJson pcInfoOfJson) {
        this.pcInfoOfJson = pcInfoOfJson;
    }

    @Override
    public int getType() {
        return TYPE_CONTACT;
    }


}