package com.prashanth.jokedisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prashanth.jokedisplay.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class DisplayActivity extends AppCompatActivity {

    TextView Jokedisplay;
    String Joke;
    Button back;
    String product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);
        Jokedisplay = (TextView) findViewById(R.id.joke_display);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                Joke = null;
                product = "free";
            } else {
                Joke = extras.getString("Joke");
                product = extras.getString("Product");
            }
        }else {
            Joke = (String) savedInstanceState.getSerializable("Joke");
            product = (String) savedInstanceState.getSerializable("Product");
        }

        Jokedisplay.setText(Joke);

        if (product.equals("Free")) {
            AdView mAdView = (AdView) findViewById(R.id.adView1);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        back = (Button) findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            finish();
            }
        }
        );

    }
}
