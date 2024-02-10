package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alertDialog = findViewById(R.id.show_allert_dialog);
        alertDialog.setOnClickListener(alert -> showAlertDialog());

        Button progressDialog = findViewById(R.id.show_progress_dialog);
        progressDialog.setOnClickListener(alert -> showProgressDialog());


    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.outline_error_24);
        builder.setTitle("Alert Dialog");
        builder.setMessage("This is an alert dialog.");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Show Alert Dialog Canceled", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Progress Dialog");
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Simulate a long-running task
        new Handler().postDelayed(() -> {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, "Task completed", Toast.LENGTH_SHORT).show();
        }, 3000);
    }


}