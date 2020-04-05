package com.darasdev.mobiledataterminal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class TerminalDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "terminalDatabase";
    private static final int DB_VERSION = 1;
    SQLiteDatabase sbBufor;



    TerminalDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db, 0, DB_VERSION);

        insertData(db, "Name", "Description", 1234, 1);
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
            db.execSQL("CREATE TABLE DATA (_ID INTEGER PRIMARY KEY,"
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "CODE INTEGER);");

        }
        if(oldVersion > 2) {
            db.execSQL("ALTER TABLE DATA ADD COLUMN FAVORITE NUMERIC");
        }
    }

}
