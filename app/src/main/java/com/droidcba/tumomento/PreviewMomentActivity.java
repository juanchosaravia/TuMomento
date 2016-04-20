package com.droidcba.tumomento;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Calendar;

public class PreviewMomentActivity extends AppCompatActivity {

    private static final String EVENT_NAME = "eventName";
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";

    private int year;
    private int month;
    private int day;

    public static Intent newInstance(Context context, String eventName, int year, int month, int day) {
        Intent intent = new Intent(context, PreviewMomentActivity.class);
        Bundle b = new Bundle();
        b.putString(EVENT_NAME, eventName);
        b.putInt(YEAR, year);
        b.putInt(MONTH, month);
        b.putInt(DAY, day);
        intent.putExtras(b);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_moment);

        Bundle bundle = getIntent().getExtras();

        ((TextView) findViewById(R.id.tvTitle)).setText(bundle.getString(EVENT_NAME));

        year = bundle.getInt(YEAR);
        month = bundle.getInt(MONTH);
        day = bundle.getInt(DAY);

        // Set remaining days:
        Calendar selectedCalendar = Calendar.getInstance();
        selectedCalendar.set(year, month, day, 0, 0, 0);
        Calendar todayCalendar = Calendar.getInstance();

        // Move this to another thread:
        long daysBetween = 0;
        while (todayCalendar.before(selectedCalendar)) {
            todayCalendar.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        String remainingValue = String.format("%d dias", daysBetween);

        TextView remainingTime = (TextView) findViewById(R.id.tvRemainingTime);
        remainingTime.setText(remainingValue);
    }
}
