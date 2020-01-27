package com.example.stress_meter.ui.results;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

import com.example.stress_meter.R;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultsFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_results, container, false);

        // todo:
        // write data to a file (timestamp and stress lvl) - DONE
        // visualize collected data from CSV - DONE
        // create broadcast alerts

        List<PointValue> values = new ArrayList<PointValue>();
        LineChartData data = new LineChartData();

        Axis axisX = new Axis();
        axisX.setName("Instances.");

        Axis axisY = new Axis();
        axisY.setName("Stress Level");

        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        LineChartView chart = root.findViewById(R.id.chart);

        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = getString(R.string.stressFileName);
        String path = baseDir + File.separator + fileName;

        TableRow.LayoutParams params1 = new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams params2 = new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TableLayout tbl = (TableLayout) root.findViewById(R.id.tableLayout);

        try {
            CSVReader reader = new CSVReader(new FileReader((path)));
            String[] nextLine;

            int counter = 0;

            while((nextLine = reader.readNext()) != null) {
                String timestamp = nextLine[0];
                String stressLevel = nextLine[1];

                TableRow row = new TableRow(getContext());
                TextView timestampLabel = new TextView(getContext());
                TextView stressLabel = new TextView(getContext());

                timestampLabel.setText(timestamp);
                stressLabel.setText(stressLevel);
                timestampLabel.setLayoutParams(params1);
                stressLabel.setLayoutParams(params1);
                stressLabel.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                //the textviews have to be added to the row created
                row.addView(timestampLabel);
                row.addView(stressLabel);

                row.setLayoutParams(params2);

                values.add(new PointValue(counter, Integer.valueOf(stressLevel)));
                counter += 1;

                tbl.addView(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        data.setLines(lines);

        chart.setLineChartData(data);
        return root;
    }
}