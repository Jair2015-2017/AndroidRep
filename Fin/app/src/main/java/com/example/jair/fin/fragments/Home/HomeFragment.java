package com.example.jair.fin.fragments.Home;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jair.fin.R;
import com.example.jair.fin.dao.FinDao;
import com.example.jair.fin.entity.olap.TranOnMonth;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    FinDao finDao;

    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        finDao = new FinDao(getActivity());

        TranOnMonth tom = finDao.getLastTOM();
        double assets = tom.getAssets();
        double expenses = tom.getExpenses();
        double remaining = tom.getRemaining();

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView assetsView=(TextView) view.findViewById(R.id.starting_value);
        assetsView.setText(String.valueOf(assets));

        TextView expensesView=(TextView) view.findViewById(R.id.spenden_value);
        expensesView.setText(String.valueOf(expenses));

        TextView remainingView=(TextView) view.findViewById(R.id.current_value);
        remainingView.setText(String.valueOf(remaining));


        return view;
    }


}
