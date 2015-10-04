package com.neon.android.ui.listview;

import android.view.View;

import com.neon.android.ui.listview.model.ListItem;

public interface RowItemHolder< MODEL, V > {

    < I extends ListItem< MODEL, V > > int getLayout( I item );

    < I extends ListItem< MODEL, V > > V onCreateView( View view, I item );

    < I extends ListItem< MODEL, V > > void fill( V viewHolder, I item );

}
