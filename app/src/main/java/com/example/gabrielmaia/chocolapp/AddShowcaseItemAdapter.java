package com.example.gabrielmaia.chocolapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.Candy;

public class AddShowcaseItemAdapter extends RecyclerView.Adapter<AddShowcaseItemAdapter.CandiesViewHolder>{

    private ArrayList<Candy> candies;
    private Candy currentCandy;

    public static class CandiesViewHolder extends RecyclerView.ViewHolder {
        public TextView candyName;
        public TextView candyPrice;
        public TextView candyType;
        public CardView candyCard;

        public CandiesViewHolder(@NonNull View itemView) {
            super(itemView);

            candyName =  itemView.findViewById(R.id.candy_name_cv);
            candyPrice =  itemView.findViewById(R.id.candy_price_cv);
            candyType =  itemView.findViewById(R.id.candy_type_cv);
            candyCard = itemView.findViewById(R.id.candy_card);
        }
    }

    public AddShowcaseItemAdapter(ArrayList<Candy> candies) {
        this.candies = candies;
    }

    @NonNull
    @Override
    public CandiesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.candy_cardview, viewGroup, false);
        return new CandiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CandiesViewHolder viewHolder, int i) {
        currentCandy = this.candies.get(i);

        viewHolder.candyName.setText(currentCandy.getName());
        viewHolder.candyType.setText(currentCandy.getType());
        viewHolder.candyPrice.setText(currentCandy.printPrice());

        viewHolder.candyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int position = viewHolder.getAdapterPosition();
                AddShowcaseItemActivity.candySelectedId = getCandyId(position);
                Toast.makeText(v.getContext(), "Doce selecionado!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getCandyId(int position) {
        Candy candy = this.candies.get(position);
        return Integer.toString(candy.getId());
    }

    @Override
    public int getItemCount() {
        return this.candies.size();
    }

}
