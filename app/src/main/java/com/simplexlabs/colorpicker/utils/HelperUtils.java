package com.simplexlabs.colorpicker.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Bill on 2017-04-05.
 */

public class HelperUtils {
    /**
     * Shows a {@link Toast} on the UI thread.
     *
     * @param text The message to show
     */
    public static void showToast(final String text, final Activity activity) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
