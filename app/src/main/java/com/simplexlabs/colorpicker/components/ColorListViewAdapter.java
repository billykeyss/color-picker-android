package com.simplexlabs.colorpicker.components;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.simplexlabs.colorpicker.R;
import com.simplexlabs.colorpicker.helperClasses.ColorModel;

import java.util.ArrayList;

/**
 * Created by Bill on 2017-04-08.
 */

public class ColorListViewAdapter extends ArrayAdapter<ColorModel> implements View.OnClickListener {

    private ArrayList<ColorModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView hexCode;
        TextView colorName;
        CircleView circleView;
        RelativeLayout listItemContainer;
    }

    public ColorListViewAdapter(ArrayList<ColorModel> data, Context context) {
        super(context, R.layout.drawer_list_item, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ColorModel colorModels = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.drawer_list_item, parent, false);
            viewHolder.hexCode = (TextView) convertView.findViewById(R.id.hexCodeList);
            viewHolder.colorName = (TextView) convertView.findViewById(R.id.colorName);
            viewHolder.circleView = (CircleView) convertView.findViewById(R.id.listCircle);
            viewHolder.listItemContainer = (RelativeLayout) convertView.findViewById(R.id.listItemContainer);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        if(colorModels.getR()*0.299 + colorModels.getG()*0.587 + colorModels.getB()*0.114 < 186) {
            viewHolder.hexCode.setTextColor(Color.WHITE);
            viewHolder.colorName.setTextColor(Color.WHITE);
        } else {
            viewHolder.hexCode.setTextColor(Color.BLACK);
            viewHolder.colorName.setTextColor(Color.BLACK);
        }

        assert colorModels != null;
        viewHolder.hexCode.setText(colorModels.getHexCode().toUpperCase());
        viewHolder.colorName.setText(colorModels.getColorName());
        viewHolder.circleView.setCircleColor(colorModels.getColor());
        viewHolder.listItemContainer.setBackgroundColor(colorModels.getColor());
        // Return the completed view to render on screen
        return convertView;
    }
}