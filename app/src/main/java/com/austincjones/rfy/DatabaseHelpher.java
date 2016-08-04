package com.austincjones.rfy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PRABHU on 11/12/2015.
 */
public class DatabaseHelpher extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="recipe";
    private static final int DATABASE_VERSION = 1;
    private static final String RECIPE_TABLE = "stureg";
    private static final String REC_TABLE = "create table "+RECIPE_TABLE +"(name TEXT primary key,ingredients TEXT,directions TEXT)";

Context context;

    public DatabaseHelpher(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(REC_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE);

        // Create tables again
        onCreate(db);
    }
/* Insert into database*/
    public void insertIntoDB(String name, String ingredients, String directions){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("ingredients", ingredients);
         values.put("directions", directions);

        // 3. insert
        db.insert(RECIPE_TABLE, null, values);
        // 4. close
        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }
/* Retrive  data from database */
    public List<DatabaseModel> getDataFromDB(){
        List<DatabaseModel> modelList = new ArrayList<DatabaseModel>();
        String query = "select * from "+RECIPE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                DatabaseModel model = new DatabaseModel();
                model.setName(cursor.getString(0));
                model.setIngredients(cursor.getString(1));
                model.setDirections(cursor.getString(2));

                modelList.add(model);
            }while (cursor.moveToNext());
        }


        Log.d("recipe data", modelList.toString());


        return modelList;
    }


    /*delete a row from database*/

    public void deleteARow(String name){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(RECIPE_TABLE, "name" + " = ?", new String[] { name });
        db.close();
    }

}
