package com.example.stress_meter.ui.results;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

import com.example.stress_meter.R;

import java.util.ArrayList;
import java.util.List;

public class ResultsFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_results, container, false);

        // todo:
        // write data to a file (timestamp and stress lvl)
        // visualize collected data from CSV
        // create broadcast alerts

        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(3, 4));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        LineChartView chart = root.findViewById(R.id.chart);
        chart.setLineChartData(data);
        return root;
    }
}