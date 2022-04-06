package com.project.mytabs;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class f2 extends Fragment {

    EditText id, name, add, mobile;
    Button btn;
    String uid, uname, uadd, umobile;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_f2, container, false);

        id = view.findViewById(R.id.id);
        name = view.findViewById(R.id.name);
        add = view.findViewById(R.id.add);
        mobile = view.findViewById(R.id.mobile);
        btn = view.findViewById(R.id.btnSend);


        btn.setOnClickListener((v) -> {
            uid = id.getText().toString().trim();
            uname = name.getText().toString().trim();
            uadd = add.getText().toString().trim();
            umobile = mobile.getText().toString().trim();
            insertData();
        });


        return view;
    }

    private void insertData() {
        StringRequest sr = new StringRequest(Request.Method.POST, "http://192.168.0.107/php/json_con/volley_php.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", uid);
                map.put("name", uname);
                map.put("add", uadd);
                map.put("mobile", umobile);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(sr);
    }
}