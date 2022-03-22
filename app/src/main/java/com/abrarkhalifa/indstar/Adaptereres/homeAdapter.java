package com.abrarkhalifa.indstar.Adaptereres;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.abrarkhalifa.indstar.R;
import com.abrarkhalifa.indstar.countDown;
import com.abrarkhalifa.indstar.map;
import com.abrarkhalifa.indstar.model.DataModelHome;

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.homie> {
    ArrayList<DataModelHome> dataHolder;
    Class<countDown> context;

    public homeAdapter(ArrayList<DataModelHome> dataHolder) {
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public homie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_of_garage_home,parent,false);
        return new homie(view);

    }

    @Override
    public void onBindViewHolder(@NonNull homie holder, int position) {
        holder.garagename.setText(dataHolder.get(position).getGarageName());

        holder.garagePhoto.setImageResource(dataHolder.get(position).getImage());
        holder.sendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "Sending Request", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), countDown.class);
                v.getContext().startActivity(intent);

            }
        });

        holder.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), map.class);
                v.getContext().startActivity(intent);
            }
        });


        holder.map.setImageResource(dataHolder.get(position).getMap());
        holder.chat.setImageResource(dataHolder.get(position).getChat());
        holder.downArrow.setImageResource(dataHolder.get(position).getDownArrow());


    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }


    class homie extends RecyclerView.ViewHolder{

        TextView garagename;
        ImageView garagePhoto,rating,map,chat,downArrow;
        Button sendReq;
        public homie(@NonNull View itemView) {
            super(itemView);
            garagePhoto = (itemView).findViewById(R.id.img_garage_pic);
            garagename = (itemView).findViewById(R.id.tv_garage_name);
            rating = (itemView).findViewById(R.id.str1);
            rating = (itemView).findViewById(R.id.str2);
            rating = (itemView).findViewById(R.id.str3);
            rating = (itemView).findViewById(R.id.str4);
            rating = (itemView).findViewById(R.id.str5);
            sendReq = (itemView).findViewById(R.id.btn_sendReq);
            map = (itemView).findViewById(R.id.img_map);
            chat = (itemView).findViewById(R.id.img_chat);
            downArrow = (itemView).findViewById(R.id.img_downArrow);
        }
    }
}

