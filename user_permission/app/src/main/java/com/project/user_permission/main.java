package com.project.user_permission;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class main extends AppCompatActivity {
    boolean permissionGranded;
    private final int permission_code = 100;
    String[] permission;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        permission = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.SEND_SMS
        };

        if (permissionGranded) {
        } else {
            if (ActivityCompat.checkSelfPermission(this, permission[0]) != PackageManager.PERMISSION_GRANTED) {
//                this method work after 23 mini sdk
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permission, permission_code);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == permission_code && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionGranded = true;
            Toast.makeText(getApplicationContext(), "cell  permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "cell permission not granted", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == permission_code && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            permissionGranded = true;
            Toast.makeText(getApplicationContext(), "Location permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Location permission not granted", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == permission_code && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
            permissionGranded = true;
            Toast.makeText(getApplicationContext(), "SMS  permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "SMS permission not granted", Toast.LENGTH_SHORT).show();
        }
        for (int str : grantResults) {
            Toast.makeText(getApplicationContext(), "     " +str, Toast.LENGTH_SHORT).show();
        }


    }
}