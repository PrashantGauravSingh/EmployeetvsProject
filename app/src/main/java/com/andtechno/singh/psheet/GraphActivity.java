package com.andtechno.singh.psheet;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {



    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;
    int[] pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        chart =findViewById(R.id.piechart);

        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();
        AddValuesToBARENTRY();
        AddValuesToBarEntryLabels();
        Bardataset = new BarDataSet(BARENTRY, "Salary");
        BARDATA = new BarData(Bardataset);
        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        Bardataset.setValueTextColor(Color.WHITE);
        chart.setData(BARDATA);
        chart.animateY(5000);
        chart.setDrawGridBackground(false);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //bottom is good
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineColor(Color.rgb(29,233,182));
        YAxis yAxis = chart.getAxisLeft(); // upper part
        yAxis.setEnabled(false);
        yAxis = chart.getAxisRight(); // lower part
        yAxis.setEnabled(false);
        yAxis.setDrawAxisLine(true);
        yAxis.setAxisLineColor(Color.rgb(242, 44, 97));



    }

    public void AddValuesToBARENTRY(){

        String[] stringArray = loginActivity.paylist.toArray(new String[0]);
        for(int y=0;y<10;y++){ //Can draw graph for araay length( min is 10 employee)
            String myNew1=stringArray[y].replaceAll("[$-+.^:,]","");
            int sal=Integer.valueOf(myNew1);
            BARENTRY.add(new BarEntry(y, sal));
        }


    }

    public void AddValuesToBarEntryLabels(){

        BarEntryLabels.add("First");
        BarEntryLabels.add("Second");
        BarEntryLabels.add("Third");
        BarEntryLabels.add("Fourth");
        BarEntryLabels.add("Fifth");
        BarEntryLabels.add("sixth");

    }

}
