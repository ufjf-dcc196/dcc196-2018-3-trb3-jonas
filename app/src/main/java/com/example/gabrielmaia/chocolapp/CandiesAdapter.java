package com.example.gabrielmaia.chocolapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import model.Candy;

public class CandiesAdapter extends RecyclerView.Adapter<CandiesAdapter.CandiesViewHolder>{

    private ArrayList<Candy> candies;
    private Candy currentCandy;

    public static class CandiesViewHolder extends RecyclerView.ViewHolder {
        public TextView candyName;

        public CandiesViewHolder(@NonNull View itemView) {
            super(itemView);

            candyName =  itemView.findViewById(R.id.candy_cv);
        }
    }

    public CandiesAdapter(ArrayList<Candy> candies) {
        this.candies = candies;
    }

    @NonNull
    @Override
    public CandiesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.candy_cardview, viewGroup, false);
        return new CandiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CandiesViewHolder viewHolder, int i) {
        currentCandy = this.candies.get(i);

        viewHolder.candyName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CandyDetailsActivity.class);
                intent.putExtra("ID", currentCandy.getId());
                v.getContext().startActivity(intent);
            }
        });

        viewHolder.candyName.setText(currentCandy.getType() + " de " + currentCandy.getName());
    }

    @Override
    public int getItemCount() {
        return this.candies.size();
    }

    public void addCandy(Candy event){
        this.candies.add(event);
        notifyItemInserted(getItemCount());
    }
}
