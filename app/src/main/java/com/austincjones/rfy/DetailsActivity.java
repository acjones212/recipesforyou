package com.austincjones.rfy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    DatabaseHelpher helpher;
    List<DatabaseModel> dbList;
    int position;
    TextView tvname,tvingredients,tvdirections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // 5. get status value from bundle
         position = bundle.getInt("position");

        tvname =(TextView)findViewById(R.id.name);
        tvingredients =(TextView)findViewById(R.id.ingredients);
        tvdirections =(TextView)findViewById(R.id.directions);
        helpher = new DatabaseHelpher(this);
        dbList= new ArrayList<DatabaseModel>();
        dbList = helpher.getDataFromDB();

        if(dbList.size()>0){
            String name= dbList.get(position).getName();
            String ingredients=dbList.get(position).getIngredients();
            String directions=dbList.get(position).getDirections();
            tvname.setText(name);
            tvingredients.setText(ingredients);
            tvdirections.setText(directions);
        }

        Toast.makeText(DetailsActivity.this, dbList.toString(), Toast.LENGTH_LONG);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
