package com.jordicros997.dsasegoncontrol;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordi on 29/05/2018.
 */

public class Adapter extends BaseAdapter {
    protected Context context;
    protected List<LlistaFollowers> followers;
    public Adapter(Activity context, List<LlistaFollowers> followers)
    {
        this.context = context;
        this.followers = followers;
    }
    @Override
    public int getCount() {
        return followers.size();
    }
    public void clear(){
        followers.clear();
    }
    public void addAll(ArrayList<LlistaFollowers> grups)
    {
        for(int i=0;i<grups.size();i++){
            this.followers.add(grups.get(i));
        }
    }
    @Override
    public Object getItem(int i) {
        return followers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view = LayoutInflater.from(context).
                    inflate(R.layout.element_listview,viewGroup, false);
        }
        LlistaFollowers ll =(LlistaFollowers) getItem(i);
        TextView title = view.findViewById(R.id.textView4);
        title.setText(ll.getUsername());
        ImageView img = view.findViewById(R.id.imageView2);
        Picasso.get().load(ll.getImgURL()).into(img);
        return view;
    }
}
