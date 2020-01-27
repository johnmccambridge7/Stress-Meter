package com.example.stress_meter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ImageConfirmationActivity extends AppCompatActivity {

    private String classification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_confirmation);

        Intent intent = getIntent();

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0);
        }

        int imageID = intent.getIntExtra("image", 0);
        classification = String.valueOf(intent.getIntExtra("classification", -1));

        final ImageView imageView = findViewById(R.id.submittedImage);
        Drawable drawable = getApplicationContext().getDrawable(imageID);
        imageView.setImageDrawable(drawable);
    }

    public void cancel(View view) {
        finish();
    }

    public void submit(View view) throws IOException {
        // get the stress level - DONE
        // write to a csv - DONE
        // construct graph lib - DONE
        // draw the graph - DONE
        // schedule the alerts
        // implement more images func. - DONE
        Long timestampLong = System.currentTimeMillis()/1000;
        String timestamp = timestampLong.toString();

        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = getString(R.string.stressFileName);
        String path = baseDir + File.separator + fileName;

        File file = new File(path);

        FileWriter fileWriter;
        CSVWriter writer;

        if(file.exists() && !file.isDirectory()) {
            // file already exists so append
            fileWriter = new FileWriter(path, true);
            writer = new CSVWriter(fileWriter);
        } else {
            writer = new CSVWriter(new FileWriter(path));
        }

        String[] packet = {timestamp, classification};
        writer.writeNext(packet);
        writer.close();
        finish();
    }
}
