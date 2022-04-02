package com.project.weather_app___;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherDayAdapter extends RecyclerView.Adapter<WeatherDayAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeatherDayModel> weatherDayModelArrayList;

    public WeatherDayAdapter(Context context, ArrayList<WeatherDayModel> weatherRVAdapterArrayList1) {
        this.context = context;
        this.weatherDayModelArrayList = weatherRVAdapterArrayList1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from(context).inflate(R.layout.model_day, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherDayModel model = weatherDayModelArrayList.get(position);
        holder.temperature.setText(model.getTemperature() + "°C");
        Picasso.get().load("http:".concat(model.getIcon())).into(holder.img);
        holder.wind.setText(model.getWindSpeed() + "km/h");
        SimpleDateFormat fulldate = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date date = fulldate.parse(model.getTime());
            holder.time.setText(new SimpleDateFormat("hh:mm aa").format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return weatherDayModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView wind, temperature, time;
        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wind = itemView.findViewById(R.id.idMAirSpeed);
            temperature = itemView.findViewById(R.id.idMDayTemperature2);
            time = itemView.findViewById(R.id.idMTiming);
            img = itemView.findViewById(R.id.idMImg);
        }
    }
}