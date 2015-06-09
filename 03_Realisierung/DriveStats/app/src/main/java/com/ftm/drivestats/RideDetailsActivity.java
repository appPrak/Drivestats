package com.ftm.drivestats;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RideDetailsActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_details);
        ArrayList<Float> daten = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 100; i++){
            daten.add(random.nextFloat());
        }

        if (savedInstanceState == null) {
            createGraph(createSeries(daten));
            createGraph(createSeries(daten));
            createGraph(createSeries(daten));
        }
    }

    // Fügt einen neuen Graphen zur FrameList hinzu
    public void AddGraph(){
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.

            // Create a new Fragment to be placed in the activity layout
            GraphFragment firstFragment = new GraphFragment();
            firstFragment.CreateSpeedGraph();
            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            // Source: http://wptrafficanalyzer.in/blog/dynamically-add-fragments-to-an-activity-in-android/
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, firstFragment);
            fragmentTransaction.commit();
        }

    }


    /**
     * Nimmt eine Liste von Werten und fügt diese in eine LineGraphSerie ein
     * @param values Hier ist noch eine Definition der Schnittstelle notwendig, momentan weren als X-Werte nur Datenpukte durchnummeriert
     * @return LineGraphSeries mit dem Inhalt der List
     */
    private LineGraphSeries<DataPoint> createSeries (List<Float> values){

        DataPoint[] pointArray = new DataPoint[values.size()];

        for (int i = 0; i<values.size(); i++){
            DataPoint point = new DataPoint(i, values.get(i));
            pointArray[i] = point;
        }
        return new LineGraphSeries<>(pointArray);

    }


    /**
     * Generiert einen GraphView und fügt ihn zum Layout hinzu
     * Alternative: http://androidplot.com/download/
     * Styling options: http://www.android-graphview.org/documentation/styling-options
     * Legende: http://www.android-graphview.org/documentation/legend
     * @param series Die zu plotenden Daten
     */
    public void createGraph(LineGraphSeries<DataPoint> series){
        LinearLayout linear = (LinearLayout) findViewById(R.id.fragment_container);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);


        //GraphView graph = new GraphView(getApplicationContext());
        GraphView graph = (GraphView) getLayoutInflater().inflate(R.layout.graph_fragment_layout, (ViewGroup) this.findViewById(R.id.fragment_container), false);

        // Daten hinzufügen
        graph.addSeries(series);

        // Scroll und skalierbarer inhalt
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);

        // Beschriftungen
        graph.setTitle("Test Graph");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Time");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Speed");

        // Design
        graph.getGridLabelRenderer().setPadding(0);
        graph.setTitleColor(getResources().getColor(R.color.graph_title));
        graph.getViewport().setBackgroundColor(getResources().getColor(R.color.graph_background));
        series.setColor(getResources().getColor(R.color.graph));

        linear.addView(graph);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ride_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
