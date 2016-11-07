package com.nelitaaas.tugas4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by amarullah87 on 06/11/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "db_mahasiswa";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "biodata";
    public static final String TABLE_NAME2 = "login";

    public static final String NIM = "_nim";
    public static final String NAMA = "nama";
    public static final String TGL = "tgl";
    public static final String JK = "jk";
    public static final String ALAMAT = "alamat";
    public static final String JURUSAN = "jurusan";
    public static final String ANGKATAN = "angkatan";

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    private SQLiteDatabase MyDB;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE " + TABLE_NAME + "(" +
                        NIM + " VARCHAR(10) PRIMARY KEY, " +
                        NAMA + " VARCHAR(100), " +
                        TGL + " DATE, " +
                        JK + " VARCHAR(10), " +
                        ALAMAT + " VARCHAR(200), " +
                        JURUSAN + " VARCHAR(20), " +
                        ANGKATAN + " VARCHAR(4) " +
                ") " ;

        String queryLogin = "CREATE TABLE " + TABLE_NAME2 + "(" +
                USERNAME + " VARCHAR(20) PRIMARY KEY, " +
                PASSWORD + " VARCHAR(20) " +
                ") " ;

        String queryInsertLogin = "INSERT INTO " +TABLE_NAME2+ " VALUES ('admin','admin')";

        db.execSQL(query);
        db.execSQL(queryLogin);
        db.execSQL(queryInsertLogin);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void openDB(){
        MyDB = getWritableDatabase();
    }

    public void closeDB(){
        if (MyDB !=null && MyDB.isOpen()){
            MyDB.close();
        }
    }

    public void insertData(String nim, String nama, String tgl,
                           String jk, String alamat, String jurusan, String angkatan){
        MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NIM, nim);
        values.put(NAMA, nama);
        values.put(TGL, tgl);
        values.put(JK, jk);
        values.put(ALAMAT, alamat);
        values.put(JURUSAN, jurusan);
        values.put(ANGKATAN, angkatan);
        /*String query = "INSERT INTO " + TABLE_NAME + "VALUES ('"+nim+"', '"+nama+"', '"+tgl+"', " +
                "'"+jk+"', '"+alamat+"', '"+jurusan+"', '"+angkatan+"')";*/

        MyDB.insert(TABLE_NAME, null, values);
        MyDB.close();
    }

    public void updateData(String nim, String nama, String tgl,
                           String jk, String alamat, String jurusan, String angkatan){

        MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAMA,nama);
        values.put(TGL,tgl);
        values.put(JK,jk);
        values.put(ALAMAT,alamat);
        values.put(JURUSAN,jurusan);
        values.put(ANGKATAN,angkatan);

        String where = NIM+ " = '" +nim+ "'";
        MyDB.update(TABLE_NAME, values, where, null);
        MyDB.close();
    }

    public void deleteData(String nim){
        MyDB = this.getWritableDatabase();

        String where = NIM+ " = '" +nim+ "'";

        MyDB.delete(TABLE_NAME, where, null);
        MyDB.close();
    }

    public Cursor getAllRecords(){
        String query = "SELECT _nim AS _id, nama, tgl, jk, alamat, jurusan, angkatan FROM " + TABLE_NAME;

        return MyDB.rawQuery(query,null);
    }

    public Integer cekNim(String nim ){
        String query = "SELECT _nim FROM " + TABLE_NAME + " WHERE _nim = '"+nim+"'";
        Cursor cursor = MyDB.rawQuery(query,null);
        return cursor.getCount();
    }

    public Integer cekLogin(String username, String password){
        String countQuery = "SELECT * FROM " + TABLE_NAME2 + " WHERE username = '"+username+"' AND password = '" + password + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        return rowCount;
    }

    public void insertLogin(String username, String password){

        String query = "INSERT INTO " + TABLE_NAME2 + "VALUES ('"+username+"', '"+password+"')";

        MyDB.execSQL(query);
    }

    public HashMap<String, String> getBioDetail(String nim){
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + NIM + " = " + nim;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put("nim", cursor.getString(0));
            user.put("nama", cursor.getString(1));
            user.put("tgl", cursor.getString(2));
            user.put("jk", cursor.getString(3));
            user.put("alamat", cursor.getString(4));
            user.put("jurusan", cursor.getString(5));
            user.put("angkatan", cursor.getString(6));
        }
        cursor.close();
        db.close();

        return user;
    }
}
