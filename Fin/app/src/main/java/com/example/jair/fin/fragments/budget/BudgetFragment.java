package com.example.jair.fin.fragments.budget;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.Toast;

import com.example.jair.fin.R;
import com.example.jair.fin.dao.FinDao;
import com.example.jair.fin.dto.Category;

import java.util.ArrayList;
import java.util.List;

import static com.example.jair.fin.R.id.listview;

/**
 * A simple {@link Fragment} subclass.
 */

public class BudgetFragment extends Fragment {
    ListView listview;
    List<Category> categories;
    FinDao findao;

    public BudgetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_budgets, container, false);
        listview = (ListView) view.findViewById(R.id.listview);
        populateListView();
        return view;
    }

    private void populateListView() {

        //String[] items = {"blu","red","yello","green"};
        findao = new FinDao(getActivity());
        categories = findao.getAllCategories();
        int size=categories.size();
        String[] catnames= new String[size];
        int i = 0;

        for (Category c: categories){
            catnames[i]=c.getBudget_name();
            i++;
        }


        ArrayAdapter adapter= new ArrayAdapter(getActivity(),R.layout.items,catnames);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //Toast.makeText(getActivity(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
