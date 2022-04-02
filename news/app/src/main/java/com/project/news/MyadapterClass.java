package com.project.news;

import android.content.Context;
import android.content.Intent;
import android.media.MediaDescrambler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class MyadapterClass extends RecyclerView.Adapter<MyadapterClass.myViewHolder> implements Filterable {
    ArrayList<model> data;
    ArrayList<model> backup;
    Context context;

    public MyadapterClass(ArrayList<model> data, Context context) {
        this.context = context;
        this.data = data;
        backup = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_row, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        final model m = data.get(position);
        holder.img1.setImageResource(data.get(position).ImgName);
        holder.t1.setText(data.get(position).getHeader());
        holder.t2.setText(data.get(position).getDesc());

        holder.img1.setOnClickListener((v) -> {
            Intent i = new Intent(context, MainActivity2.class);
            i.putExtra("imagename", m.ImgName);
            i.putExtra("header", m.getHeader());
            i.putExtra("desc", m.getDesc());
            i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        public ImageView img1;
        public TextView t1, t2;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);

        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<model> filterdata = new ArrayList<>();
            if (keyword.toString().isEmpty()) {
                filterdata.addAll(backup);
            } else {
                for (model m : backup) {
                    if (m.getHeader().toString().toLowerCase().equals(keyword.toString().toLowerCase())) {
                        filterdata.add(m);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterdata;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data.clear();
            data.addAll((ArrayList<model>) results.values);
        }
    };

}
