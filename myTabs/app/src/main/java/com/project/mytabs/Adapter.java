package com.project.mytabs;

import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {
    Datum[] data;

    public Adapter(Datum[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.signle, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        Datum cur = data[position];
        holder.id.setText(cur.getAge());
        holder.email.setText(cur.getEmail());
        holder.fname.setText(cur.getFname());
        holder.lname.setText(cur.getLname());
//        Picasso.get().load(cur.getAvatar()).into(holder.icon);
    }


    @Override
    public int getItemCount() {
        return data.length;
    }

    public class holder extends RecyclerView.ViewHolder {

        TextView id, email, fname, lname;
        ImageView icon;

        public holder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idUser);
            email = itemView.findViewById(R.id.idEmail);
            fname = itemView.findViewById(R.id.idFName);
            lname = itemView.findViewById(R.id.idLName);
            icon = itemView.findViewById(R.id.idICon);
        }
    }

}
