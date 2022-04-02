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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherWeekAdapter extends RecyclerView.Adapter<WeatherWeekAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeatherWeekModel> weatherWeekModelArrayList;

    public WeatherWeekAdapter(Context context, ArrayList<WeatherWeekModel> weatherWeekModelArrayList1) {
        this.context = context;
        this.weatherWeekModelArrayList = weatherWeekModelArrayList1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.model_week, parent, false));
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date;
    SimpleDateFormat wnf = new SimpleDateFormat("EEEE");

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherWeekModel model = weatherWeekModelArrayList.get(position);
        try {
            date = sdf.parse(model.getDay());
            holder.date.setText(wnf.format(date));
        } catch (ParseException e) {
        }
        holder.minTemp.setText("" + model.getMinTemp() + "°C");
        holder.maxTemp.setText(model.getMaxTemp() + "°C");
        Picasso.get().load("http:".concat(model.getIcon())).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return weatherWeekModelArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date, minTemp, maxTemp;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.idMWdate);
            minTemp = itemView.findViewById(R.id.idMWMinTemperature);
            maxTemp = itemView.findViewById(R.id.idMWMaxTemperature);
            img = itemView.findViewById(R.id.idMWImage);
        }
    }
}
