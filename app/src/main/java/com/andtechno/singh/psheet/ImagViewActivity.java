package com.andtechno.singh.psheet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.andtechno.singh.psheet.DetailedActivity.mphoto;

public class ImagViewActivity extends AppCompatActivity {
    ImageView bitImage,back;
    TextView timeSamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imag_view);

        bitImage=findViewById(R.id.imgVw);
        timeSamp=findViewById(R.id.timeStamp);
        back=findViewById(R.id.back);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String timeStamp = simpleDateFormat.format(new Date());
        timeSamp.setText("Time Stamp : "+timeStamp);
        bitImage.setImageBitmap(mphoto);


    }

    public void goBack(View view) {

        finish();
    }
}
