package com.ftm.drivestats;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


/**
 * Created by Markus Weber on 30.05.2015.
 * Diese Klasse dient dazu, einen Graphen anhand der übergebenen Daten zu erstellen
 * Source: http://www.android-graphview.org/documentation/how-to-create-a-simple-graph
 */
public class GraphFragment extends Fragment {
    GraphView graph; //= (GraphView) this.getView();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.graph_fragment_layout, container, false);
    }

    // todo: Definiere in welchem Format die daten übergeben werden
    public GraphView CreateSpeedGraph(){
        // todo: ich kriegs nicht hin, dass GraphView in das FrameLayout eingetragen wird
        graph = (GraphView) this.getView() ;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });

        if (graph != null) {
            graph.addSeries(series);
        }
        return graph;
    }

}
