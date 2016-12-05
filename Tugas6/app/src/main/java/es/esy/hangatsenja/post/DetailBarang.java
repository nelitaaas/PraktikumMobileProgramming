package es.esy.hangatsenja.post;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailBarang extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        String setKode = getIntent().getStringExtra("kode");
        String setNama = getIntent().getStringExtra("nama");
        String setHarga = getIntent().getStringExtra("harga");

        EditText kode = (EditText) findViewById(R.id.kode_barang_ed);
        EditText nama = (EditText) findViewById(R.id.nama_barang_ed);
        EditText harga = (EditText) findViewById(R.id.harga_ed);

        assert kode != null;
        kode.setText(setKode); //kode.setVisibility(View.INVISIBLE);
        assert nama != null;
        nama.setText(setNama); //nama.setVisibility(View.INVISIBLE);
        assert harga != null;
        harga.setText(setHarga); //harga.setVisibility(View.INVISIBLE);

        Button btnEdit = (Button) findViewById(R.id.btnEdit);
        assert btnEdit != null;
        btnEdit.setVisibility(View.GONE);
    }
}
