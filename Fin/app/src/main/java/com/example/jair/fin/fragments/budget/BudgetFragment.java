package com.example.jair.fin.fragments.budget;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;

import com.example.jair.fin.R;
import com.example.jair.fin.dao.FinDao;
import com.example.jair.fin.dto.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class BudgetFragment extends Fragment {
    ListView listview;
    List<Category> categories;
    FinDao findao;
    static public EditBudgetDialog editBudgetDialog;


    public BudgetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_budgets, container, false);
        listview = (ListView) view.findViewById(R.id.listview);
        TextView remView = (TextView)view.findViewById(R.id.remaining_for_budget);
        findao=new FinDao(getActivity());
        double assets = findao.getLastTOM().getAssets();
        double budgets = findao.totalBudget();
        remView.setText(String.valueOf((assets-budgets)));
        populateListView();



        return view;
    }

    private void populateListView() {

        //String[] items = {"blu","red","yello","green"};
        findao = new FinDao(getActivity());
        categories = findao.getAllCategories();
        List<String> categoriesWithBudgets=new ArrayList<>();


        for (Category category : categories) {
            String name = category.getBudget_name();
            if (!name.equals("no budget")) {
                categoriesWithBudgets.add(category.getBudget_name());
            }

        }


        ArrayAdapter adapter= new ArrayAdapter(getActivity(),R.layout.items,categoriesWithBudgets);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editBudgetDialog = new EditBudgetDialog();

                String cat =  String.valueOf(parent.getItemAtPosition(position));
                editBudgetDialog.category = findao.getCategoryByBudget(cat);

                editBudgetDialog.show(getActivity().getSupportFragmentManager(),"edit_budget");

            }
        });

    }




}

/*
 listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

               // Toast.makeText(getActivity(), "long"+parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                EditBudgetDialog dialog= new EditBudgetDialog();

                String cat =  String.valueOf(parent.getItemAtPosition(position));
                dialog.category = findao.getCategoryByBudget(cat);

                dialog.show(getActivity().getSupportFragmentManager(),"edit_budget");

                return false;
            }
        });

 */