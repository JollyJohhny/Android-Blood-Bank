package com.example.androidbloodbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<String> {

    Context mContext;
    ArrayList<ListType> Profiles;

    public ListAdapter(Context context, ArrayList<ListType> profiles) {
        super(context, R.layout.listview_item);
        this.Profiles = profiles;
        mContext = context;


    }

    @Override
    public int getCount() {
        return Profiles.size();
    }

    @NonNull
    @Override
   /* public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_item, parent, false);
            mViewHolder.mFlag = (ImageView) convertView.findViewById(R.id.imageView);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.mFlag.setImageResource(flags[position]);
        mViewHolder.mName.setText(names[position]);

        return convertView;
    }*/


    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.listview_item, parent, false);

        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtContact = convertView.findViewById(R.id.txtContact);


        txtName.setText("Name: " +Profiles.get(position).getName());
        txtContact.setText("Contact: " + Profiles.get(position).getContact());


        return convertView;
    }



    static class ViewHolder {
        ImageView contactImage;
        TextView contactName;
        TextView contactNumber;
        TextView contactEmail;
    }
}
