package com.neon.android.ui.listview.converter;


import com.neon.android.ui.listview.model.ListItem;

import java.util.List;

public interface ListItemConverter< INPUT > {

    List<ListItem> convert(INPUT input);

}
