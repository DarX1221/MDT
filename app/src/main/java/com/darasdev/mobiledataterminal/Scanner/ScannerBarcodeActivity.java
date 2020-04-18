package com.darasdev.mobiledataterminal.Scanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.darasdev.mobiledataterminal.AddProductActivity;
import com.darasdev.mobiledataterminal.DataBase;
import com.darasdev.mobiledataterminal.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.vision.CameraSource;
import com.google.zxing.Result;

import java.io.IOException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;


public class ScannerBarcodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;

    TextView barcodeInfo;
    SurfaceView cameraView;
    CameraSource cameraSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {

            if (checkPermission()) {
                Toast.makeText(this, "Permission is granted!", Toast.LENGTH_SHORT).show();
            }
            else {
                requestPermissions();
            }
        }

    }


    private boolean checkPermission(){
        return (ContextCompat.checkSelfPermission(this, CAMERA) == PackageManager.PERMISSION_GRANTED);
    }


    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[] {CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermissionResult(int requestCode, String permission[], int grantResults[]) {
        switch (requestCode){
            case REQUEST_CAMERA :
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted) {
                        Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this, "Permision denied!", Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        {
                            if (shouldShowRequestPermissionRationale(CAMERA)){
                                displayAlertMessage("You need to allow access for both permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                requestPermissions(new String[] {CAMERA}, REQUEST_CAMERA);
                                            }
                                        });

                            }
                        }
                    }
                }
                break;
        }
    }


    @Override
    public void onResume(){
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if (scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
            else {
                requestPermissions();
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.startCamera();
    }

    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", listener)
                .setNegativeButton("CLOSE", null)
                .create()
                .show();

    }

    @Override
    public void handleResult(final Result result) {
        final String scanResult = result.getText();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scannerView.resumeCameraPreview(ScannerBarcodeActivity.this);
            }
        });
        builder.setNeutralButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scanResult));
                //startActivity(intent);
                setCodeInAddProduct(scanResult);

                Toast.makeText(ScannerBarcodeActivity.this, scanResult, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setMessage(scanResult);
        AlertDialog alert = builder.create();
        alert.show();
    }


    static AddProductActivity addProductActivity;
    public void setParentObject(AddProductActivity addProductActivity) {
        this.addProductActivity = addProductActivity;
    }
    void setCodeInAddProduct(String scan) {
        if (addProductActivity != null) {
            addProductActivity.scannerResult(scan);
        }
    }
}
