package com.nelitaaas.tugas4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by amarullah87 on 07/11/16.
 */
public class LihatBiodata extends AppCompatActivity{

    TextView nim, nama, tgl, jk, alamat, jurusan, angkatan;
    Button kembali;
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihat_biodata);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DBHelper(getApplicationContext());
        String getNim = getIntent().getStringExtra("nim");

        nim = (TextView) findViewById(R.id.nim);
        nama = (TextView)findViewById(R.id.nama);
        tgl = (TextView)findViewById(R.id.tgl);
        jk = (TextView)findViewById(R.id.jk);
        alamat = (TextView)findViewById(R.id.alamat);
        jurusan = (TextView)findViewById(R.id.jurusan);
        angkatan = (TextView)findViewById(R.id.angkatan);

        kembali = (Button) findViewById(R.id.btKembali);

        HashMap mhs = dbHelper.getBioDetail(getNim);
        String nim2 = String.valueOf(mhs.get("nim"));
        String nama2 = String.valueOf(mhs.get("nama"));
        String tgl2 = String.valueOf(mhs.get("tgl"));
        String jk2 = String.valueOf(mhs.get("jk"));
        String alamat2 = String.valueOf(mhs.get("alamat"));
        String jurusan2 = String.valueOf(mhs.get("jurusan"));
        String angkatan2 = String.valueOf(mhs.get("angkatan"));

        nim.setText(nim2);
        nama.setText(nama2);
        tgl.setText(tgl2);
        jk.setText(jk2);
        alamat.setText(alamat2);
        jurusan.setText(jurusan2);
        angkatan.setText(angkatan2);

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.me.refreshData();
                finish();
            }
        });
    }
}