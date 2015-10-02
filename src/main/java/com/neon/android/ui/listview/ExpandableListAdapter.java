package com.neon.android.ui.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;

import com.neon.android.ui.R;
import com.neon.android.ui.listview.model.ListHeaderItem;
import com.neon.android.ui.listview.model.ListRowItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * example from http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
 */
public class ExpandableListAdapter< HEADER_MODEL, HEADER_VIEW_HOLDER, ROW_MODEL, ROW_VIEW_HOLDER > extends BaseExpandableListAdapter {

    private final LayoutInflater inflater;

    private final List<ListHeaderItem< HEADER_MODEL, HEADER_VIEW_HOLDER >> groups = new ArrayList<>();
    private final Map< Integer, List<ListRowItem< ROW_MODEL, ROW_VIEW_HOLDER > > > groupChildren = new HashMap<>();

    private int groupIndicatorId;
    private int groupIndicatorExpanded = R.drawable.ic_navigation_arrow_drop_up;
    private int groupIndicatorCollapsed = R.drawable.ic_navigation_arrow_drop_down;

    public ExpandableListAdapter( Context context ) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ExpandableListAdapter( Context context, int groupIndicatorId ) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupIndicatorId = groupIndicatorId;
    }

    public ExpandableListAdapter( Context context, int groupIndicatorId, int groupIndicatorExpanded, int groupIndicatorCollapsed) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupIndicatorId = groupIndicatorId;
        this.groupIndicatorExpanded = groupIndicatorExpanded;
        this.groupIndicatorCollapsed = groupIndicatorCollapsed;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List< ListRowItem< ROW_MODEL, ROW_VIEW_HOLDER > > items = groupChildren.get(groupPosition);
        return items != null ? items.size() : 0;
    }

    @Override
    public ListHeaderItem< HEADER_MODEL, HEADER_VIEW_HOLDER > getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public ListRowItem< ROW_MODEL, ROW_VIEW_HOLDER > getChild(int groupPosition, int childPosition) {
        return groupChildren.get( groupPosition ).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View result = convertView;

        // get rendering item
        ListHeaderItem< HEADER_MODEL, HEADER_VIEW_HOLDER > item = groups.get(groupPosition);

        RowItemHolder< HEADER_MODEL, HEADER_VIEW_HOLDER > viewHolder = item.getViewHolder();
        if ( viewHolder != null ) {
            if ( result == null ) {
                // create view to render
                result = inflater.inflate(viewHolder.getLayout(item), parent, false);
                HEADER_VIEW_HOLDER header_view_holder = viewHolder.onCreateView(result);
                result.setTag( header_view_holder );
            }
            viewHolder.fill((HEADER_VIEW_HOLDER) result.getTag(), item );
        }

        ImageView groupIndicator = (ImageView) result.findViewById( groupIndicatorId );
        if ( groupIndicator != null ) {
            if ( getChildrenCount( groupPosition ) > 0 ) {
                groupIndicator.setVisibility( View.VISIBLE );
                groupIndicator.setImageResource( isExpanded ? groupIndicatorExpanded : groupIndicatorCollapsed );
            } else {
                groupIndicator.setVisibility( View.GONE );
            }
        }

        return result;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View result = convertView;

        // get rendering item
        ListRowItem< ROW_MODEL, ROW_VIEW_HOLDER > item = groupChildren.get(groupPosition).get(childPosition);

        RowItemHolder< ROW_MODEL, ROW_VIEW_HOLDER > viewHolder = item.getViewHolder();
        if ( viewHolder != null ) {
            if ( result == null ) {
                // create view to render
                result = inflater.inflate(viewHolder.getLayout(item), parent, false);
                ROW_VIEW_HOLDER row_view_holder = viewHolder.onCreateView(result);
                result.setTag( row_view_holder );
            }
            viewHolder.fill((ROW_VIEW_HOLDER) result.getTag(), item);
        }

        return result;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void clear() {
        groups.clear();
        groupChildren.clear();
    }

    public void addGroup( ListHeaderItem< HEADER_MODEL, HEADER_VIEW_HOLDER > item ) {
        groups.add( item );
        groupChildren.put( groups.size(), new ArrayList<ListRowItem< ROW_MODEL, ROW_VIEW_HOLDER > >() );
    }

    public void addGroupChild( ListHeaderItem< HEADER_MODEL, HEADER_VIEW_HOLDER > item, ListRowItem< ROW_MODEL, ROW_VIEW_HOLDER > child ) {
        int indexOf = groups.indexOf(item);
        List<ListRowItem<ROW_MODEL, ROW_VIEW_HOLDER>> listRowItems = groupChildren.get(indexOf);
        if ( listRowItems == null ) {
            listRowItems = new ArrayList<>();
            groupChildren.put( indexOf, listRowItems );
        }
        listRowItems.add( child );
    }

}
