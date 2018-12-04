package com.example.gabrielmaia.chocolapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
    public void onBindViewHolder(@NonNull final CandiesViewHolder viewHolder, int i) {
        currentCandy = this.candies.get(i);

        viewHolder.candyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();

                Intent intent = new Intent(v.getContext(), CandyDetailsActivity.class);
                intent.putExtra("CANDY_ID", getCandyId(position));
                v.getContext().startActivity(intent);
            }
        });

        viewHolder.candyName.setText(currentCandy.getName());
        viewHolder.candyType.setText(currentCandy.getType());
        viewHolder.candyPrice.setText(currentCandy.printPrice());
    }

    private String getCandyId(int position) {
        Candy candy = this.candies.get(position);
        return Integer.toString(candy.getId());
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
