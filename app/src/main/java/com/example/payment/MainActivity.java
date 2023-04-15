package com.example.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    Button pay;
    TextView retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);
        pay = findViewById(R.id.pay);
        retry = findViewById(R.id.retry);

        if (!checkInternet()){

            NoInternetDialog noInternetDialog = new NoInternetDialog(MainActivity.this);
            noInternetDialog.setCancelable(false);
            noInternetDialog.show();

        }

    }

    private boolean checkInternet(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){

                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());

                if (networkCapabilities != null){

                    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){

                        return true;

                    } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                        return true;

                    } else{

                        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
                        
                    }

                }

            }else {

                ConnectivityManager ca = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = ca.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnectedOrConnecting();

            }

        }
//        return  connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
        return false;

    }

}
