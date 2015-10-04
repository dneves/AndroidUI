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
public class PinnedSectionAdapter< HEADER_MODEL, HEADER_VIEW_HOLDER, ROW_MODEL, ROW_VIEW_HOLDER >
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
                ListHeaderItem<HEADER_MODEL, HEADER_VIEW_HOLDER > headerItem = (ListHeaderItem<HEADER_MODEL, HEADER_VIEW_HOLDER >) item;
                RowItemHolder<HEADER_MODEL, HEADER_VIEW_HOLDER > viewHolder = headerItem.getViewHolder();
                if ( view == null ) {
                    view = inflater.inflate(viewHolder.getLayout(headerItem), parent, false);
                    HEADER_VIEW_HOLDER header_view_holder = viewHolder.onCreateView(view, headerItem);
                    view.setTag( header_view_holder );
                }
                viewHolder.fill( ( HEADER_VIEW_HOLDER ) view.getTag(), headerItem);
                break;
            }
            case ROW: {
                ListRowItem<ROW_MODEL, ROW_VIEW_HOLDER > rowItem = (ListRowItem<ROW_MODEL, ROW_VIEW_HOLDER >) item;
                RowItemHolder<ROW_MODEL, ROW_VIEW_HOLDER> viewHolder = rowItem.getViewHolder();
                if ( view == null ) {
                    view = inflater.inflate(viewHolder.getLayout(rowItem), parent, false);
                    ROW_VIEW_HOLDER row_view_holder = viewHolder.onCreateView(view, rowItem);
                    view.setTag( row_view_holder );
                }
                viewHolder.fill( ( ROW_VIEW_HOLDER ) view.getTag(), rowItem);
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
