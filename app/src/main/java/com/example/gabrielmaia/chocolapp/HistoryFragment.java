package com.example.gabrielmaia.chocolapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import model.History;
import persistence.HistoryDAO;

public class HistoryFragment extends android.support.v4.app.Fragment {

    private static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static Context context;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    public static RecyclerView.Adapter getAdapter() {
        if(adapter == null){
            HistoryDAO historyDAO = new HistoryDAO(context);
            adapter = new HistoryAdapter(historyDAO.getAll());
        }
        return adapter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.history_fragment, container, false);

        recyclerView = rootView.findViewById(R.id.history_rv);
        layoutManager = new LinearLayoutManager(getContext());

        HistoryDAO historyDAO = new HistoryDAO(this.getContext());
        adapter = new HistoryAdapter(historyDAO.getAll());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        return rootView;
    }
}
