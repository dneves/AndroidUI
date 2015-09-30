package com.neon.android.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentNavigator {

    private final FragmentManager fragmentManager;

    private final int containerId;

    public FragmentNavigator(FragmentManager fragmentManager, int containerId) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
    }

    public void execute( Fragment target, boolean addToBackStack ) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.setCustomAnimations( R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit );

        fragmentTransaction.replace( containerId, target);
        if ( addToBackStack ) {
            fragmentTransaction.addToBackStack( "" + target.getId() );
        }
        fragmentTransaction.commit();
    }

}
