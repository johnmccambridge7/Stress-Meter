package com.example.stress_meter.ui.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.stress_meter.Classifications;
import com.example.stress_meter.EMAAlarmReceiver;
import com.example.stress_meter.ImageConfirmationActivity;
import com.example.stress_meter.ImagesAdapter;
import com.example.stress_meter.PSM;
import com.example.stress_meter.R;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class HomeFragment extends Fragment {

    private int[] images;
    private ImagesAdapter adapter;
    private GridView grid;
    private Button moreImages;
    private UpdateGridTask task;
    private int selectedIndex = 0;
    private Classifications classifications;

    public class UpdateGridTask extends AsyncTask<Void, Integer[], Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            int[][] grids = {PSM.getGrid1(), PSM.getGrid2(), PSM.getGrid3()};
            Integer[] grid = new Integer[16];

            Random r = new Random();
            int choice = r.nextInt(3);

            // don't select the current index as the new set of images
            while(choice == selectedIndex) {
                choice = r.nextInt(3);
            }

            int[] gridSelection = grids[choice];
            selectedIndex = choice;

            for(int i=0; i < 16; i++) {
                grid[i] = Integer.valueOf(gridSelection[i]);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            publishProgress(grid);

            return null;
        }

        public void onProgressUpdate(Integer[]... param_2) {
            Integer[] imagesBoxed = param_2[0];
            final int[] images = new int[16];

            for(int i=0; i < 16; i++) {
                images[i] = imagesBoxed[i];
            }

            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent confirmIntent = new Intent(getContext(), ImageConfirmationActivity.class);
                    confirmIntent.putExtra("image", images[i]);
                    confirmIntent.putExtra("classification", classifications.get(images[i]));
                    startActivity(confirmIntent);
                }
            });

            adapter = new ImagesAdapter(getContext(), images);
            grid.invalidateViews();
            grid.setAdapter(adapter);

            Log.i("jmacdonald", "refreshing images");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        classifications = new Classifications();

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        images = PSM.getGrid1();

        grid = root.findViewById(R.id.images);
        moreImages = root.findViewById(R.id.moreImages);

        adapter = new ImagesAdapter(getContext(), images);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent confirmIntent = new Intent(getContext(), ImageConfirmationActivity.class);
                confirmIntent.putExtra("image", images[i]);
                confirmIntent.putExtra("classification", classifications.get(images[i]));
                startActivity(confirmIntent);
            }
        });

        moreImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moreImages(view);
            }
        });

        return root;
    }

    private void moreImages(View view) {
        task = new UpdateGridTask();
        task.execute();
    }
}