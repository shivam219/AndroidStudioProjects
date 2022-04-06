package com.project.mytabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.security.acl.LastOwnerException;
import java.util.jar.JarEntry;

public class f1 extends Fragment {
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        find view by id not available here
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_f1, container, false);
        recyclerView = rootView.findViewById(R.id.recycle_view);
        TextView textView = rootView.findViewById(R.id.data);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//                                        new appcompactActity
        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.107/php/json_con/json_php.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                textView.setText(response);
                Toast.makeText(getActivity(), " " + response, Toast.LENGTH_SHORT).show();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
//                Gson gson1 = gsonBuilder.create();
//                if (gson == gson1) {
//                    Toast.makeText(getContext(), "same", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getContext(), "no same", Toast.LENGTH_SHORT).show();
//                }
                Datum data[] = gson.fromJson(response, Datum[].class);
                recyclerView.setAdapter(new Adapter(data));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

        return rootView;
    }
}