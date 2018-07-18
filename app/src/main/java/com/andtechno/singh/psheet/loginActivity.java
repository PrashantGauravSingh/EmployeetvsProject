package com.andtechno.singh.psheet;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;

public class loginActivity extends AppCompatActivity {

    EditText uname,passwd;
    Button Loginbtn;
    ProgressDialog progressDialog;
    static ArrayList<String> namelist = new ArrayList<>();
   static  ArrayList<String> positionlist = new ArrayList<>();
   static  ArrayList<String> Citylist = new ArrayList<>();
   static ArrayList<String> idList = new ArrayList<>();
   static  ArrayList<String> joinlist = new ArrayList<>();
   static  ArrayList<String> paylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Loginbtn=findViewById(R.id.btnLogin);
        uname=findViewById(R.id.username);
        passwd=findViewById(R.id.password);


    }

    public void signin(View view) {

        String usname=uname.getText().toString();
        String ppword=passwd.getText().toString();

        if(isInternetOn()) {
            if (!usname.isEmpty()) {
                if (!ppword.isEmpty()) {
                    fetchData();
                } else {
                    showAlert("Please input your password");
                }

            } else {
                showAlert("Please input your username");
            }
        }else {
            showAlert("No Internet Available");
        }

    }

    private void fetchData() {
        //todo Show progress dialog

        progressDialog = new ProgressDialog(loginActivity.this);
        progressDialog.setMessage("Sign in..."); // Setting Message
        progressDialog.setTitle(""); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);

        LoginBody body=new LoginBody();
        body.userName=uname.getText().toString();
        body.password=passwd.getText().toString();
        new ApiFacade().getApiService().getLoginData(body).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                //todo hide progress dialog

                if (!response.isSuccessful()) {
                    progressDialog.dismiss();
                    showAlert("Invalid Credentials!\nPlease input valid username and password.");
                    return;
                }
                Log.e( "kkk onResponse: ", "success");
                try {
                    String body = response.body().string();
                    // todo Convert data string to json object

                    Log.e("Body","message: "+body);

                    try {

                    JSONObject newObject = new JSONObject(body);
                  String data=  newObject.getString("TABLE_DATA");
                    JSONObject dataObject = new JSONObject(data);
                    JSONArray ja_data = dataObject.getJSONArray("data");
                    int length = ja_data.length();
                    Log.e("json Array",""+length);
                    for(int i=0;i<ja_data.length();i++){

                        JSONArray first = ja_data.getJSONArray(i);
                       for(int j=0;j<6;j++) {

                           if (j == 0)
                               namelist.add(first.getString(j));
                            else if (j == 1)
                               positionlist.add(first.getString(j));
                            else if (j == 2)
                               Citylist.add(first.getString(j));
                            else if (j == 3)
                               idList.add(first.getString(j));
                            else if (j == 4)
                               joinlist.add(first.getString(j));
                            else if (j == 5)
                               paylist.add(first.getString(j));
                       }

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                    progressDialog.dismiss();

             Intent mainIntent=new Intent(loginActivity.this,MainActivity.class);
                startActivity(mainIntent);
//            }
                 //   covertJsonDataToClass(body);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                //todo hide

                progressDialog.dismiss();
                showAlert("Invalid credentials!");
            }
        });
    }

   public void showAlert(String message){
       AlertDialog alertDialog = new AlertDialog.Builder(
               loginActivity.this).create();
       alertDialog.setTitle("Alert");
       alertDialog.setMessage(message);
       alertDialog.setIcon(R.drawable.ic_launcher_background);
       alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
           }
       });

       alertDialog.show();
    }

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

        //    Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

          //  Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    @Override
    public void onBackPressed() {

    }
}
