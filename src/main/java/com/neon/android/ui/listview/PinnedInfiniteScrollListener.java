package com.neon.android.ui.listview;


import com.neon.android.ui.listview.model.ListItem;

public abstract class PinnedInfiniteScrollListener< HEADER_MODEL, ROW_MODEL > extends InfiniteScrollListener {

    private final PinnedSectionAdapter< HEADER_MODEL, ROW_MODEL > adapter;

    public PinnedInfiniteScrollListener(PinnedSectionAdapter< HEADER_MODEL, ROW_MODEL > adapter) {
        this.adapter = adapter;
    }

    public PinnedInfiniteScrollListener(int visibleThreshold, PinnedSectionAdapter< HEADER_MODEL, ROW_MODEL > adapter) {
        super(visibleThreshold);
        this.adapter = adapter;
    }

    public PinnedInfiniteScrollListener(int visibleThreshold, int startPage, PinnedSectionAdapter< HEADER_MODEL, ROW_MODEL > adapter) {
        super(visibleThreshold, startPage);
        this.adapter = adapter;
    }

    @Override
    public void loadFromPage(int page, int totalItemsCount) {
        int lastItemIndex = adapter.getCount() - 1;
        ListItem< ? > listItem = lastItemIndex >= 0 ? adapter.getItem( lastItemIndex ) : null;
        loadFromItem( listItem, page, totalItemsCount );
    }

    public abstract void loadFromItem( ListItem< ? > item, int page, int totalItemsCount );

}
