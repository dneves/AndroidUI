package com.neon.android.ui.listview;

import android.support.v4.app.Fragment;

import com.neon.android.ui.FragmentNavigator;
import com.neon.android.ui.listview.model.ListItem;


public abstract class FragmentNavigatorListener< TYPE > implements ListItem.OnItemClickListener< TYPE > {

    private final FragmentNavigator fragmentNavigator;

    public FragmentNavigatorListener(FragmentNavigator fragmentNavigator) {
        this.fragmentNavigator = fragmentNavigator;
    }

    @Override
    public void onClick( TYPE model ) {
        Fragment target = getTarget( model );
        fragmentNavigator.execute( target, isAddToBackStack() );
    }

    protected boolean isAddToBackStack() {
        return true;
    }

    protected abstract Fragment getTarget( TYPE model );
}
