package com.nelitaaas.tugas4;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by amarullah87 on 06/11/16.
 */
public class TambahData extends AppCompatActivity{

    static final int DATE_DIALOG_ID = 999;
    private int year, month, day, year2, month2, day2;
    EditText nim, nama, tgl, jk, alamat, jurusan, angkatan;
    Button simpan;
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DBHelper(getApplicationContext());

        nim = (EditText)findViewById(R.id.nim);
        nama = (EditText)findViewById(R.id.nama);
        tgl = (EditText)findViewById(R.id.tgl);
        jk = (EditText)findViewById(R.id.jk);
        alamat = (EditText)findViewById(R.id.alamat);
        jurusan = (EditText)findViewById(R.id.jurusan);
        angkatan = (EditText)findViewById(R.id.angkatan);

        tgl.setInputType(InputType.TYPE_NULL);
        tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        simpan = (Button) findViewById(R.id.btSimpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nim2, nama2, tgl2, jk2, alamat2, jurusan2, angkatan2;
                nim2 = nim.getText().toString();
                nama2 = nama.getText().toString();
                tgl2 = tgl.getText().toString();
                jk2 = jk.getText().toString();
                alamat2 = alamat.getText().toString();
                jurusan2 = jurusan.getText().toString();
                angkatan2 = angkatan.getText().toString();

                dbHelper.insertData(
                        nim2,
                        nama2,
                        tgl2,
                        jk2,
                        alamat2,
                        jurusan2,
                        angkatan2
                );
                MainActivity.me.refreshData();
                finish();
            }
        });

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR); year2 = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH); month2 = calendar.get(Calendar.MONTH);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth + 1;
            day = selectedDay;

            String sMonth, sDay;
            if(month < 10){
                sMonth = "0" + month;
            }else{
                sMonth = String.valueOf(month);
            }

            if(day < 10){
                sDay = "0" + day;
            }else{
                sDay = String.valueOf(day);
            }

            //Toast.makeText(Pemesanan.this, year +"/"+month+"/"+day, Toast.LENGTH_SHORT).show();
            // set selected date into textview
            tgl.setText(year +"-"+sMonth+"-"+sDay);

        }
    };
}
