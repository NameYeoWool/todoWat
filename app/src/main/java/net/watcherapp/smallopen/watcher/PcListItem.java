package net.watcherapp.smallopen.watcher;

/**
 * Created by aaa on 2019-04-15.
 */

public abstract class PcListItem {

    public static final int TYPE_CONTACT = 0;
    public static final int TYPE_CONTACT_NON = 1;
    public static final int TYPE_SECTION = 2;
    public static final int TYPE_FOOD=3;
    public static final int TYPE_REVIEW=4;

    abstract public int getType();
}
