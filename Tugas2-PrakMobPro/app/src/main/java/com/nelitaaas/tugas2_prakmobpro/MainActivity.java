package com.nelitaaas.tugas2_prakmobpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtNim) EditText _txtNim;
    @BindView(R.id.txtNama)EditText _txtNama;
    @BindView(R.id.matkul1)CheckBox _matkul1;
    @BindView(R.id.matkul2)CheckBox _matkul2;
    @BindView(R.id.matkul3)CheckBox _matkul3;
    @BindView(R.id.btSubmit)Button _btSubmit;

    String nim , nama , cb1 , cb2, cb3 , mt1, mt2, mt3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        ButterKnife.bind(this);

        cb1 = _matkul1.getText().toString();
        cb2 = _matkul2.getText().toString();
        cb3 = _matkul3.getText().toString();

        mt1="";
        mt2="";
        mt3="";

        _matkul1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_matkul1.isChecked()){
                    mt1=cb1;
                }else{
                    mt1="";
                }
            }
        });

        _matkul2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_matkul2.isChecked()){
                    mt2=cb2;
                }else{
                    mt2="";
                }
            }
        });

        _matkul3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_matkul3.isChecked()){
                    mt3=cb3;
                }else {
                    mt3="";
                }
            }
        });
        _btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nim = _txtNim.getText().toString();
                nama = _txtNama.getText().toString();

                if (nim.equals("") || nama.equals("")){
                    Toast.makeText(MainActivity.this, "Nama / Nim Harus Diisi", Toast.LENGTH_SHORT).show();
                }else if (mt1.equals("") && mt2.equals("") && mt3.equals("")){
                    Toast.makeText(MainActivity.this, "Minimal 1 Matkul", Toast.LENGTH_SHORT).show();
                }else {
                    Bundle b = new Bundle();

                    b.putString("kirimNim",nim);
                    b.putString("kirimNama", nama);
                    b.putString("kirimCb1", mt1);
                    b.putString("kirimCb2", mt2);
                    b.putString("kirimCb3", mt3);

                    Intent intentKirim = new Intent(MainActivity.this, TangkapIntent.class);
                    intentKirim.putExtras(b);
                    startActivity(intentKirim);
                }
            }
        });
    }
}
