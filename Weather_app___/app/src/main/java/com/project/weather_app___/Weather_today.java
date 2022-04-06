package com.project.weather_app___;

import static com.project.weather_app___.MainActivity.toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Weather_today extends Fragment {
    private TextView mainTemperature, conditionText, temperatureDayTV, temperatureNightTV, temperatureFeelTV, todayDateTv, sunriseTv, sunsetTv;
    private RecyclerView weather_rv_day, weather_rv_week;
    ConstraintLayout backgroudImage;
    private ImageView weatherConditionIcon;
    private String cityName;
    private ArrayList<WeatherDayModel> weatherDayModelArrayList = new ArrayList<>();
    private WeatherDayAdapter weatherDayAdapter;

    private ArrayList<WeatherWeekModel> weatherWeekModelArrayList = new ArrayList<>();
    private WeatherWeekAdapter weatherWeekAdapter;

    static Weather_today instance;
    static String previousLocation = "";
    private final String SHARE_PREFERENCE = "sharecity";
    private final String SHARE_CITYNAME = "cityname";
    //    refresh today layout
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    @SuppressLint("MissingPermission")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragement_weather, container, false);
        instance = this;
        mainTemperature = rootView.findViewById(R.id.idMainTemperature);
        conditionText = rootView.findViewById(R.id.idConditionText);
        weather_rv_day = rootView.findViewById(R.id.id_day_weather_rv);
        weather_rv_week = rootView.findViewById(R.id.id_week_weather_rv);
        backgroudImage = rootView.findViewById(R.id.idBackgroudImage);
        weatherConditionIcon = rootView.findViewById(R.id.idWeatherConditionIcon);
        temperatureDayTV = rootView.findViewById(R.id.idTVDayTemperature);
        temperatureNightTV = rootView.findViewById(R.id.idTVNightTemperature);
        temperatureFeelTV = rootView.findViewById(R.id.idTVFeelTemperature);
        todayDateTv = rootView.findViewById(R.id.idTVTodayDay);
        sunriseTv = rootView.findViewById(R.id.idTVSunrise);
        sunsetTv = rootView.findViewById(R.id.idTVSunset);
        weatherDayAdapter = new WeatherDayAdapter(getActivity(), weatherDayModelArrayList);
        weather_rv_day.setAdapter(weatherDayAdapter);

        weatherWeekAdapter = new WeatherWeekAdapter(getActivity(), weatherWeekModelArrayList);
        weather_rv_week.setAdapter(weatherWeekAdapter);

        loadCityNameIntoPreviousLocation();//load previous city name to variable
        swipeRefreshLayout = rootView.findViewById(R.id.idswipelayoutWeather);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadCityNameIntoPreviousLocation();
                getWeatherInfo(previousLocation);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        if (previousLocation != "") {
            getWeatherInfo(previousLocation);
        } else {
            cityName = "mumbai";
            getWeatherInfo(cityName);
        }
        return rootView;
    }

    public void getWeatherInfo(String cityName) {
        String url = "http://api.weatherapi.com/v1/forecast.json?key=c8993fdb29054963ae1192356220202&q=" + cityName + "&days=2&aqi=yes&alerts=yes";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONObject response) {
                weatherDayModelArrayList.clear();
                try {
                    JSONObject current = response.getJSONObject("current");
                    int temperature = (int) current.getDouble("temp_c");
                    mainTemperature.setText(temperature + "°C");
                    String condition = current.getJSONObject("condition").getString("text");
                    conditionText.setText(condition);
                    String conditionIcon = current.getJSONObject("condition").getString("icon");
                    Picasso.get().load("http:".concat(conditionIcon)).into(weatherConditionIcon);
                    temperatureFeelTV.setText("Feel like " + current.getInt("feelslike_c") + " °C");
                    JSONObject forecast = response.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0);
                    JSONArray forecastArr = response.getJSONObject("forecast").getJSONArray("forecastday");
                    temperatureDayTV.setText("Day " + forecast.getJSONObject("day").getString("maxtemp_c") + " °C");
                    temperatureNightTV.setText("Night " + forecast.getJSONObject("day").getString("mintemp_c") + " °C");
                    sunriseTv.setText("Sunrise  " + forecast.getJSONObject("astro").getString("sunrise"));
                    sunsetTv.setText("Sunset  " + forecast.getJSONObject("astro").getString("sunset"));
                    int isDay = current.getInt("is_day");
                    String fullDateFormat = response.getJSONObject("location").getString("localtime");
                    String date = forecast.getString("date");
                    Date fullDate = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(fullDateFormat);
                    String HourAm = new SimpleDateFormat("hh:mm aa").format(fullDate);
                    LocalDate currentDate = LocalDate.parse(date);
                    int day = currentDate.getDayOfMonth();
                    String month = currentDate.getMonth().toString().toLowerCase();
                    todayDateTv.setText(day + " " + month + ", " + HourAm);

                    int isDayCode = current.getJSONObject("condition").getInt("code");

                    setImageToBackground(isDayCode, isDay);
                    JSONArray hourArrayDay1 = forecastArr.getJSONObject(0).getJSONArray("hour");
                    JSONArray hourArrayDay2 = forecastArr.getJSONObject(0).getJSONArray("hour");
                    int count = 24;
                    for (int i = fullDate.getHours(); i < hourArrayDay1.length(); i++) {
                        JSONObject hourObj = hourArrayDay1.getJSONObject(i);
                        String time = hourObj.getString("time");
                        String temper = ((int) Double.parseDouble(hourObj.getString("temp_c"))) + "";
                        String img = hourObj.getJSONObject("condition").getString("icon");
                        String wind = hourObj.getString("wind_kph");
                        --count;
                        weatherDayModelArrayList.add(new WeatherDayModel(time, temper, img, wind));
                    }
                    for (int i = 0; i < count; i++) {
                        JSONObject hourObj = hourArrayDay2.getJSONObject(i);
                        String time = hourObj.getString("time");
                        String temper = ((int) Double.parseDouble(hourObj.getString("temp_c"))) + "";
                        String img = hourObj.getJSONObject("condition").getString("icon");
                        String wind = hourObj.getString("wind_kph");
                        weatherDayModelArrayList.add(new WeatherDayModel(time, temper, img, wind));
                    }
                    weatherDayAdapter.notifyDataSetChanged();/*change recycle view*/
                    String stateName = response.getJSONObject("location").getString("region");
                    String cityname = response.getJSONObject("location").getString("name");
                    toolbar.setTitle(cityname + ", " + stateName);
//                    Toast.makeText(getActivity(), cityname + ", " + stateName, Toast.LENGTH_SHORT).show();
                    setWeekForecast(cityname.toLowerCase());
                    setData(cityName);

                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "City not found", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void setImageToBackground(int code, int isDay) {
        Toast.makeText(getActivity(), "  " + code, Toast.LENGTH_SHORT).show();
        if (isDay == 1) {
            switch (code) {
                case 1000:
                case 1003:
                case 1006:
                case 1009:
                    backgroudImage.setBackgroundResource(R.drawable.dayclear);
                    break;
            /*    case 1006:
                  case 1009:
                    backgroudImage.setBackgroundResource(R.drawable.partycloud);
                    break;*/
                case 1030:
                case 1063:
                case 1066:
                case 1069:
                case 1114:
                case 1117:
                case 1135:
                case 1210:/*light snow all below*/
                case 1213:
                case 1216:
                case 1219:
                    backgroudImage.setBackgroundResource(R.drawable.mist);
                    break;
                case 1072:
                case 1180:
                case 1183:
                case 1186:
                case 1189:
                case 1192:
                case 1198:
                case 1204:
                case 1237://Ice pellets light and moderate//
                    backgroudImage.setBackgroundResource(R.drawable.rainingdrizzle);
                    break;
                case 1195:
                case 1201:
                case 1207:
                case 1276:
                case 1273:

                    backgroudImage.setBackgroundResource(R.drawable.heavyrainshower);
                    break;
                case 1087:
                    backgroudImage.setBackgroundResource(R.drawable.thunder);
                    break;
                case 1240:
                case 1243:
                case 1246:
                case 1249:
                case 1252:
                    backgroudImage.setBackgroundResource(R.drawable.rain_shower);
                    break;
                case 1279:
                case 1258:
                case 1255:
                case 1282:
                case 1225:
                case 1222:
                    backgroudImage.setBackgroundResource(R.drawable.heavy_show);
                    break;
            }
        } else {
            switch (code) {
                case 1087:
                case 1273:
                case 1276:
                case 1279:
                case 1282:
                    backgroudImage.setBackgroundResource(R.drawable.night_thunder);
                    break;
                case 1186:
                case 1180:
                case 1183:
                case 1189:
                case 1192:
                case 1195:
                case 1240:
                case 1246:
                case 1249:
                    backgroudImage.setBackgroundResource(R.drawable.night_raining);
                    break;
                default:
                    backgroudImage.setBackgroundResource(R.drawable.nightclear);
                    break;
            }
        }

    }

    public void setData(String cityName) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARE_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARE_CITYNAME, cityName);
        editor.apply();
    }

    public void loadCityNameIntoPreviousLocation() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARE_PREFERENCE, Context.MODE_PRIVATE);
        previousLocation = sharedPreferences.getString(SHARE_CITYNAME, "");
    }

    public void setWeekForecast(String cityName) {
        String url = "http://api.weatherapi.com/v1/forecast.json?key=c4be6f1c1d944d27bde91724222403&q=" + cityName + "&days=8&aqi=yes&alerts=yes";
        RequestQueue requestQueueweek = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                weatherWeekModelArrayList.clear();
                try {
                    JSONArray forcecastArr = response.getJSONObject("forecast").getJSONArray("forecastday");
                    int cityCount = forcecastArr.length();
                    for (int i = 0; i < cityCount; i++) {
                        JSONObject cur = forcecastArr.getJSONObject(i);
                        String date = cur.getString("date");
                        String min = ((int) Double.parseDouble(cur.getJSONObject("day").getString("maxtemp_c"))) + "";
                        String max = ((int) Double.parseDouble(cur.getJSONObject("day").getString("mintemp_c"))) + "";
                        String iconUrl = cur.getJSONObject("day").getJSONObject("condition").getString("icon");
                        weatherWeekModelArrayList.add(new WeatherWeekModel(date, iconUrl, max, min));
                    }
                    weatherWeekAdapter.notifyDataSetChanged();

                } catch (JSONException j) {
                    Toast.makeText(getContext(), "error in second", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueueweek.add(jsonObjectRequest);
    }

    public static Weather_today getInstance() {
        return instance;
    }
}