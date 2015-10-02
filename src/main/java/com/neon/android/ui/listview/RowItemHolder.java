package com.neon.android.ui.listview;

import android.view.View;

import com.neon.android.ui.listview.model.ListItem;

public interface RowItemHolder< MODEL, V > {

    < I extends ListItem< MODEL, V > > int getLayout( I item );

    V onCreateView( View view );

    < I extends ListItem< MODEL, V > > void fill( V viewHolder, I item );

}
