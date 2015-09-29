package com.neon.android.ui.listview.model;


import com.neon.android.ui.listview.RowItemHolder;

public abstract class PinnedListHeaderItem< TYPE > extends ListHeaderItem< TYPE > {

    public PinnedListHeaderItem(RowItemHolder<TYPE> viewHolder) {
        super(viewHolder);
    }

    public abstract String getLabel();
}
