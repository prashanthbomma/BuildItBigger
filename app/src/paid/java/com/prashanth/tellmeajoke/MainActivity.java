package com.prashanth.tellmeajoke;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.prashanth.jokedisplay.DisplayActivity;

public class MainActivity extends AppCompatActivity implements GCEAsync.Callback  {

    Button Tellajoke;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        Tellajoke = (Button) findViewById(R.id.button_joke);
        Tellajoke.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pDialog.setMessage("Loading");
                showDialog();
                getFetchJoke();
        }
        }
        );

    }

    private void getFetchJoke(){
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
        String product = "Paid";
        intent.putExtra("Product", product);
        startActivity(intent);
    }
}
