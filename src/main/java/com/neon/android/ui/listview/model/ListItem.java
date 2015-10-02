package com.neon.android.ui.listview.model;


import com.neon.android.ui.listview.RowItemHolder;

public class ListItem< TYPE, VIEW_HOLDER > {

    public interface OnItemClickListener< TYPE > {
        void onClick(TYPE model);
    }

    private final RowItemHolder<TYPE, VIEW_HOLDER > viewHolder;

    private OnItemClickListener< TYPE > onItemClickListener;

    private TYPE model;


    public ListItem( RowItemHolder<TYPE, VIEW_HOLDER > viewHolder ) {
        this.viewHolder = viewHolder;
    }

    public RowItemHolder<TYPE, VIEW_HOLDER > getViewHolder() {
        return viewHolder;
    }

    public TYPE getModel() {
        return model;
    }

    public void setModel(TYPE model) {
        this.model = model;
    }

    public OnItemClickListener<TYPE> getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener<TYPE> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
