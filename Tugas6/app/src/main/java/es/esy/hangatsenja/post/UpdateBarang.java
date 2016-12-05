package es.esy.hangatsenja.post;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class UpdateBarang extends AppCompatActivity{

    private AddBarangTask aksiSimpan = null;

    private EditText kode, nama, harga;
    private String setKode, setNama, setHarga, getKode, getNama, getHarga;
    private Button btnEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        setKode = getIntent().getStringExtra("kode");
        setNama = getIntent().getStringExtra("nama");
        setHarga = getIntent().getStringExtra("harga");

        kode = (EditText) findViewById(R.id.kode_barang_ed);
        nama = (EditText) findViewById(R.id.nama_barang_ed);
        harga = (EditText) findViewById(R.id.harga_ed);

        kode.setText(setKode); nama.setText(setNama); harga.setText(setHarga);
        getNama = nama.getText().toString();
        getHarga = harga.getText().toString();
        
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aksiSimpan = new AddBarangTask(setKode, getNama, getHarga);
                aksiSimpan.execute((Void) null);
            }
        });
    }

    private class AddBarangTask extends AsyncTask<Void, Void, Boolean>{
        String mKodeBarang;
        String mNamaBarang;
        String mHargaBarang;

        public AddBarangTask(String setKode, String getNama, String getHarga) {
            mKodeBarang = setKode;
            mNamaBarang = getNama;
            mHargaBarang = getHarga;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
// Create data variable for sent values to server
            String data = null;
            try {
//Set Data Post
                data = URLEncoder.encode("kode", "UTF-8")
                        + "=" + URLEncoder.encode(mKodeBarang, "UTF-8");
                data += "&" + URLEncoder.encode("nama", "UTF-8") + "="
                        + URLEncoder.encode(mNamaBarang, "UTF-8");
                data += "&" + URLEncoder.encode("harga", "UTF-8")
                        + "=" + URLEncoder.encode(mHargaBarang, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String text = "";
            BufferedReader reader=null;
// Send data
            try
            {
// Defined URL where to send data
                URL url = new URL("http://dthan.net/update_barang.php");
// Send POST data request
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new
                        OutputStreamWriter(conn.getOutputStream());
                wr.write( data );
                wr.flush();
// Get the server response
                reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;
// Read Server Response
                while((line = reader.readLine()) != null)
                {
// Append server response in string
                    sb.append(line + "\n");
                }
            }
            catch(Exception ex)
            {
            }
            finally
            {
                try
                {
                    reader.close();
                }
                catch(Exception ex) {}
            }
// TODO: register the new account here.
            return true;
        }
        @Override
        protected void onPostExecute(final Boolean success) {
            aksiSimpan = null;
            if (success) {
                finish();
                Toast.makeText(UpdateBarang.this, "Data Berhasil Diubah!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(
                        getApplicationContext(),"gagal",Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected void onCancelled() {
            aksiSimpan = null;
        }
    }
}
