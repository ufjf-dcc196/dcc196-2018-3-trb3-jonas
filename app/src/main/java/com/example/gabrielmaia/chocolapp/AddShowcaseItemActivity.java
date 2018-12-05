package com.example.gabrielmaia.chocolapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import model.ShowcaseItem;
import persistence.CandyDAO;
import persistence.ShowcaseItemDAO;

public class AddShowcaseItemActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static String candySelectedId;
    private SeekBar quantitySeekBar;
    private TextView quantity;
    private Button save;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_showcase_item);

        recyclerView = findViewById(R.id.showcase_options_rv);
        layoutManager = new LinearLayoutManager(this);

        CandyDAO candyDAO = new CandyDAO(this);
        adapter = new AddShowcaseItemAdapter(candyDAO.getAll());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        quantity = findViewById(R.id.s_quantity);
        save = findViewById(R.id.s_save);
        quantitySeekBar = findViewById(R.id.s_quantity_seek_bar);

        quantitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                quantity.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemQuantity = quantity.getText().toString();
                if (itemQuantity == null || itemQuantity.isEmpty())
                    Toast.makeText(getApplicationContext(), "Insira uma quantidade válida!", Toast.LENGTH_SHORT).show();
                if (candySelectedId == null)
                    Toast.makeText(getApplicationContext(), "Selecione o tipo de doce", Toast.LENGTH_SHORT).show();
                else {
                    ShowcaseItemDAO showcaseItemDAO = new ShowcaseItemDAO(v.getContext());
                    showcaseItemDAO.create(new ShowcaseItem(Integer.parseInt(candySelectedId), Integer.parseInt(itemQuantity)));

                    ShowcaseItem last = showcaseItemDAO.getLast();
                    ((ShowcaseAdapter) ShowcaseFragment.adapter).addShowcaseItem(last);

                    Toast.makeText(getApplicationContext(), "Novo estoque adicionado!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
