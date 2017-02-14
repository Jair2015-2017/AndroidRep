package com.example.jair.fin.fragments.budget;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jair.fin.R;
import com.example.jair.fin.dto.Category;

public class EditBudgetDialog extends DialogFragment {


    public EditBudgetDialog() {
        // Required empty public constructor
    }

    public Category category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_budget, container, false);
        TextView titleView=(TextView) view.findViewById(R.id.budget_name_edit_title);
        EditText budgetv =(EditText) view.findViewById(R.id.amount_input_edit_budget);
        EditText budgetname=(EditText) view.findViewById(R.id.name_input_edit_budget);
        TextView cat= (TextView)view.findViewById(R.id.cat_name_edit_budget);

        String title = category.getBudget_name();
        String budget = String.valueOf(category.getBudget());
        String name = category.getCat_name();

        titleView.setText(title);
        budgetv.setText(budget);
        budgetname.setText(title);
        cat.setText(name);

        return view;
    }

}
