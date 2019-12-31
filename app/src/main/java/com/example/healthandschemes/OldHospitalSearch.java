package com.example.healthandschemes;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import static android.icu.text.Normalizer.YES;

public class OldHospitalSearch extends AppCompatActivity {
     Button button,getLbutton;
     TextView showlocation;
     CheckBox t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
     String s1,lo,lal;
     int f1,f2,f3,f4,f5,f6,f7,f8,f9,f10;

     LocationManager locationManager;
     String latitude,longitude;
     private  static  final  int REQUEST_LOCATION=1;

     FirebaseDatabase firebase;
     DatabaseReference ref;
     ActionBar abr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldhospitalsearch);

        abr=getSupportActionBar();
        abr.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f07c1d")));

        firebase=FirebaseDatabase.getInstance();

        t1=findViewById(R.id.switch11);
        t2=findViewById(R.id.switch12);
        t3=findViewById(R.id.switch13);
        t4=findViewById(R.id.switch14);
        t5=findViewById(R.id.switch15);
        t6=findViewById(R.id.switch16);
        t7=findViewById(R.id.switch17);
        t8=findViewById(R.id.switch18);
        t9=findViewById(R.id.switch19);
        t10=findViewById(R.id.switch20);

        f1=0;
        f2=0;
        f3=0;
        f4=0;
        f5=0;
        f6=0;
        f7=0;
        f8=0;
        f9=0;
        f10=0;

        s1="";
        lal="";
        lo="";


        ActivityCompat.requestPermissions(OldHospitalSearch.this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION );

        showlocation=findViewById(R.id.textviewlc);
        getLbutton=findViewById(R.id.button4);
        button=findViewById(R.id.button3);

        getLbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

                if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    OnGPS();
                }
                else{
                    getLocation();
                }
            }

            private void getLocation() {
                if(ActivityCompat.checkSelfPermission(OldHospitalSearch.this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(OldHospitalSearch.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(OldHospitalSearch.this,new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION );
                }
                else
                {
                    Location LocationGPS=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

                    if(LocationGPS !=null)
                    {
                        double lat=LocationGPS.getLatitude();
                        double longi=LocationGPS.getLongitude();

                        latitude=String.valueOf(lat);
                        longitude=String.valueOf(longi);

                        lo=longitude;
                        lal=latitude;

                        showlocation.setText("LOCATION"+"\n"+"LATITUDE"+latitude+"\n"+"LONGITUDE"+longitude);
                    }
                    else if(LocationNetwork !=null)
                    {
                        double lat=LocationNetwork.getLatitude();
                        double longi=LocationNetwork.getLongitude();

                        latitude=String.valueOf(lat);
                        longitude=String.valueOf(longi);

                        lo=longitude;
                        lal=latitude;

                        showlocation.setText("LOCATION"+"\n"+"LATITUDE"+latitude+"\n"+"LONGITUDE"+longitude);
                    }
                    else if(LocationPassive !=null)
                    {
                        double lat=LocationPassive.getLatitude();
                        double longi=LocationPassive.getLongitude();

                        latitude=String.valueOf(lat);
                        longitude=String.valueOf(longi);

                        lo=longitude;
                        lal=latitude;

                        showlocation.setText("LOCATION"+"\n"+"LATITUDE"+latitude+"\n"+"LONGITUDE"+longitude);
                    }
                    else
                    {
                        Toast.makeText(OldHospitalSearch.this,"CANNOT DETECT",Toast.LENGTH_SHORT).show();
                    }

                }
            }

            private void OnGPS() {
                final AlertDialog.Builder builder=new AlertDialog.Builder(OldHospitalSearch.this);
                builder.setMessage("ENABLE GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                    }
                });
                final AlertDialog alertDialog=builder.create();
                alertDialog.show();

            }


        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.isChecked())
                {
                    f1=1;
                }
                else
                {
                    f1=0;
                }
            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.isChecked())
                {
                    f1=1;
                }
                else
                {
                    f1=0;
                }
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t2.isChecked())
                {
                    f2=1;
                }
                else
                {
                    f2=0;
                }
            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t3.isChecked())
                {
                    f3=1;
                }
                else
                {
                    f3=0;
                }
            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t4.isChecked())
                {
                    f4=1;
                }
                else
                {
                    f4=0;
                }
            }
        });

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t5.isChecked())
                {
                    f5=1;
                }
                else
                {
                    f5=0;
                }
            }
        });

        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t6.isChecked())
                {
                    f6=1;
                }
                else
                {
                    f6=0;
                }
            }
        });

        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t7.isChecked())
                {
                    f7=1;
                }
                else
                {
                    f7=0;
                }
            }
        });

        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t8.isChecked())
                {
                    f8=1;
                }
                else
                {
                    f8=0;
                }
            }
        });

        t9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t9.isChecked())
                {
                    f9=1;
                }
                else
                {
                    f9=0;
                }
            }
        });

        t10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t10.isChecked())
                {
                    f10=1;
                }
                else
                {
                    f10=0;
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(lo=="" && lal=="")
                {
                    Toast.makeText(OldHospitalSearch.this,"GET LOCATION AGAIN",Toast.LENGTH_SHORT).show();

                }


                else {
                    if (f1 == 1) {
                        s1 = s1 + "Y";
                    } else {
                        s1 = s1 + "N";
                    }


                    if (f2 == 1) {
                        s1 = s1 + "Y";
                    } else {
                        s1 = s1 + "N";
                    }


                    if (f3 == 1) {
                        s1 = s1 + "Y";
                    } else {
                        s1 = s1 + "N";
                    }

                    if (f4 == 1) {
                        s1 = s1 + "Y";
                    } else {
                        s1 = s1 + "N";
                    }

                    if (f5 == 1) {
                        s1 = s1 + "Y";
                    } else {
                        s1 = s1 + "N";
                    }

                    if (f6 == 1) {
                        s1 = s1 + "Y";
                    } else {
                        s1 = s1 + "N";
                    }

                    if (f7 == 1) {
                        s1 = s1 + "Y";
                    } else {
                        s1 = s1 + "N";
                    }

                    if (f8 == 1) {
                        s1 = s1 + "Y";
                    } else {
                        s1 = s1 + "N";
                    }

                    if (f9 == 1) {
                        s1 = s1 + "Y";
                    } else {
                        s1 = s1 + "N";
                    }

                    if (f10 == 1) {
                        s1 = s1 + "Y";
                    } else {
                        s1 = s1 + "N";
                    }


                    s1 = s1 + "/" + lal + "/" + lo;
                    Toast.makeText(OldHospitalSearch.this, "Data Sent.", Toast.LENGTH_SHORT).show();

                    try {
                        DatabaseReference data = firebase.getReference("DATAIMES");
                        data.child("REQ").setValue(s1);
                        s1="";
                        Toast.makeText(OldHospitalSearch.this, "Data Sent.1111", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                //do something
                            }
                        }, 2000 );//time in milisecond
                    }catch (Exception e){

                    }







                    //Toast.makeText(Ambulance.this, "Data Sent...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OldHospitalSearch.this, OldHospitalFound.class);
                    intent.putExtra("DATAIMES", s1);
                    startActivity(intent);
                }
            }
        });
    }
}
