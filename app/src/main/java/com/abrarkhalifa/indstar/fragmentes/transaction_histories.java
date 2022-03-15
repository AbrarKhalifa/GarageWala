package com.abrarkhalifa.indstar.fragmentes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abrarkhalifa.indstar.Adaptereres.homeAdapter;
import com.abrarkhalifa.indstar.R;
import com.abrarkhalifa.indstar.historyAdapter;
import com.abrarkhalifa.indstar.model.DataModelHistory;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link transaction_histories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class transaction_histories extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<DataModelHistory> dataHistory;
    public transaction_histories() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment transaction_histories.
     */
    // TODO: Rename and change types and number of parameters
    public static transaction_histories newInstance(String param1, String param2) {
        transaction_histories fragment = new transaction_histories();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_histories, container, false);
        recyclerView = view.findViewById(R.id.recViewHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataHistory = new ArrayList<>();

        DataModelHistory d1 = new DataModelHistory("Ak Services","Carborator Filter","Oil Changing","Cluth Repair","500","210","90","800");
        dataHistory.add(d1);

        DataModelHistory d2 = new DataModelHistory("Devansh Auto ","Break Changing","Puncher","HeadLight","900","100","1500","2500");
        dataHistory.add(d2);

        DataModelHistory d3 = new DataModelHistory("Aasif Services","Color","Stickering","light modified","5000","2000","2500","9500");
        dataHistory.add(d3);

        DataModelHistory d4 = new DataModelHistory("Ak Services","Carborator Filter","Oil Changing","Cluth Repair","500","210","90","800");
        dataHistory.add(d4);

        DataModelHistory d5 = new DataModelHistory("Devansh Auto ","Break Changing","Puncher","HeadLight","900","100","1500","2500");
        dataHistory.add(d5);

        DataModelHistory d6 = new DataModelHistory("Aasif Services","Color","Stickering","light modified","5000","2000","2500","9500");
        dataHistory.add(d6);



        recyclerView.setAdapter(new historyAdapter(dataHistory));
        return  view;
    }
}