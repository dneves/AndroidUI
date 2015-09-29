package com.neon.android.ui.listview.model;

import com.neon.android.ui.listview.RowItemHolder;

public class ListHeaderItem< TYPE > extends ListItem< TYPE > {

    public ListHeaderItem( RowItemHolder<TYPE> viewHolder ) {
        super(viewHolder);
    }

    @Override
    public String toString() {
        return getModel().toString();
    }

}
