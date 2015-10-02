package com.neon.android.ui.listview;

import android.annotation.TargetApi;
import android.content.Context;
import android.widget.SectionIndexer;

import com.neon.android.ui.listview.model.ListHeaderItem;
import com.neon.android.ui.listview.model.ListItem;
import com.neon.android.ui.listview.model.PinnedListHeaderItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IndexedPinnedSectionAdapter< HEADER_MODEL, HEADER_VIEW_HOLDER, ROW_MODEL, ROW_VIEW_HOLDER >
        extends PinnedSectionAdapter< HEADER_MODEL, HEADER_VIEW_HOLDER, ROW_MODEL, ROW_VIEW_HOLDER >
        implements SectionIndexer {


    private static class SectionItem {
        private String title;
        private int sectionPosition;
        private int listPosition;

        public SectionItem(String title, int sectionPosition, int listPosition) {
            this.title = title;
            this.sectionPosition = sectionPosition;
            this.listPosition = listPosition;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    private final List< SectionItem > sections = new ArrayList<>();

    // listPosition -> sectionitem
    private final Map< Integer, SectionItem > byListPosition = new HashMap<>();


    private int sectionPosition = 0;
    private int listPosition = 0;


    public IndexedPinnedSectionAdapter(Context context) {
        super(context);
    }

    @Override
    public Object[] getSections() {
        return sections.toArray();
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        if (sectionIndex >= sections.size()) {
            sectionIndex = sections.size() - 1;
        }
        SectionItem sectionItem = sectionIndex >= 0 && sectionIndex < sections.size() ?
                sections.get(sectionIndex) : null;
        return sectionItem == null ? 0 : sectionItem.listPosition;
    }

    @Override
    public int getSectionForPosition(int position) {
        if (position >= getCount()) {
            position = getCount() - 1;
        }
        SectionItem sectionItem = byListPosition.get(position);
        return sectionItem == null ? 0 : sectionItem.sectionPosition;
    }

    @Override
    public void clear() {
        sectionPosition = 0;
        listPosition = 0;
        sections.clear();
        byListPosition.clear();

        super.clear();
    }

    @Override
    public void add( ListItem item ) {
        if ( item instanceof ListHeaderItem) {
            String label = item.toString();
            if ( item instanceof PinnedListHeaderItem ) {
                PinnedListHeaderItem< HEADER_MODEL, HEADER_VIEW_HOLDER > header =
                        ( PinnedListHeaderItem< HEADER_MODEL, HEADER_VIEW_HOLDER > ) item;
                label = header.getLabel();
            }
            SectionItem sectionItem = new SectionItem( label, sectionPosition, listPosition );
            sections.add( sectionItem );
            sectionPosition++;
        }

        int lastSectionIndex = sections.size() - 1;
        SectionItem lastSectionItem = lastSectionIndex >= 0 && lastSectionIndex< sections.size() ?
                sections.get( sections.size() - 1 ) : null;
        byListPosition.put(listPosition, lastSectionItem);

        listPosition++;

        super.add(item);
    }

    @TargetApi( 11 )
    public void addAll(Collection<? extends ListItem> collection) {
        if ( collection != null ) {
            for (ListItem item : collection) {
                this.add(item);
            }
        }
    }

    public void addAll(ListItem... items) {
        if ( items != null ) {
            for (ListItem item : items) {
                this.add(item);
            }
        }
    }

}
