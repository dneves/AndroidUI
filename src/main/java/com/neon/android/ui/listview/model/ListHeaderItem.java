package com.neon.android.ui.listview.model;

import com.neon.android.ui.listview.RowItemHolder;

public class ListHeaderItem< TYPE, VIEW_HOLDER > extends ListItem< TYPE, VIEW_HOLDER > {

    public ListHeaderItem( RowItemHolder<TYPE, VIEW_HOLDER > viewHolder ) {
        super(viewHolder);
    }

}
