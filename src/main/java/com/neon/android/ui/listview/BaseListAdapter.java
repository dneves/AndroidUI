package com.neon.android.ui.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.neon.android.ui.listview.model.ListRowItem;

public class BaseListAdapter< ROW_MODEL, ROW_VIEW_HOLDER> extends ArrayAdapter<ListRowItem< ROW_MODEL, ROW_VIEW_HOLDER > > {

    private final LayoutInflater inflater;

    public BaseListAdapter(Context context) {
        super(context, 0);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get rendering item
        ListRowItem<ROW_MODEL, ROW_VIEW_HOLDER> item = getItem(position);

        // create view to render
        View view = convertView;

        RowItemHolder<ROW_MODEL, ROW_VIEW_HOLDER> viewHolder = item.getViewHolder();
        if ( view == null ) {
            view = inflater.inflate(viewHolder.getLayout( item ), parent, false);
            ROW_VIEW_HOLDER row_view_holder = viewHolder.onCreateView(view, item);
            view.setTag( row_view_holder );
        }
        viewHolder.fill( ( ROW_VIEW_HOLDER ) view.getTag(), item );

        return view;
    }

}
