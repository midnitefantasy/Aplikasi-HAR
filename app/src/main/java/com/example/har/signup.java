package com.example.har;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button btnSignUp,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnSignUp = (Button)findViewById(R.id.btnSign);
        btnBack = (Button)findViewById(R.id.btnBack);
        Spinner spin = (Spinner) findViewById(R.id.jeniskelamin);
        spin.setOnItemSelectedListener(this);
        String[] jk = {"Pilih", "Laki-laki", "Perempuan"};
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,jk);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), "Selected : " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0){
    }


}