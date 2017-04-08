package com.simplexlabs.colorpicker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.simplexlabs.colorpicker.components.ColorListViewAdapter;
import com.simplexlabs.colorpicker.helperClasses.ColorModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private ArrayList<ColorModel> colorModels;
    private static ColorListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        adapter = new ColorListViewAdapter(colorModels, getApplicationContext());

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        colorModels = new ArrayList<>();

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.END);

        adapter = new ColorListViewAdapter(colorModels, getApplicationContext());

        mDrawerList.setAdapter(adapter);

        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, CameraFragment.newInstance())
                    .commit();
        }
    }

    public void updateListView(HashMap<ColorModel, CameraFragment.MutableInt> mostCommonColors) {
        colorModels.clear();
        ColorModel colorModelTemp;

        Set<String> colorNameSet = new HashSet();

        Iterator it = mostCommonColors.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            colorModelTemp = (ColorModel) pair.getKey();
            if (colorModelTemp.getColorName() != "Black" && colorNameSet.add(colorModelTemp.getColorName())) {
                colorModels.add((ColorModel) pair.getKey());
            }

            it.remove(); // avoids a ConcurrentModificationException
        }

        adapter.notifyDataSetChanged();
        mDrawerLayout.openDrawer(Gravity.RIGHT);
    }
}
