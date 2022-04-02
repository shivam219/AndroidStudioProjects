package com.project.user_permission;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    ConnectivityManager connectivityManager;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isNetConnecte = isNetConnected();
        boolean isEnableLocatio = isEnableLocation();
        if (isNetConnecte && isEnableLocatio) {
            new Handler().postDelayed(() -> {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }, 3000);
        }
    }

    public boolean isEnableLocation() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            return true;
        } else {
//            turnOnLocation();
        }
        return false;
    }

    public void turnOnLocation() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SplashScreen.this).setTitle("location Settings").setMessage("Your Location off please turn on").setInverseBackgroundForced(true).setCancelable(false).
                setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        boolean t = startActivityIfNeeded(intent, 10);
//                        onDestroy();
//                        m1();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog pop = dialog.create();
        pop.show();

//        return isEnableLocation();
    }

    private void m1() {
    }


    public boolean isNetConnected() {
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        } else {
            turnOnInternet();
        }
        return false;
    }

    public void turnOnInternet() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SplashScreen.this).setTitle("Turn on Internet").setMessage("Not connected to internet").setCancelable(false).setInverseBackgroundForced(true).
                setPositiveButton("turn On", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        boolean t = startActivityIfNeeded(intent, 20);
//                        onDestroy();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finish();
            }
        });
        AlertDialog pop = dialog.create();
        pop.show();
//        return isNetConnected();
    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(this, "ACtitvi dete", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("tag", "desotry fist");
    }
}