package com.nelitaaas.tugas4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by amarullah87 on 07/11/16.
 */
public class UpdateBiodata extends AppCompatActivity{

    EditText nama, tgl, jk, alamat, jurusan, angkatan;
    TextView nim;
    Button simpan;
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_biodata);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DBHelper(getApplicationContext());
        final String getNim = getIntent().getStringExtra("nim");

        nim = (TextView) findViewById(R.id.nim);
        nama = (EditText)findViewById(R.id.nama);
        tgl = (EditText)findViewById(R.id.tgl);
        jk = (EditText)findViewById(R.id.jk);
        alamat = (EditText)findViewById(R.id.alamat);
        jurusan = (EditText)findViewById(R.id.jurusan);
        angkatan = (EditText)findViewById(R.id.angkatan);

        HashMap mhs = dbHelper.getBioDetail(getNim);

        String nama2 = String.valueOf(mhs.get("nama"));
        String tgl2 = String.valueOf(mhs.get("tgl"));
        String jk2 = String.valueOf(mhs.get("jk"));
        String alamat2 = String.valueOf(mhs.get("alamat"));
        String jurusan2 = String.valueOf(mhs.get("jurusan"));
        String angkatan2 = String.valueOf(mhs.get("angkatan"));

        nim.setText(getNim);
        nama.setText(nama2);
        tgl.setText(tgl2);
        jk.setText(jk2);
        alamat.setText(alamat2);
        jurusan.setText(jurusan2);
        angkatan.setText(angkatan2);

        simpan = (Button) findViewById(R.id.btSimpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nim2 = nim.getText().toString();
                String nama2 = nama.getText().toString();
                String tgl2 = tgl.getText().toString();
                String jk2 = jk.getText().toString();
                String alamat2 = alamat.getText().toString();
                String jurusan2 = jurusan.getText().toString();
                String angkatan2 = angkatan.getText().toString();

                //Toast.makeText(UpdateBiodata.this, jk2 + " " + angkatan2, Toast.LENGTH_SHORT).show();

                dbHelper.updateData(
                        nim2,
                        nama2,
                        tgl2,
                        jk2,
                        alamat2,
                        jurusan2,
                        angkatan2
                );


                Toast.makeText(UpdateBiodata.this, "Data Berhasil di Update", Toast.LENGTH_SHORT).show();
                MainActivity.me.refreshData();
                finish();
            }
        });
    }
}
