package com.example.geek.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geek.R;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_calendar)
    Toolbar toolbar_calendar;
    @BindView(R.id.clv_calendar)
    CalendarView clv_calendar;
    @BindView(R.id.iv_calendar)
    ImageView iv_calendar;
    @BindView(R.id.tv_calendar)
    TextView tv_calendar;
    String string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar_calendar);
        clv_calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if (month<10){
                    if (dayOfMonth<10){
                        string = String.valueOf(year)+"0"+String.valueOf(month+1)+"0"+String.valueOf(dayOfMonth+1);
                    }else {
                        string = String.valueOf(year)+"0"+String.valueOf(month+1)+String.valueOf(dayOfMonth+1);
                    }
                }else {
                    if (dayOfMonth<10){
                        string = String.valueOf(year)+String.valueOf(month+1)+"0"+String.valueOf(dayOfMonth+1);
                    }else {
                        string = String.valueOf(year)+String.valueOf(month+1)+String.valueOf(dayOfMonth+1);
                    }
                }
            }
        });
    }

    @SuppressLint("SimpleDateFormat")
    @OnClick({R.id.tv_calendar, R.id.iv_calendar})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_calendar:
                Intent intent = getIntent();
                intent.putExtra("format",string);
                setResult(300,intent);
                finish();
                break;
            case R.id.iv_calendar:
                finish();
                break;
        }
    }
}
