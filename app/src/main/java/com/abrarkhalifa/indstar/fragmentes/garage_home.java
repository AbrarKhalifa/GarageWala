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
import com.abrarkhalifa.indstar.model.DataModelHome;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link garage_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class garage_home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    RecyclerView recyclerViewHistory;
    ArrayList<DataModelHome> dataHolder;

    public garage_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment garage_home.
     */
    // TODO: Rename and change types and number of parameters
    public static garage_home newInstance(String param1, String param2) {
        garage_home fragment = new garage_home();
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
        View view = inflater.inflate(R.layout.fragment_garage_home, container, false);
        recyclerViewHistory= view.findViewById(R.id.recView);
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(getContext()));

        dataHolder = new ArrayList<>();
        DataModelHome cli1 = new DataModelHome(R.drawable.mechanic_img,"Aasif Services"
                ,R.drawable.ic_rating,R.drawable.ic_google_map,R.drawable.ic_chat,R.drawable.ic_down_arrow);
        dataHolder.add(cli1);

        DataModelHome cli2 = new DataModelHome(R.drawable.mechanic_img2,"Devansh Services"
                ,R.drawable.ic_rating,R.drawable.ic_google_map,R.drawable.ic_chat,R.drawable.ic_down_arrow);
        dataHolder.add(cli2);

        DataModelHome cli3 = new DataModelHome(R.drawable.mechanic_img3,"Aamir Services"
                ,R.drawable.ic_rating,R.drawable.ic_google_map,R.drawable.ic_chat,R.drawable.ic_down_arrow);
        dataHolder.add(cli3);
        DataModelHome cli4 = new DataModelHome(R.drawable.mechanic_img4,"Aamir Services"
                ,R.drawable.ic_rating,R.drawable.ic_google_map,R.drawable.ic_chat,R.drawable.ic_down_arrow);
        dataHolder.add(cli4);

        recyclerViewHistory.setAdapter(new homeAdapter(dataHolder));
        return view;
    }
}