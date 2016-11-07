package com.nelitaaas.tugas4;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ListView listView;
    public static MainActivity me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        me = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iTambah = new Intent(getApplicationContext(), TambahData.class);
                startActivity(iTambah);
            }
        });
        dbHelper = new DBHelper(this);
        dbHelper.openDB();
        refreshData();
    }

    public void refreshData() {
        listView = (ListView)findViewById(R.id.lvMahasiswa);
        final Cursor cursor = dbHelper.getAllRecords();
        final String[] from = new String[]{"_id", DBHelper.NAMA, DBHelper.JURUSAN, DBHelper.ANGKATAN};
        int[] to = new int[]{R.id.txtNim, R.id.txtNama, R.id.txtJurusan, R.id.txtAngkatan};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_list, cursor, from, to);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //if(cursor.moveToPosition(position)){
                final String nim = cursor.getString(cursor.getColumnIndex("_id"));
                //}
                //Toast.makeText(MainActivity.this, nim, Toast.LENGTH_SHORT).show();

                final CharSequence[] dialogItem = {"Lihat Biodata", "Update", "Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item){
                            case 0:
                                Intent iLihat = new Intent(getApplicationContext(), LihatBiodata.class);
                                iLihat.putExtra("nim", nim);
                                startActivity(iLihat);
                                break;

                            case 1:
                                Intent iUpdate = new Intent(getApplicationContext(), UpdateBiodata.class);
                                iUpdate.putExtra("nim", nim);
                                startActivity(iUpdate);
                                break;

                            case 2:
                                new AlertDialog.Builder(MainActivity.this)
                                        .setIcon(R.drawable.ic_warning)
                                        .setTitle("Hapus Data")
                                        .setMessage("Anda yakin akan Menghapus " + nim + " ?")
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dbHelper.deleteData(nim);
                                                finish();
                                                startActivity(getIntent());
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
