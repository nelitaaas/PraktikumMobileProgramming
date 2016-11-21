package com.nelitaaas.tutorialservice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by amarullah87 on 08/11/16.
 */
public class LihatMahasiswa extends AppCompatActivity{

    @BindView(R.id.nim) EditText _nim;
    @BindView(R.id.nama) EditText _nama;
    @BindView(R.id.alamat) EditText _alamat;
    @BindView(R.id.jurusan) EditText _jurusan;
    @BindView(R.id.angkatan) EditText _angkatan;
    @BindView(R.id.btSimpan) Button _btSimpan;
    @BindView(R.id.parentLayout) CoordinatorLayout _parent;
    private final OkHttpClient client = new OkHttpClient();

    Response response;
    String respon, nim;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihat_mahasiswa);
        ButterKnife.bind(this);

        nim = getIntent().getStringExtra("nim");
        loadDetailMahasiswa(nim);
    }

    private void loadDetailMahasiswa(String nim) {

    }
}
