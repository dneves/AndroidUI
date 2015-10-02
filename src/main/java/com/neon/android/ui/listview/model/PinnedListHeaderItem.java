package com.neon.android.ui.listview.model;


import com.neon.android.ui.listview.RowItemHolder;

public abstract class PinnedListHeaderItem< TYPE, VIEW_HOLDER > extends ListHeaderItem< TYPE, VIEW_HOLDER > {

    public PinnedListHeaderItem(RowItemHolder<TYPE, VIEW_HOLDER > viewHolder) {
        super(viewHolder);
    }

    public abstract String getLabel();
}
