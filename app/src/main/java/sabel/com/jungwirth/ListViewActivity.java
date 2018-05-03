package sabel.com.jungwirth;

import android.content.Context;
import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.security.Policy;
import java.util.Timer;

public class ListViewActivity extends AppCompatActivity {

    // DATA FIELDS
    private ListView lvZimmer;
    private ZimmerRepo zimmerpunkte;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lvZimmer = findViewById(R.id.lvZimmer);
        Serializable serializable = getIntent().getSerializableExtra("zimmerpunkte");
        if (serializable instanceof ZimmerRepo) {
            zimmerpunkte = (ZimmerRepo) serializable;
            ArrayAdapter<Zimmer> arrayAdapter = new ArrayAdapter<Zimmer>(this, android.R.layout.simple_list_item_1, zimmerpunkte.getZimmerpunkte());
            lvZimmer.setAdapter(arrayAdapter);
        } // END IF


        lvZimmer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Zimmer zimmer = zimmerpunkte.get(i);
                float zimmerWert = zimmer.getWert();
                if (zimmerWert < 200.0) {
                    System.out.println("Blizlicht");;
                    flashLightOn();
                }
            }
        });


    } // END ONCREATE

    private void flashLightOn() {

        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            //Timer timer = new Timer();
            Thread.sleep(5000);
            cameraManager.setTorchMode(cameraId, false);

        } catch (CameraAccessException e) {
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



} // END CLASS
