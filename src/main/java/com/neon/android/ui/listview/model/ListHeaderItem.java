package com.neon.android.ui.listview.model;

import com.neon.android.ui.listview.RowItemHolder;

public class ListHeaderItem< TYPE, VIEW_HOLDER > extends ListItem< TYPE, VIEW_HOLDER > {

    private boolean expanded = true;

    public ListHeaderItem( RowItemHolder<TYPE, VIEW_HOLDER > viewHolder ) {
        super(viewHolder);
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }

}
