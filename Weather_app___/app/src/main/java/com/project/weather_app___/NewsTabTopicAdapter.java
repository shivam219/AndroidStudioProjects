package com.project.weather_app___;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsTabTopicAdapter extends RecyclerView.Adapter<NewsTabTopicAdapter.Holder> {

    ArrayList<Integer> ImgId;
    ArrayList<String> TopicName;
    Context Context;

    public NewsTabTopicAdapter(ArrayList<Integer> imgId, ArrayList<String> topicName, Context context) {
        this.ImgId = imgId;
        this.TopicName = topicName;
        this.Context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.model_news_topic, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.img.setImageResource(ImgId.get(position));
        holder.img.setOnClickListener((v) -> {
            News_Today.getInstance().getNewsInfo(TopicName.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return ImgId.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView img;
        LinearLayout idNCM;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.idMNTopicIcon);
            idNCM = itemView.findViewById(R.id.idNCM);
        }
    }
}
