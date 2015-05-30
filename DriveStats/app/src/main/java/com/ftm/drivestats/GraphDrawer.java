package com.ftm.drivestats;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

/**
 * Created by Markus Weber on 30.05.2015.
 * Diese Klasse dient dazu, einen Graphen anhand der übergebenen Daten zu erstellen
 */
public class GraphDrawer extends Fragment {
    // todo: Definiere in welchem Format die daten übergeben werden
    // http://www.android-graphview.org/documentation/how-to-create-a-simple-graph
    public void CreateGraph(List<Float> data)
    {
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.id.graph, container, false);
    }

}
