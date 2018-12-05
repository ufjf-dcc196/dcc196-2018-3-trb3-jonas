package com.example.gabrielmaia.chocolapp;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import model.Candy;
import model.History;
import model.ShowcaseItem;
import persistence.CandyDAO;
import persistence.HistoryDAO;
import persistence.ShowcaseItemDAO;

public class ShowcaseAdapter extends RecyclerView.Adapter<ShowcaseAdapter.ShowcaseViewHolder> {

    private ArrayList<ShowcaseItem> showcase;
    private ShowcaseItem currentItem;

    public ShowcaseAdapter(ArrayList<ShowcaseItem> showcase) {
        this.showcase = showcase;
    }

    public static class ShowcaseViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemType;
        public TextView itemQuantity;
        public TextView remaining;
        public CardView showcaseItemCard;
        public ImageView done;
        public ConstraintLayout cardConstraint;

        public ShowcaseViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName =  itemView.findViewById(R.id.item_name);
            itemType =  itemView.findViewById(R.id.item_type);
            itemQuantity =  itemView.findViewById(R.id.item_quantity);
            showcaseItemCard = itemView.findViewById(R.id.showcase_item_card);
            done = itemView.findViewById(R.id.done_check);
            remaining = itemView.findViewById(R.id.items_remaining_lbl);
            cardConstraint = itemView.findViewById(R.id.card_constraint);
            done.setVisibility(View.INVISIBLE);
        }
    }
    @NonNull
    @Override
    public ShowcaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.showcase_item_cardview, viewGroup, false);
        return new ShowcaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowcaseViewHolder viewHolder, int i) {
        currentItem = this.showcase.get(i);

        if(currentItem.getQuantity() == 0){
            viewHolder.itemQuantity.setVisibility(View.GONE);
            viewHolder.remaining.setVisibility(View.GONE);
            viewHolder.done.setVisibility(View.VISIBLE);
            viewHolder.cardConstraint.setBackgroundColor(viewHolder.done.getResources().getColor(R.color.done));
        }

        viewHolder.showcaseItemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                ShowcaseItem clicked = getShowcaseItem(position);

                ShowcaseItemDAO showcaseItemDAO = new ShowcaseItemDAO(v.getContext());

                if(clicked.getQuantity() >= 1){
                    clicked.setQuantity(clicked.getQuantity() - 1);
                    showcaseItemDAO.update(clicked);
                    notifyDataSetChanged();

                    HistoryDAO historyDAO = new HistoryDAO(v.getContext());
                    historyDAO.create(clicked.getCandyId(), v.getContext());

                    History last = historyDAO.getLast();
                    ((HistoryAdapter) HistoryFragment.getAdapter()).addHistory(last);
                }

            }
        });

        CandyDAO candyDAO = new CandyDAO(viewHolder.itemName.getContext());
        Candy candy = candyDAO.read(currentItem.getCandyId());
        viewHolder.itemName.setText(candy.getName());
        viewHolder.itemType.setText(candy.getType());
        viewHolder.itemQuantity.setText(String.valueOf(currentItem.getQuantity()));
    }

    private ShowcaseItem getShowcaseItem(int position) {
        ShowcaseItem s = this.showcase.get(position);
        return s;
    }

    @Override
    public int getItemCount() {
        return this.showcase.size();
    }
}
