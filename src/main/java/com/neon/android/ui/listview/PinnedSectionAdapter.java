package com.neon.android.ui.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.neon.android.ui.listview.model.ListHeaderItem;
import com.neon.android.ui.listview.model.ListItem;
import com.neon.android.ui.listview.model.ListRowItem;

/**
 * https://github.com/beworker/pinned-section-listview
 */
public class PinnedSectionAdapter< HEADER_MODEL, ROW_MODEL >
        extends ArrayAdapter<ListItem>
        implements PinnedSectionListView.PinnedSectionListAdapter {

    private static final int HEADER = 0;
    private static final int ROW = 1;

    private final LayoutInflater inflater;


    public PinnedSectionAdapter( Context context ) {
        super(context, 0);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get rendering item
        ListItem item = getItem(position);

        // create view to render
        View view = convertView;
        int viewType = getItemViewType(position);
        switch (viewType) {
            case HEADER: {
                ListHeaderItem<HEADER_MODEL> headerItem = (ListHeaderItem<HEADER_MODEL>) item;
                RowItemHolder<HEADER_MODEL> viewHolder = headerItem.getViewHolder();
                // TODO : use viewholder pattern
                view = inflater.inflate(viewHolder.getLayout(headerItem), parent, false);
                viewHolder.fill(view, headerItem);
                break;
            }
            case ROW: {
                ListRowItem<ROW_MODEL> rowItem = (ListRowItem<ROW_MODEL>) item;
                RowItemHolder<ROW_MODEL> viewHolder = rowItem.getViewHolder();
                // TODO : use viewholder pattern
                view = inflater.inflate( viewHolder.getLayout(rowItem), parent, false);
                viewHolder.fill(view, rowItem);
                break;
            }
        }

        return view;
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == HEADER;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if ( position >= 0 && position < getCount() ) {
            ListItem item = getItem(position);
            if (item instanceof ListHeaderItem) {
                return HEADER;
            }
        }
        return ROW;
    }

}
