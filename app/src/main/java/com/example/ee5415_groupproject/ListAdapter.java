package com.example.ee5415_groupproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListAdapter extends BaseAdapter {
    Context context;
    private final String[] names;

    public ListAdapter(Context context, String[] names) {
        this.context = context;
        this.names = names;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.list_items, parent, false);
            viewHolder.name = (TextView)
                    view.findViewById(R.id.receiver_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(names[position]);
        TextView tempName = view.findViewById(R.id.receiver_name);
        ImageView tempImage = view.findViewById(R.id.image1);
        tempName.setOnClickListener(v -> {
                    Intent intent = new Intent(context.getApplicationContext(),
                            contact.class);
                    intent.putExtra("name", names[position]);
                    context.startActivity(intent);
                }
        );
        tempImage.setOnClickListener(v -> {
                    Intent intent = new Intent(context.getApplicationContext(),
                            MessageActivity.class);
                    Database db = new Database(context.getApplicationContext());
                    Cursor res = db.queryAll();
                    if (res.getCount() != 0) {
                        while (res.moveToNext()) {
                            if (res.getString(1).equals(names[position])) {
                                intent.putExtra("receiverName", names[position]);
                                intent.putExtra("emailAddress", res.getString(2));
                                break;
                            }
                        }
                    }
                    context.startActivity(intent);
                }
        );
        return view;
    }

    private static class ViewHolder {
        TextView name;
    }
}
