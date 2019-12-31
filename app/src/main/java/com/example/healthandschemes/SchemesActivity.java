package com.example.healthandschemes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchemesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    FirebaseDatabase f1;
    String[] schemesApplicable;

    String[][] scheme_name = {

            {"ANDHRA PRADESH","Rajiv Aarogyasri"},
            {"ASSAM","Atal Amrit Abhiyan Scheme"},
            {"ASSAM","Free Operations for Children Having Congenital Heart Disease"},
            {"CHATTISGARH","Rashtriya Swasthya Bima Yojana"},
            {"GOA","Deen Dayal Swasthya Seva Yojana"},
            {"GUJARAT","Mamta Taruni Programme"},
            {"GUJARAT","Mukhyamantri Amrutum (MA) Yojana"},
            {"HARYANA","Rajiv Gandhi Pariwar Bima Yojna"},
            {"HIMACHAL PRADESH","HIMCARE"},
            {"KARNATAKA","Vajpayee Arogya Shree"},
            {"KERALA","Janani Suraksha Yojana (JSY)"},
            {"MADHYA PRADESH","Deen Dayal Antyoday Upchar Yojna"},
            {"MAHARASHTRA","Rajiv Gandhi Jeevandayee Arogya Yojana (RGJAY)"},
            {"MANIPUR","Chief Minister-gi Hakshelgi Tengbang (CMHT)"},
            {"MEGHALAYA","Megha Health Insurance Scheme (MHIS)"},
            {"MIZORAM","yushman Bharat – Pradhan Mantri Jan Arogya Yojana (AB-PMJAY)"},
            {"NAGALAND"," Ayushman Card or Pradhan Mantri Jan Arogya Yojana (PMJAY) "},
            {"NAGALAND","National Vector Borne Disease and Control Programme (NVBDCP)"},
            {"ODISHA"," Biju Swasthya Kalyan Yojana"},
            {"PUNJAB","RASHTRIYA BAL SWASTHYA KARYAKRAM (RBSK)"},
            {"RAJASTHAN","Ayushman Bharat Pradhan Mantri Jan Arogya Yojana"},
            {"RAJASTHAN","Bhamashah Swasthya Bima Yojana"},
            {"SIKKIM","Accredited Social Health Activist (ASHA)"},
            {"SIKKIM","Antyodaya Anna Yojana (AAY)"},
            {"TAMIL NADU","Chief Minister’s Comprehensive Health Insurance Scheme"},
            {"TRIPURA","Ayushman Bharat – PMJAY"},
            {"TRIPURA","Tripura Health Assurance Scheme for Poor (THASP)"},
            {"UTTARAKHAND","Rastriya Swastya Bima Yojna"},
            {"WEST BENGAL","West Bengal Cashless Medical Treatment Scheme"},
            {"WEST BENGAL","West Bengal Swasthyasathi Health Insurance Scheme"},

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemes);

        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_schemes, R.id.textviewList, schemesApplicable));

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        f1= FirebaseDatabase.getInstance();
        DatabaseReference mref=f1.getReference("Schemes").child("STATE SCHEMES").child("GOA/Deen Dayal Swasthya Seva Yojana");

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String msg[] = new String[9];
                String value = null;
                int i=0;
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    value = ds.getValue(String.class);
                    msg[i] = value;
                    i++;
                }
                value = "";
                for(i=1;i<4;i++){
                    value += msg[i] + "\n";
                }
                Scheme.description = msg[1];


                Toast.makeText(SchemesActivity.this, value, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


        final List<String> categories = new ArrayList<String>();
        for(int i=0; i<scheme_name.length;i++){
            categories.add(scheme_name[i][0]);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
