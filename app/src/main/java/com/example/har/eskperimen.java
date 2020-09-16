package com.example.har;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class eskperimen extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "eksperimen";
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private SensorEvent sensors;
    private Button btnMulai;
    private TextView xAcc, yAcc, zAcc, xGyr, yGyr, zGyr, xMag, yMag, zMag, aMag, bMag, cMag;

    private LineChart mChart;
    private Thread thread;
    private boolean plotData = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekam_2);
        btnMulai = (Button) findViewById(R.id.btnMulai);
        btnMulai.setEnabled(true);
        btnMulai.setClickable(true);
        xAcc = (TextView) findViewById((R.id.xAcc));
        yAcc = (TextView) findViewById((R.id.yAcc));
        zAcc = (TextView) findViewById((R.id.zAcc));
        xGyr = (TextView) findViewById((R.id.xGyr));
        yGyr = (TextView) findViewById((R.id.yGyr));
        zGyr = (TextView) findViewById((R.id.zGyr));
        xMag = (TextView) findViewById((R.id.xMag));
        yMag = (TextView) findViewById((R.id.yMag));
        zMag = (TextView) findViewById((R.id.zMag));
        aMag = (TextView) findViewById((R.id.aMag));
        bMag = (TextView) findViewById((R.id.bMag));
        cMag = (TextView) findViewById((R.id.cMag));



        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        mChart = (LineChart) findViewById(R.id.grafikeksperimen);
        // enable description text
        mChart.getDescription().setEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.WHITE);
        for(int i=0; i<sensors.size(); i++){
            Log.d(TAG, "onCreate: Sensor "+ i + ": " + sensors.get(i).toString());
        }

        if (mAccelerometer != null) {
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        }

        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LineData data = new LineData();
                data.setValueTextColor(Color.WHITE);

                // add empty data
                mChart.setData(data);

                // get the legend (only possible after setting data)
                Legend l = mChart.getLegend();

                // modify the legend ...
                l.setForm(Legend.LegendForm.LINE);
                l.setTextColor(Color.WHITE);

                XAxis xl = mChart.getXAxis();
                xl.setTextColor(Color.WHITE);
                xl.setDrawGridLines(true);
                xl.setAvoidFirstLastClipping(true);
                xl.setEnabled(true);

                YAxis leftAxis = mChart.getAxisLeft();
                leftAxis.setTextColor(Color.WHITE);
                leftAxis.setDrawGridLines(false);
                leftAxis.setAxisMaximum(10f);
                leftAxis.setAxisMinimum(0f);
                leftAxis.setDrawGridLines(true);

                YAxis rightAxis = mChart.getAxisRight();
                rightAxis.setEnabled(false);

                mChart.getAxisLeft().setDrawGridLines(false);
                mChart.getXAxis().setDrawGridLines(false);
                mChart.setDrawBorders(false);

                feedMultiple();
                btnMulai.setText("Finish");
            }
        });

    }

    private void addEntry(SensorEvent event) {

        LineData data = mChart.getData();

        if (data != null) {

            ILineDataSet set = data.getDataSetByIndex(0);
            // set.addEntry(...); // can be called as well

            if (set == null) {
                set = createSet();
                data.addDataSet(set);
            }

//            data.addEntry(new Entry(set.getEntryCount(), (float) (Math.random() * 80) + 10f), 0);
            data.addEntry(new Entry(set.getEntryCount(), event.values[0] + 5), 0);
            data.notifyDataChanged();

            // let the chart know it's data has changed
            mChart.notifyDataSetChanged();

            // limit the number of visible entries
            mChart.setVisibleXRangeMaximum(150);
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            mChart.moveViewToX(data.getEntryCount());

        }
    }

    private LineDataSet createSet() {

        LineDataSet set = new LineDataSet(null, "Dynamic Data");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setLineWidth(3f);
        set.setColor(Color.MAGENTA);
        set.setHighlightEnabled(false);
        set.setDrawValues(false);
        set.setDrawCircles(false);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setCubicIntensity(0.2f);
        return set;
    }

    private void feedMultiple() {

        if (thread != null){
            thread.interrupt();
        }

        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true){
                    plotData = true;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (thread != null) {
            thread.interrupt();
        }
        mSensorManager.unregisterListener(this);

    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        /*if(plotData){
            addEntry(event);
            plotData = false;
        }*/
        xAcc.setText((int) event.values[0]);
        yAcc.setText((int) event.values[1]);
        zAcc.setText((int) event.values[2]);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onDestroy() {
        mSensorManager.unregisterListener(eskperimen.this);
        thread.interrupt();
        super.onDestroy();
    }
}
