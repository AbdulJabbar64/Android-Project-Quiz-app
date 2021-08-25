package com.android.app.quizapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.android.app.quizapp.R;
import com.android.app.quizapp.quizdb.Category;

import java.util.List;

public class CategoriesAdapter extends BaseAdapter {
    List<Category> categories;

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setCategories(List<Category> list){
        this.categories= list;
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.category_item,null);
        TextView title = convertView.findViewById(R.id.name);
        TextView subtitle = convertView.findViewById(R.id.subtitle);

        title.setText(categories.get(position).getTitle());
        subtitle.setText(categories.get(position).getDescription());
        return convertView;
    }
}
