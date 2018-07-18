package com.andtechno.singh.psheet;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by retisense on 18/07/18.
 */

public class customListAdapter extends ArrayAdapter<String> {private final Activity context;
    private final ArrayList<String> itemname;


    public customListAdapter(Activity context, ArrayList<String> itemname, ArrayList<String> moreDetails) {
        super(context, R.layout.listview_item, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;

    }
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_item, null,true);


//        TextView extratxt = (TextView) rowView.findViewById(R.id.labe2);
//        TextView name = (TextView) rowView.findViewById(R.id.label);

      //  name.setText();
//        extratxt.setText("");
        return rowView;

    };
}
