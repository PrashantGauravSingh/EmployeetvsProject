package com.andtechno.singh.psheet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailedActivity extends AppCompatActivity {

    public static final int CAMERA_REQUEST = 1888;
    public ImageView mimageView;
    public TextView timestamp;
   public static  Bitmap mphoto;
    EditText Name,Position,City,EmpId,Salary,DOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Bundle extras = getIntent().getExtras();
       int position= (int)extras.get("position");
       Log.e("Position",""+position);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mimageView=findViewById(R.id.image_from_camera);
        Name=findViewById(R.id.etMName);
        Position=findViewById(R.id.etPosition);
        City=findViewById(R.id.etcity);
        EmpId=findViewById(R.id.EmployeID);
        Salary=findViewById(R.id.etMLocation);
        timestamp=findViewById(R.id.timestamp);
        DOB=findViewById(R.id.etDob);

        FloatingActionButton fab = findViewById(R.id.fab);

        Name.setText(loginActivity.namelist.get(position));
        Position.setText(loginActivity.positionlist.get(position));
        City.setText(loginActivity.Citylist.get(position));
        EmpId.setText(loginActivity.idList.get(position));
        Salary.setText(loginActivity.paylist.get(position));
        DOB.setText(loginActivity.joinlist.get(position));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            mphoto = (Bitmap) data.getExtras().get("data");
            Intent newIntent=new Intent(DetailedActivity.this,ImagViewActivity.class);
            startActivity(newIntent);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
            String timeStamp = simpleDateFormat.format(new Date());
        }
    }

}
