package com.example.gabrielmaia.chocolapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import persistence.CandyDAO;

public class CandiesFragment extends android.support.v4.app.Fragment {

    private static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public static RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.candies_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.candies_rv);
        layoutManager = new LinearLayoutManager(getContext());

        CandyDAO candyDAO = new CandyDAO(this.getContext());
        adapter = new CandiesAdapter(candyDAO.getAll());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
