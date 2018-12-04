package com.example.gabrielmaia.chocolapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import model.Candy;
import persistence.CandyDAO;

public class CandyDetailsActivity extends AppCompatActivity {

    private TextView name;
    private TextView type;
    private TextView price;
    private TextView description;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_candy_details);

        Bundle extras = getIntent().getExtras();
        String candyId = extras.getString("CANDY_ID");

        Candy candy = CandyDAO.read(Integer.parseInt(candyId));

        if(candy != null){
            name = findViewById(R.id.c_edit_name);
            type = findViewById(R.id.c_edit_type);
            price = findViewById(R.id.c_edit_price);
            description = findViewById(R.id.c_edit_description);
            save = findViewById(R.id.c_save);

            name.setText(candy.getName());
            type.setText(candy.getType());
            price.setText(candy.printPrice());
            description.setText(candy.getDescription());
        }
        else {
            Toast.makeText(getApplicationContext(), " Não foi possível encontrar o doce " + candyId + ".", Toast.LENGTH_SHORT).show();
        }
    }
}
