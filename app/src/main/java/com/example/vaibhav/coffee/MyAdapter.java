package com.example.vaibhav.coffee;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vaibhav on 2/4/17.
 */




public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> prod_name;
    private ArrayList<String> prod_price;


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView price;
        public TextView name;

        public ViewHolder(View v) {
            super(v);
            price = (TextView) v.findViewById(R.id.price);
            name = (TextView) v.findViewById(R.id.name);
        }
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> myDataset,ArrayList<String> myyear) {
        prod_name = myDataset;
        prod_price=myyear;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coffee_recycler, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final String name = prod_name.get(position);

        holder.name.setText(prod_name.get(position));
        holder.price.setText(prod_price.get(position));





    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return prod_name.size();
    }

}