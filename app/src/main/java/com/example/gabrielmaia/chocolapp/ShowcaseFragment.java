package com.example.gabrielmaia.chocolapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import model.Revenue;
import persistence.RevenueDAO;
import persistence.ShowcaseItemDAO;

public class ShowcaseFragment extends android.support.v4.app.Fragment {

    private static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static TextView revenue;
    public FloatingActionButton fab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.showcase_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.showcase_rv);
        layoutManager = new LinearLayoutManager(getContext());

        ShowcaseItemDAO showcase = new ShowcaseItemDAO(this.getContext());
        adapter = new ShowcaseAdapter(showcase.getAll());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        revenue = rootView.findViewById(R.id.revenue_textview);

        RevenueDAO revenueDAO = new RevenueDAO(rootView.getContext());
        Revenue r = revenueDAO.read();

        revenue.setText(r.printPrice());

        fab = rootView.findViewById(R.id.add_showcase_item);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddShowcaseItemActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        return rootView;
    }
}
