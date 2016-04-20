package com.droidcba.tumomento;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class CreateCountdownActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView dateSelected;
    private View createMoment;
    private DatePickerDialog datePicker;
    private int year;
    private int month;
    private int day;
    private EditText title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_countdown);

        // Initialize date for today:
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);

        // Configure Date picker with pre-selected today date:
        datePicker = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));

        // Init dateSelected with today date:
        dateSelected = (TextView) findViewById(R.id.tvDateSelected);
        dateSelected.setText(getDateWithFormat(year, month, day));
        dateSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show();
            }
        });

        title = (EditText) findViewById(R.id.title);

        // Configure next button:
        createMoment = findViewById(R.id.create);
        createMoment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = PreviewMomentActivity.newInstance(
                        CreateCountdownActivity.this,
                        title.getText().toString(),
                        year, month, day);
                startActivity(intent);
            }
        });
    }

    private String getDateWithFormat(int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
        return String.format("%02d/%02d/%d", dayOfMonth, monthOfYear, year);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissDatePicker();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        dateSelected.setText(getDateWithFormat(year, monthOfYear, dayOfMonth));
        dismissDatePicker();
    }

    private void dismissDatePicker() {
        if (datePicker != null && datePicker.isShowing()) {
            datePicker.dismiss();
        }
    }
}
