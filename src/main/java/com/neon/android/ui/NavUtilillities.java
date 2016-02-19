package com.neon.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;


public final class NavUtilillities {

    private NavUtilillities() {

    }

    public static void navigateUp( Activity activity ) {
        Intent upIntent = NavUtils.getParentActivityIntent( activity );
        if (NavUtils.shouldUpRecreateTask( activity, upIntent)) {
            TaskStackBuilder.create(activity)
                    .addNextIntentWithParentStack(upIntent)
                    .startActivities();
        } else {
            NavUtils.navigateUpTo(activity, upIntent);
        }
    }    
    
}
