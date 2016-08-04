package com.austincjones.rfy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etName,etIngredients,etDirections;
    Button btnSubmit,btngetdata;
    DatabaseHelpher helpher;
    List<DatabaseModel> dbList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbList= new ArrayList<DatabaseModel>();
        etName = (EditText)findViewById(R.id.etName);
        etIngredients = (EditText)findViewById(R.id.etIngredients);
        etDirections =(EditText)findViewById(R.id.etDirections);
        btnSubmit  =(Button)findViewById(R.id.btnSubmit);
        btngetdata =(Button)findViewById(R.id.btngetdata);
        btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));

               // startActivity(new Intent(MainActivity.this, DetailsActivity.class));

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=etName.getText().toString();
                String ingredients=etIngredients.getText().toString();
                String directions=etDirections.getText().toString();

            if(name.equals("")| ingredients.equals("") ||directions.equals("")){
                Toast.makeText(MainActivity.this,"Please fill all the fields", Toast.LENGTH_LONG).show();
            }else {
                helpher = new DatabaseHelpher(MainActivity.this);
                helpher.insertIntoDB(name, ingredients, directions);
            }
                etName.setText("");
                etIngredients.setText("");
                etDirections.setText("");

                Toast.makeText(MainActivity.this, "insert value", Toast.LENGTH_LONG);

            }
        });

    }


}
