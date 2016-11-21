package com.nelitaaas.tugas5;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    private static String url = "http://dthan.net/json_mhs.php";
    // JSON
    private static String TAG_STUDENTINFO = "data";
    private static String TAG_NIM = "nim";
    private static String TAG_NAMA = "nm_pd";
    private static String TAG_ANGKATAN = "angkatan";
    private static String TAG_TGL_LAHIR = "tgl_lahir";
    private static String TAG_NAMA_AYAH = "nm_ayah";
    private static String TAG_NAMA_IBU = "nm_ibu_kandung";
    private static String TAG_ALAMAT = "jln";

    ArrayList<HashMap<String, String>> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetStudents().execute();
    }

    private class GetStudents extends AsyncTask<Void, Void, Void>{

        ArrayList<HashMap<String, String>> studentList;
        ProgressDialog pDialog;
        ListView lv = getListView();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
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
                    R.layout.list_item, new String[]{TAG_NAMA, TAG_NIM,
                    TAG_ANGKATAN}, new int[]{R.id.nama,
                    R.id.nim, R.id.angkatan});
            setListAdapter(adapter);
        }
    }

    private ArrayList<HashMap<String, String>> ParseJSON(String json) {
        if (json != null) {
            try {
                // Hashmap for ListView
                JSONObject jsonObj = new JSONObject(json);
                JSONArray students = jsonObj.getJSONArray(TAG_STUDENTINFO);
                // looping through All Students
                for (int i = 0; i < students.length(); i++) {
                    JSONObject c = students.getJSONObject(i);
                    String nama = c.getString(TAG_NAMA);
                    String nim = c.getString(TAG_NIM);
                    String angkatan = c.getString(TAG_ANGKATAN);
                    String tgl_lahir = c.getString(TAG_TGL_LAHIR);
                    String alamat = c.getString(TAG_ALAMAT);
                    String ayah = c.getString(TAG_NAMA_AYAH);
                    String ibu = c.getString(TAG_NAMA_IBU);

                    // tmp hashmap for single student
                    HashMap<String, String> student = new HashMap<>();

                    // adding each child node to HashMap key => value
                    student.put(TAG_NAMA, nama.replace("-", " "));
                    student.put(TAG_NIM, nim);
                    student.put(TAG_ANGKATAN, angkatan);
                    student.put(TAG_TGL_LAHIR, tgl_lahir);
                    student.put(TAG_ALAMAT, alamat.replace("-", " "));
                    student.put(TAG_NAMA_AYAH, ayah);
                    student.put(TAG_NAMA_IBU, ibu);

                    // adding student to students list
                    studentList.add(student);
                }
                return studentList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
            return null;
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        HashMap<String, String> data = studentList.get(position);
        String nim = data.get(TAG_NIM);
        String nama = data.get(TAG_NAMA);
        String angkatan = data.get(TAG_ANGKATAN);
        String tgl_lahir = data.get(TAG_TGL_LAHIR);
        String alamat = data.get(TAG_ALAMAT);
        String ayah = data.get(TAG_NAMA_AYAH);
        String ibu = data.get(TAG_NAMA_IBU);

        Intent detail = new Intent(getApplicationContext(), DetailActivity.class);
        detail.putExtra("nim",nim);
        detail.putExtra("nama",nama);
        detail.putExtra("angkatan",angkatan);
        detail.putExtra("tgl_lahir",tgl_lahir);
        detail.putExtra("alamat",alamat);
        detail.putExtra("ayah",ayah);
        detail.putExtra("ibu",ibu);
        startActivity(detail);
    }
}
