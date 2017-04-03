package com.prashanth.tellmeajoke;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.prashanth.jokedisplay.DisplayActivity;
import com.prashanth.tellmeajoke.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity implements GCEAsync.Callback {

    Button Tellajoke;
    InterstitialAd mInterstitialAd;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.institial_ad_unit));


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AdRequest adRequest1 = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest1);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        Tellajoke = (Button) findViewById(R.id.button_joke);
        Tellajoke.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            pDialog.setMessage("Loading");
                            showDialog();
                            getFetchJoke();
                        }
                    });
                }
                else {
                    getFetchJoke();
                }}
        }
        );

    }

    private void getFetchJoke()
    {
        new GCEAsync(this).execute();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    @Override
    public void onFinished(String result) {
            hideDialog();
            Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
            intent.putExtra("Joke", result);
            String product = "Free";
            intent.putExtra("Product", product);
            startActivity(intent);
    }

}
