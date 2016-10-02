package com.nelitaaas.tugas2_prakmobpro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nelitaaas on 02/10/16.
 */
public class TangkapIntent extends AppCompatActivity {
    @BindView(R.id.tampilNim)TextView _tampilNim;
    @BindView(R.id.tampilNama)TextView _tampilNama;
    @BindView(R.id.lvMatkul)ListView _tampilMatkul;

    String nim, nama, cb1, cb2, cb3;
    StringBuilder s = new StringBuilder(3);
    ArrayList<String> matkul = new ArrayList<String>();
    ArrayAdapter adapter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_tangkap);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        Bundle b = getIntent().getExtras();

        nim = b.getString("kirimNim");
        nama = b.getString("kirimNama");
        cb1 = b.getString("kirimCb1");
        cb2 = b.getString("kirimCb2");
        cb3 = b.getString("kirimCb3");

        if (!cb1.equals("")) {
            matkul.add(cb1);
        }
        if (!cb2.equals("")) {
            matkul.add(cb2);
        }
        if (!cb3.equals("")) {
            matkul.add(cb3);
        }

        adapter = new ArrayAdapter<String>(this,R.layout.item_list, R.id.item, matkul);

        _tampilNim.setText(nim);
        _tampilNama.setText(nama);
        _tampilMatkul.setAdapter(adapter);

        //Toast.makeText(this, "NIM : " + nim + " \nNama :" + nama + "\nMatkul:" + s, Toast.LENGTH_LONG).show();
        //Toast.makeText(this, "Matkul : " +s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }
}
