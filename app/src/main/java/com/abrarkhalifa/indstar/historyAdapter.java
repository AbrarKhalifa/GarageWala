package com.abrarkhalifa.indstar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abrarkhalifa.indstar.model.DataModelHistory;

import java.util.ArrayList;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.myViewHolder> {
    ArrayList<DataModelHistory> arrayList;

    public historyAdapter(ArrayList<DataModelHistory> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_design,parent,false);
       return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.garageNameHistory.setText(arrayList.get(position).getGarageName());

        holder.serviceOneNameHistory.setText(arrayList.get(position).getGarageServiceOne());
        holder.serviceTwoNameHistory.setText(arrayList.get(position).getGarageServiceTwo());
        holder.serviceThreeNameHistory.setText(arrayList.get(position).getGarageServiceThree());

        holder.servicePriceOneHistory.setText(arrayList.get(position).getGarageServicePriceOne());
        holder.servicePriceTwoHistory.setText(arrayList.get(position).getGarageServicePriceTwo());
        holder.servicePriceThreeHistory.setText(arrayList.get(position).getGarageServicePriceThree());

        holder.totalPriceHistory.setText(arrayList.get(position).getGarageServiceTotalPrice());



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView garageNameHistory,
                serviceOneNameHistory,serviceTwoNameHistory,serviceThreeNameHistory,
                servicePriceOneHistory,servicePriceTwoHistory,servicePriceThreeHistory,
                totalPriceHistory,
        bycash,serviceHistory,priceHistory;

        ImageView garagePhoto,ratings;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            garageNameHistory = (itemView).findViewById(R.id.garageNameHistory);

            serviceOneNameHistory = (itemView).findViewById(R.id.serviceOneNameHistory);
            serviceTwoNameHistory = (itemView).findViewById(R.id.serviceTwoNameHistory);
            serviceThreeNameHistory = (itemView).findViewById(R.id.serviceThreeNameHistory);

            servicePriceOneHistory = (itemView).findViewById(R.id.servicePriceOneHistory);
            servicePriceTwoHistory = (itemView).findViewById(R.id.servicePriceTwoHistory);
            servicePriceThreeHistory = (itemView).findViewById(R.id.servicePriceThreeHistory);

            totalPriceHistory = (itemView).findViewById(R.id.totalPriceHistory);


            ratings = (itemView).findViewById(R.id.strOne);
            ratings = (itemView).findViewById(R.id.strTwo);
            ratings = (itemView).findViewById(R.id.strThree);
            ratings = (itemView).findViewById(R.id.strFour);
            ratings = (itemView).findViewById(R.id.strFive);

            garagePhoto = (itemView).findViewById(R.id.imgMerchantHistory);

            bycash = (itemView).findViewById(R.id.bycashHistory);
            serviceHistory = (itemView).findViewById(R.id.serviceHistory);
            priceHistory = (itemView).findViewById(R.id.priceHistory);




        }
    }
}
