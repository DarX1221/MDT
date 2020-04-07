package com.darasdev.mobiledataterminal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

    //  Data Access Object, object to comunicate with DataBase Helper (TerminalDatabaseHelper)
    //  using functions

public class DataDAO {
    private TerminalDatabaseHelper TDbHelper;

    public DataDAO(Context context){
        TDbHelper = new TerminalDatabaseHelper(context);
    }

    void insertProduct(Product product){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBase.DB_NAME, product.getName());
        contentValues.put(DataBase.DB_DESCRIPTION, product.getDescription());
        contentValues.put(DataBase.DB_CODE, product.getCode());

        TDbHelper.getWritableDatabase().insert(DataBase.NameOfDataBase, null, contentValues);
    }

    void deleteProduct(final Long id){
        TDbHelper.getWritableDatabase().delete(DataBase.NameOfDataBase,
                " " + DataBase.DB_ID + " = ? ",
                new String[]{id.toString()}
                );
    }





    int id;
    public ArrayList getAllProduct(){

        Cursor cursor = TDbHelper.getReadableDatabase().query(DataBase.NameOfDataBase,
                new String[] {DataBase.DB_ID, DataBase.DB_NAME, DataBase.DB_DESCRIPTION, DataBase.DB_CODE},
                null, null, null, null, null);

        ArrayList<Product> listOfProducts = new ArrayList<>();

        cursor.moveToFirst();
        id = 0;
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                id++;
                listOfProducts.add(mapCursorToProduct(cursor));
            }
        }

        return listOfProducts;
    }


    private Product mapCursorToProduct(final Cursor cursor){
        Product buforProduct = new Product();

        int ID_ID = cursor.getColumnIndex(DataBase.DB_ID);
        int NAME_ID = cursor.getColumnIndex(DataBase.DB_NAME);
        int DESCRIPTION_ID = cursor.getColumnIndex(DataBase.DB_DESCRIPTION);
        int CODE_ID = cursor.getColumnIndex(DataBase.DB_CODE);

        // Change to private and use setter's
        buforProduct.id = cursor.getInt(ID_ID);
        buforProduct.name = cursor.getString(NAME_ID);
        buforProduct.description = cursor.getString(DESCRIPTION_ID);
        buforProduct.code = cursor.getInt(CODE_ID);

        return buforProduct;
    }
}
