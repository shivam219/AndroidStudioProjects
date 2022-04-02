package com.project.video_loader;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class adaptervideo extends RecyclerView.Adapter<adaptervideo.holder> {
    ArrayList<videomodel> data;
    public adaptervideo(ArrayList<videomodel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.signgle_video_row, parent, false);

        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
         holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        VideoView video;
        TextView title, desc;
        ProgressBar pd ;
        public holder(@NonNull View itemView) {
            super(itemView);
            video = itemView.findViewById(R.id.video_view);
            title = itemView.findViewById(R.id.tile);
            desc = itemView.findViewById(R.id.desc);
            pd = itemView.findViewById(R.id.video_pdb);
        }

        void setData(videomodel obj) {
            video.setVideoPath(obj.getUrl());
            title.setText(obj.getTitle());
            desc.setText(obj.getDesc());
            video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp)
                {
                    pd.setVisibility(View.GONE);
                    mp.start();
                }
            });
            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }
    }
}
