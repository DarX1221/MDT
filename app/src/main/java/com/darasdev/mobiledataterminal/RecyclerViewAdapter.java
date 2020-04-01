package com.darasdev.mobiledataterminal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Product> mProductList;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView productNameTV, productDescrTV, productCodeTV;
        public MyViewHolder(View v) {
            super(v);
            productNameTV = (TextView) v.findViewById(R.id.product_name_textview);
            productDescrTV = (TextView) v.findViewById(R.id.product_description_textview);
            productCodeTV = (TextView) v.findViewById(R.id.product_code_textview);
        }
    }


    public RecyclerViewAdapter(ArrayList<Product> productList) {
        mProductList = productList;
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_row, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        Product currentProduct = mProductList.get(position);

        // - replace the contents of the view with that element
        holder.productNameTV.setText(currentProduct.getName());
        holder.productDescrTV.setText(currentProduct.getDescription());
        String codeBufor = Integer.toString(currentProduct.getCode());
        holder.productCodeTV.setText(codeBufor);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

}
