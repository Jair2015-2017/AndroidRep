package com.example.jair.fin.fragments.budget;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jair.fin.R;
import com.example.jair.fin.dao.FinDao;
import com.example.jair.fin.dto.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddBudgetDialog extends DialogFragment {

    Spinner spinner;
    ArrayAdapter<String> adapter;
    List<String> categorynames;
    List<Category> categoryList;
    Category category;
    String array;
    FinDao finDao;
    public AddBudgetDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_budget_dialog, container, false);

        /* if (array != null) {
            TextView name = (TextView) view.findViewById(R.id.add_expense_name_input);
            name.setText(array);

        }*/

        finDao = new FinDao(getActivity());
        categoryList = finDao.getAllCategories();
        categorynames = new ArrayList<>();
        for (Category category : categoryList) {
            String name = category.getCat_name();
            categorynames.add(name);
        }
        spinner = (Spinner) view.findViewById(R.id.budget_category_spinner);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_layout, R.id.txt, categorynames);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String name = String.valueOf(parent.getItemAtPosition(position));
                category = finDao.getCategoryByName(name);
                //Toast.makeText(getActivity(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }

        });

    return view;
    }

    public Category getCategory() {
        return category;
    }

}
