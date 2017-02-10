package com.example.jair.fin.fragments.Home;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jair.fin.R;
import com.example.jair.fin.activities.MainActivity;


public class AddEarningDialog extends DialogFragment{

    String array;

    public AddEarningDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_earning_dialog, container, false);
        array = MainActivity.safe;

        if (array != null){
            TextView name = (TextView) view.findViewById(R.id.add_earning_name_input);
            name.setText(array);

        }
        return view;
    }

}
