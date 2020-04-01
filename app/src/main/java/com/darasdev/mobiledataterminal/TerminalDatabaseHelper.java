package com.darasdev.mobiledataterminal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class TerminalDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "terminalDatabase";
    private static final int DB_VERSION = 1;


    //Context contextBufor;
    TerminalDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        //contextBufor = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db, 0, DB_VERSION);

        insertData(db, "Product", "Description", 123, 1);
        insertData(db, "Phone1", "Sony Xperia X", 4558, 1);
        insertData(db, "Cable", "USB Type-B",2338, 1);
        insertData(db, "Phone2", "Xiaomi MI6", 1258, 0);
        insertData(db, "Cable", "USB Type-C",2338, 1);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }


    private static void insertData(SQLiteDatabase db, String name,
                                    String description, int code, int resourceId) {
        ContentValues dataValues = new ContentValues();
        dataValues.put("NAME", name);
        dataValues.put("DESCRIPTION", description);
        db.insert("DATA", null, dataValues);
    }


    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion < 1){
            toastMaker("Create DB");
            db.execSQL("CREATE TABLE DATA (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "CODE INTEGER);");

        }
        if(oldVersion > 2) {
            db.execSQL("ALTER TABLE DATA ADD COLUMN FAVORITE NUMERIC");
            toastMaker("Upgrade DB");
        }
    }


    void createSomeProd(){
        /*insertData(db, "Product", "Description", 123, 1);
        insertData(db, "Phone1", "Sony Xperia X", 4558, 1);
        insertData(db, "Cable", "USB Type-B",2338, 1);
        insertData(db, "Phone2", "Xiaomi MI6", 1258, 0);
        insertData(db, "Cable", "USB Type-C",2338, 1);*/
    }



    void toastMaker(String text){

        //Toast.makeText(contextBufor, text, Toast.LENGTH_SHORT).show();
    }
}
