package com.project.user_permission;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (!isEnableLocation()) {
//            getLoc();
//        }
//        if (!isEnableLocation()) {
//            Toast.makeText(this, "Please turn on location", Toast.LENGTH_SHORT).show();
//            getLoc();
//            if (!isEnableLocation()) {
//                try {
//                    Toast.makeText(this, "Sorry app won't work", Toast.LENGTH_SHORT).show();
//                    Thread.sleep(1000);
//                    finish();
//                } catch (InterruptedException e) {
//
//                }
//            }
//        }
    }

    public void getLoc() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("location Open");
        dialog.setMessage("Your Location off please turn on");
        dialog.setInverseBackgroundForced(true);
        dialog.setCancelable(true);
        dialog.setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent in = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(in);
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                return;
            }
        });
//        dialog.show();
        AlertDialog pop = dialog.create();
        pop.show();
    }

    public boolean isEnableLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}