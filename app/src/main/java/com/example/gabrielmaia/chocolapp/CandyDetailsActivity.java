package com.example.gabrielmaia.chocolapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        final String candyId = extras.getString("CANDY_ID");

        CandyDAO candyDAO = new CandyDAO(this);
        final Candy candy = candyDAO.read(Integer.parseInt(candyId));

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

            save.setOnClickListener(new View.OnClickListener() {
                Double newRealPrice = null;

                @Override
                public void onClick(View v) {
                    String newName = name.getText().toString();
                    String newType = type.getText().toString();
                    String newPrice = price.getText().toString();
                    String newDescription = description.getText().toString();

                    try{
                        newRealPrice = Double.parseDouble(newPrice);
                    } catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(), "Insira um preço válido!", Toast.LENGTH_SHORT).show();
                    }

                    if (newName == null || newName.isEmpty())
                        Toast.makeText(getApplicationContext(), "Insira um nome válido!", Toast.LENGTH_SHORT).show();
                    else if (newType == null || newType.isEmpty())
                        Toast.makeText(getApplicationContext(), "Insira um tipo válido!", Toast.LENGTH_SHORT).show();
                    else if (newDescription == null || newDescription.isEmpty())
                        Toast.makeText(getApplicationContext(), "Insira uma descrição válida!", Toast.LENGTH_SHORT).show();
                    else{
                        candy.setName(newName);
                        candy.setType(newType);
                        candy.setRealPrice(newRealPrice);
                        candy.setDescription(newDescription);

                        CandyDAO candyDAO = new CandyDAO(v.getContext());
                        candyDAO.update(candy);

                        Toast.makeText(getApplicationContext(), candy.getType() + " de " + candy.getName() + " atualizado!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), " Não foi possível encontrar o doce " + candyId + ".", Toast.LENGTH_SHORT).show();
        }
    }
}
