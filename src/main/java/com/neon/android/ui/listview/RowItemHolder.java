package com.neon.android.ui.listview;

import android.view.View;

import com.neon.android.ui.listview.model.ListItem;

public interface RowItemHolder< MODEL > {

    < I extends ListItem< MODEL >> int getLayout(I item);

    < I extends ListItem< MODEL > > void fill(final View view, final I item);

}
