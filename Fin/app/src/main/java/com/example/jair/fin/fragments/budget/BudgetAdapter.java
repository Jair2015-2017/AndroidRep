package com.example.jair.fin.fragments.budget;

import android.content.Context;
import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.jair.fin.R;

import java.util.List;

/**
 * Created by Jair on 2/13/2017.
 */

public class BudgetAdapter extends ArrayAdapter<String> {
    public int layout;
    public BudgetAdapter(Context context, int resource, String[] objects) {

        super(context, resource, objects);
        layout=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            LayoutInflater inflater= LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout,parent,false);
            ViewHolder viewHolder= new ViewHolder();
            viewHolder.budgetName = (TextView) convertView.findViewById(R.id.budget_name_item);
            viewHolder.budget = (TextView) convertView.findViewById(R.id.budget_amount_item);
            viewHolder.edit = (Button) convertView.findViewById(R.id.edit_budget);
            viewHolder.delete = (Button) convertView.findViewById(R.id.delete_budget);
            convertView.setTag(viewHolder);


        return super.getView(position, convertView, parent);
    }

    public class ViewHolder{
        TextView budgetName;
        TextView budget;
        Button edit;
        Button delete;
    }
}

