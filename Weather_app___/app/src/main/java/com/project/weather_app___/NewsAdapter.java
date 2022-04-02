package com.project.weather_app___;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Holder> {

    Context context;
    ArrayList<NewsModel> newsModelArrayList;

    public NewsAdapter(Context context, ArrayList<NewsModel> newsModelArrayList) {
        this.context = context;
        this.newsModelArrayList = newsModelArrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.model_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        NewsModel model = newsModelArrayList.get(position);
        holder.title.setText(model.getTitle());
        Picasso.get().load(model.getIcon()).into(holder.icon);
        holder.content.setText(model.getContent());
        holder.publisher.setText(model.getPublisher());
        holder.date.setText(model.getDate());

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, NewsView.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("title", model.getTitle());
                i.putExtra("content", model.getContent());
                i.putExtra("Img_url", model.getIcon());
                i.putExtra("date", model.getDate());
                i.putExtra("publisher", model.getPublisher());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsModelArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView title, content, publisher, date;
        ImageView icon;
        LinearLayout ll;

        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.idMNTitle);
            icon = itemView.findViewById(R.id.idMNICon);
            content = itemView.findViewById(R.id.idMNContent);
            publisher = itemView.findViewById(R.id.idMNPublisher);
            date = itemView.findViewById(R.id.idMNDate);
            ll = itemView.findViewById(R.id.idNCM);
        }
    }
}
