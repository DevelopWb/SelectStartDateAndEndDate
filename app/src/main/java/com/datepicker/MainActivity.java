package com.datepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.datepicker.PublicUtils.GetWeekFromDate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 年月日
     */
    private TextView mStartTimeTv;
    private RelativeLayout mStartTimeRl;
    private DatePicker mStartDp;
    /**
     * 年月日
     */
    private TextView mEndTimeTv;
    private RelativeLayout mEndTimeRl;
    private DatePicker mEndDp;
    private LinearLayout mCustomContentLl;
    private int yearStart;
    private int monthStart;
    private int dayStart;
    private int year_end;
    private int month_end;
    private int day_end;
    private String date_start_str;
    private String date_end_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDatePicker();
    }

    private void initView() {
        mStartTimeTv = (TextView) findViewById(R.id.start_time_tv);
        mStartTimeRl = (RelativeLayout) findViewById(R.id.start_time_rl);
        mStartTimeRl.setOnClickListener(this);
        mStartDp = (DatePicker) findViewById(R.id.start_dp);
        mEndTimeTv = (TextView) findViewById(R.id.end_time_tv);
        mEndTimeRl = (RelativeLayout) findViewById(R.id.end_time_rl);
        mEndTimeRl.setOnClickListener(this);
        mEndDp = (DatePicker) findViewById(R.id.end_dp);
        mCustomContentLl = (LinearLayout) findViewById(R.id.custom_content_ll);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_time_rl:
                changeDatePickerStatus(mStartDp, mEndDp);
                break;
            case R.id.end_time_rl:
                changeDatePickerStatus(mEndDp, mStartDp);
                break;
            default:
                break;
        }
    }
    /**
     * 初始化日期
     */
    private void initCalender() {
        Calendar calendar_end = Calendar.getInstance();
        calendar_end.add(Calendar.DAY_OF_YEAR,7);
        Date date_end = calendar_end.getTime();
        SimpleDateFormat form = new SimpleDateFormat("yyyy年MM月dd日");
        String ymd = form.format(date_end);
        date_end_str = ymd;
        String week = GetWeekFromDate(ymd);
        mEndTimeTv.setText(ymd + " " + week);
        year_end = calendar_end.get(Calendar.YEAR);
        month_end = calendar_end.get(Calendar.MONTH);
        day_end = calendar_end.get(Calendar.DAY_OF_MONTH);

        Calendar calendar_start = Calendar.getInstance();
        calendar_start.add(Calendar.MONTH, 0);
        Date date_start = calendar_start.getTime();
        String ymd_start = form.format(date_start);
        date_start_str = ymd_start;
        String week_start = GetWeekFromDate(ymd_start);
        mStartTimeTv.setText(ymd_start + " " + week_start);
        yearStart = calendar_start.get(Calendar.YEAR);
        monthStart = calendar_start.get(Calendar.MONTH);
        dayStart = calendar_start.get(Calendar.DAY_OF_MONTH);
    }
    /**
     * 初始化DatePicker
     */
    private void initDatePicker() {
        initCalender();
        mStartDp.init(yearStart, monthStart, dayStart, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String str_start = getStringTypeOfDate(year, monthOfYear + 1, dayOfMonth);
                mStartTimeTv.setText(str_start);
                date_start_str = new StringBuffer().append(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日").toString();
            }
        });
        mEndDp.init(year_end, month_end, day_end, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String str_end = getStringTypeOfDate(year, monthOfYear + 1, dayOfMonth);
                date_end_str = new StringBuffer().append(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日").toString();
                mEndTimeTv.setText(str_end);
            }
        });
    }

    /**
     * 改变两个DatePicker的状态
     *
     * @param displayDP
     * @param unDisplayDp
     */
    private void changeDatePickerStatus(DatePicker displayDP, DatePicker unDisplayDp) {
        displayDP.setVisibility(View.VISIBLE);
        unDisplayDp.setVisibility(View.GONE);
    }
    /**
     * 将年月日改成特定格式的字符串
     */
    private String getStringTypeOfDate(int year, int month, int day) {
        StringBuffer sb = new StringBuffer();
        if (month < 10) {
            sb.append(year + "年" + "0" + month + "月" + day + "日");
        } else {
            sb.append(year + "年" + month + "月" + day + "日");
        }
        String week = GetWeekFromDate(sb.toString());
        sb.append(" " + week);
        return sb.toString();
    }
}
