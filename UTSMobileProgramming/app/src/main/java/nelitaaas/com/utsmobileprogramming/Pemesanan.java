package nelitaaas.com.utsmobileprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by amarullah87 on 30/10/16.
 */
public class Pemesanan extends AppCompatActivity{

    String nama, deskripsi, harga;
    TextView txtKue;
    EditText txtUcapan,txtDari;
    Button btnSubmit;
    private Spinner spUntuk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pesanan);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        nama = getIntent().getStringExtra("nama");
        final int position = getIntent().getIntExtra("position",0);

        txtKue = (TextView)findViewById(R.id.textKue);
        txtKue.setText(nama);

        txtUcapan =(EditText)findViewById(R.id.kartuUcapan);
        txtDari = (EditText)findViewById(R.id.txtDari);
        spUntuk = (Spinner) findViewById(R.id.listUntuk);
        btnSubmit = (Button) findViewById(R.id.submit);
        ArrayList<String> daftar;
        daftar = new ArrayList<>();
        daftar.add("Friends");
        daftar.add("Brother");
        daftar.add("Sister");
        daftar.add("Parent");
        ArrayAdapter<String> jenisAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, daftar);
        assert spUntuk != null;
        spUntuk.setAdapter(jenisAdapter);
        spUntuk.setSelection(0);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ucapan = txtUcapan.getText().toString();
                String dari = txtDari.getText().toString();
                String pilihan = spUntuk.getSelectedItem().toString();

                Intent iFinish = new Intent(getApplicationContext(),FinishOrder.class);
                iFinish.putExtra("ucapan", ucapan);
                iFinish.putExtra("dari", dari);
                iFinish.putExtra("pilihan",pilihan);
                iFinish.putExtra("position",position);
                startActivity(iFinish);

                finish();
            }
        });

    }
}
