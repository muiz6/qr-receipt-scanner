package com.example.qrreceiptscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.qrreceiptscanner.home.DBHelper;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class scannerView extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView sView;
    public DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sView = new ZXingScannerView(this);
        db = new DBHelper(this);
        setContentView(sView);

        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                sView.startCamera();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }

    @Override
    public void handleResult(Result rawResult) {
//        dummyActivity.tView.setText(rawResult.getText()+"");
        startActivity(new Intent(this, dummyActivity.class));
        boolean flag = db.insertData(rawResult.getText());
        if(flag){
            Toast toast = Toast.makeText(getApplicationContext(), "Data added", Toast.LENGTH_LONG);
            toast.show();
        }
        System.out.println(rawResult.getText());
    }

    @Override
    protected void onPause() {
        super.onPause();
        sView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sView.setResultHandler(this);
        sView.startCamera();
    }
}