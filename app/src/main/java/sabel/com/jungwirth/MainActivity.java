package sabel.com.jungwirth;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // DATA FIELDS
    private Button btnSave;
    private Button btnAll;
    private TextView tvOrt, tvHelligkeit, tvWert;
    private EditText edOrt;
    private ZimmerRepo zimmerPunkte;
    private String ort;

    private SensorManager sensorManager;
    private Sensor sensor;
    private WindowManager.LayoutParams layoutParams;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener((SensorEventListener) this, sensor, SensorManager.SENSOR_DELAY_NORMAL);


        initEvents();


    } // END ONCREATE


    private void initEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ort = edOrt.getText().toString();
                System.out.println("-------------" + ort + "-------------");
                float helligkeitswert = Float.valueOf(tvWert.getText().toString());

                Zimmer zimmer = new Zimmer(new Date(), ort, helligkeitswert);
                zimmerPunkte.add(zimmer);


            }
        });


        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                intent.putExtra("zimmerpunkte", zimmerPunkte);
                startActivity(intent);
            }
        });


    }


    private void initComponents() {

        tvOrt = findViewById(R.id.tvOrt);
        tvHelligkeit = findViewById(R.id.tvHelligkeit);
        tvWert = findViewById(R.id.tvWert);
        edOrt = findViewById(R.id.edOrt);
        btnAll = findViewById(R.id.btnAll);
        btnSave = findViewById(R.id.btnSave);
        zimmerPunkte = new ZimmerRepo();


    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        tvWert.setText("" + sensorEvent.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }


} // END CLASS
