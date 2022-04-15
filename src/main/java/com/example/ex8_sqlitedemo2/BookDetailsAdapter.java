package com.example.ex8_sqlitedemo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookDetailsAdapter extends ArrayAdapter<BookDetails> {
    private int resourceId;
    public BookDetailsAdapter(Context context, int textViewResourceId,
                              List<BookDetails> objects){
        super(context, textViewResourceId ,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        BookDetails bookDetails = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                    false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.book_name);
            viewHolder.category_name = (TextView) view.findViewById(R.id.category_name);
            viewHolder.price = (TextView) view.findViewById(R.id.price);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(bookDetails.getName());
        viewHolder.category_name.setText(bookDetails.getCategory_name());
        viewHolder.price.setText(bookDetails.getPrice());
        return view;
    }

    class ViewHolder {
        TextView name;
        TextView category_name;
        TextView price;
    }
}
