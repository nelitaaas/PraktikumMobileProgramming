package es.esy.hangatsenja.post;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity implements View.OnClickListener {
    private AddBarangTask aksiSimpan = null;
    // URL to get contacts JSON
    private static String url = "http://dthan.net/json_barang.php";
    // JSON Node names
    private static final String TAG_BARANGINFO = "data";
    private static final String TAG_KODE = "kode_barang";
    private static final String TAG_NAMA = "nama_barang";
    private static final String TAG_HARGA = "harga";
    // private ListAdapter daftar;
    private String item;
    private ListView listView;
    Button btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTambah = (Button) findViewById(R.id.btnTambahBarang);
        btnTambah.setOnClickListener(this);
// Calling async task to get json
        new GetBarang().execute();

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, AddBarang.class);
        startActivity(i);
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetBarang extends AsyncTask<Void, Void, Void> {
        // Hashmap for ListView
        ArrayList<HashMap<String, String>> studentList;
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
// Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
// Creating service handler class instance
            Koneksi webreq = new Koneksi();
// Making a request to url and getting response
            String jsonStr = webreq.makeWebServiceCall(url, Koneksi.GET);
            Log.d("Response: ", "> " + jsonStr);
            studentList = ParseJSON(jsonStr);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
// Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
/**
 * Updating parsed JSON data into ListView
 * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, studentList,
                    R.layout.list_item, new String[]{TAG_NAMA, TAG_KODE, TAG_HARGA}, new int[]{R.id.nama, R.id.kodeBarang, R.id.harga});
            setListAdapter(adapter);
            listView = getListView();
            listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id) {
// item = (String) parent.getItemAtPosition(position);
                HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
                final String nama = map.get(TAG_NAMA);
                final String kode = map.get(TAG_KODE);
                final String harga = map.get(TAG_HARGA);

                /*Intent update = new Intent(MainActivity.this, UpdateBarang.class);
                update.putExtra("kode", kode);
                update.putExtra("nama", nama);
                update.putExtra("harga", harga);
                startActivity(update);*/

                final CharSequence[] dialogItem = {"Detail", "Update", "Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, final int item) {
                        switch (item){
                            case 0:
                                Intent iLihat = new Intent(MainActivity.this, DetailBarang.class);
                                iLihat.putExtra("kode", kode);
                                iLihat.putExtra("nama", nama);
                                iLihat.putExtra("harga", harga);
                                startActivity(iLihat);
                                break;

                            case 1:
                                Intent iUpdate = new Intent(MainActivity.this, UpdateBarang.class);
                                iUpdate.putExtra("kode", kode);
                                iUpdate.putExtra("nama", nama);
                                iUpdate.putExtra("harga", harga);
                                startActivity(iUpdate);
                                break;

                            case 2:
                                new AlertDialog.Builder(MainActivity.this)
                                        .setIcon(R.drawable.ic_warning)
                                        .setTitle("Hapus Data")
                                        .setMessage("Anda yakin akan Menghapus " + nama + " ?")
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                aksiSimpan = new AddBarangTask(kode);
                                                aksiSimpan.execute((Void) null);
                                            }
                                        })
                                        .setNegativeButton("Tidak", null)
                                        .show();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
            ;
        }
    }

    private ArrayList<HashMap<String, String>> ParseJSON(String json) {
        if (json != null) {
            try {
// Hashmap for ListView
                ArrayList<HashMap<String, String>> barangList = new
                        ArrayList<HashMap<String, String>>();
                JSONObject jsonObj = new JSONObject(json);
// Getting JSON Array node
                JSONArray dataBarang = jsonObj.getJSONArray(TAG_BARANGINFO);
// looping through All Students
                for (int i = 0; i < dataBarang.length(); i++) {
                    JSONObject c = dataBarang.getJSONObject(i);
                    String nama = "Nama Barang : " + c.getString(TAG_NAMA);
                    String kode = "Kode : " + c.getString(TAG_KODE);
                    String harga = "Harga : Rp. " + c.getString(TAG_HARGA);
// tmp hashmap for single student
                    HashMap<String, String> barang = new HashMap<String, String>();
// adding each child node to HashMap key => value
                    barang.put(TAG_NAMA, nama);
                    barang.put(TAG_KODE, kode);
                    barang.put(TAG_HARGA, harga);
                    barangList.add(barang);
                }
                return barangList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
            return null;
        }
    }

    private class AddBarangTask extends AsyncTask<Void, Void, Boolean>{
        String mKodeBarang;

        public AddBarangTask(String kode) {
            mKodeBarang = kode;
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
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String text = "";
            BufferedReader reader=null;
// Send data
            try
            {
// Defined URL where to send data
                URL url = new URL("http://dthan.net/delete_barang.php");
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
                Toast.makeText(MainActivity.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
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