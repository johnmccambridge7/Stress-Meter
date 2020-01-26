package com.example.stress_meter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ImageConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_confirmation);

        Intent intent = getIntent();

        int imageID = intent.getIntExtra("image", 0);
        int classification = intent.getIntExtra("classification", -1);

        Log.i("jmacdonald", "Received image with stress: " + String.valueOf(classification));

        final ImageView imageView = findViewById(R.id.submittedImage);
        Drawable drawable = getApplicationContext().getDrawable(imageID);
        imageView.setImageDrawable(drawable);
    }

    public void cancel(View view) {
        finish();
    }

    public void submit(View view) {
        // get the stress level
        // write to a csv
        // construct graph lib
        // draw the graph
        // schedule the alerts
        // implement more images func.
    }
}
