package com.project.weather_app___;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class News_Today extends Fragment {
    public static News_Today instance;
    private RecyclerView news_rv, news_tab_topic_rv;

    private ArrayList<NewsModel> newsModelArrayList = new ArrayList<>();

    private ArrayList<Integer> topicLIst;
    private ArrayList<String> topicName;
    private NewsAdapter newsAdapter;
    private NewsTabTopicAdapter topicAdapter;
    // refresh news layout
    SwipeRefreshLayout swipeRefreshLayout;
    String previous = "weather";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragement_news, container, false);
        instance = this;

        news_rv = rootView.findViewById(R.id.id_news_rv);
        newsAdapter = new NewsAdapter(getContext(), newsModelArrayList);
        news_rv.setAdapter(newsAdapter);

        swipeRefreshLayout = rootView.findViewById(R.id.idswipelayoutNews);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNewsInfo(previous);
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        news_tab_topic_rv = rootView.findViewById(R.id.id_news_tab_topic_rv);
        topicLIst = new ArrayList<>();
        topicName = new ArrayList<>();
        topicLIst.add(R.drawable.news_tab_topic_local);
        topicName.add("local");
        topicLIst.add(R.drawable.news_tab_topic_weather);
        topicName.add("weather");
        topicLIst.add(R.drawable.news_tab_topic_shop);
        topicName.add("shopping");
        topicLIst.add(R.drawable.news_tab_topic_sport);
        topicName.add("sport");
        topicLIst.add(R.drawable.news_tab_topic_tech);
        topicName.add("technology");
        topicLIst.add(R.drawable.news_tab_topic_cartoon);
        topicName.add("cartoon");
        topicLIst.add(R.drawable.news_tab_topic_politics);
        topicName.add("politics");
        topicLIst.add(R.drawable.news_tab_topic_business);
        topicName.add("business");
        topicLIst.add(R.drawable.news_tab_topic_entertainment);
        topicName.add("entertainment");
        topicAdapter = new NewsTabTopicAdapter(topicLIst, topicName, getContext());
        news_tab_topic_rv.setAdapter(topicAdapter);
        topicAdapter.notifyDataSetChanged();
        getNewsInfo("weather");
        return rootView;
    }

    public void getNewsInfo(String topic) {
//        String url1 = "https://gnews.io/api/v4/search?q=" + topic + "&token=055a7ae97b11197e4dda56778f1598aa&lang=en&max=45&country=in";
//        String url1 = "https://gnews.io/api/v4/search?q=" + topic + "&token=48ca703eaf035b87932f1fba4b45d27a&lang=en&max=45&country=in";
        String url1 = "https://gnews.io/api/v4/search?q=" + topic + "&token=93fd7e9a5ee1bc2bdaba5840038809e3&lang=en&max=45&country=in";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONObject response) {
                newsModelArrayList.clear();
                try {
                    JSONArray articles = response.getJSONArray("articles");
                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject curArticle = articles.getJSONObject(i);
                        String title = curArticle.getString("title");
                        String imgUrl = curArticle.getString("image");
                        String content = curArticle.getString("content");
                        String publisher = curArticle.getJSONObject("source").getString("name");
                        String date = curArticle.getString("publishedAt");
                        LocalDate ld = LocalDate.parse(date.substring(0, 10), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate ld2 = LocalDate.now();
                        long days = Period.between(ld, ld2).getDays();
                        newsModelArrayList.add(new NewsModel(title, imgUrl, content, publisher, (days == 0 ? " Today " : days + " Days ago")));
                    }
                    Toast.makeText(getContext(), topic, Toast.LENGTH_SHORT).show();
                    previous = topic;
                    newsAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "total request per used", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    /*use this in new tab topic adapter*/
    public static News_Today getInstance() {
        return instance;
    }
}
