package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alertDialog = findViewById(R.id.show_allert_dialog);
        alertDialog.setOnClickListener(alert -> showAlertDialog());

        Button progressDialog = findViewById(R.id.show_progress_dialog);
        progressDialog.setOnClickListener(alert -> showProgressDialog());

        Button datePickerDialog = findViewById(R.id.show_datepicker_dialog);
        datePickerDialog.setOnClickListener(alert -> showDatePickerDialog());


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

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            // month is zero-based, so add 1 to get the correct month
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(MainActivity.this, "تاریخ انتخاب شده: " + selectedDate, Toast.LENGTH_SHORT).show();


        };

        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, dayOfMonth);
        datePickerDialog.show();
    }


}