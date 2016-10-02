package com.hello.nelitaaas.tugas1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView TampilNama,TampilNim,TampilEmail,TampilAngkatan,lblNama,lblEmail,lblNim,lblAngkatan;
    private EditText EtNama, EtNim,EtEmail,EtAngkatan;
    private Button BtnTampil , BtnReset, BtnKembali;
    private String StrNama,StrEmail,StrNim,StrAngkatan;
    private ImageView imgProfile;

    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TampilNama = (TextView)findViewById(R.id.TampilNama);
        TampilEmail = (TextView)findViewById(R.id.TampilEmail);
        TampilNim = (TextView)findViewById(R.id.TampilNim);
        TampilAngkatan = (TextView)findViewById(R.id.TampilAngkatan);

        lblNama=(TextView)findViewById(R.id.lblNama);
        lblEmail=(TextView)findViewById(R.id.lblEmail);
        lblNim=(TextView)findViewById(R.id.lblNim);
        lblAngkatan=(TextView)findViewById(R.id.lblAngkatan);

        EtNama = (EditText)findViewById(R.id.nama);
        EtEmail = (EditText)findViewById(R.id.email);
        EtNim = (EditText)findViewById(R.id.nim);
        EtAngkatan = (EditText)findViewById(R.id.angkatan);

        BtnTampil = (Button)findViewById(R.id.BtnTampil);
        BtnReset = (Button)findViewById(R.id.BtnReset);
        BtnKembali = (Button)findViewById(R.id.BtnBack);

        imgProfile = (ImageView) findViewById(R.id.imgProfile);
        assert imgProfile != null;
        imgProfile.setOnClickListener(this);

        assert BtnTampil!=null;
        BtnTampil.setOnClickListener(this);
        BtnReset.setOnClickListener(this);
        BtnKembali.setOnClickListener(this);

        setAutoFocus();

    }

    private void setAutoFocus() {
        EtNama.requestFocus();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.BtnTampil:
                StrNama=EtNama.getText().toString();
                StrEmail=EtEmail.getText().toString();
                StrNim=EtNim.getText().toString();
                StrAngkatan=EtAngkatan.getText().toString();

                OutputBiodata();

                TampilNama.setText("Nama : \n"+StrNama);
                TampilEmail.setText("Email :\n"+StrEmail);
                TampilNim.setText("Nim : \n"+StrNim);
                TampilAngkatan.setText("Angkatan :\n"+StrAngkatan);

            break;

            case R.id.BtnReset:
                ResetBiodata();
                break;

            case R.id.BtnBack:
                InputBiodata();
                ResetBiodata();
                break;

            case R.id.imgProfile:
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;

            default:
                break;
        }
    }

    private void ResetBiodata() {
        EtNama.setText("");
        EtEmail.setText("");
        EtNim.setText("");
        EtAngkatan.setText("");

        imgProfile.setImageResource(R.drawable.add_photo);

        setAutoFocus();
    }

    private void InputBiodata() {
        BtnKembali.setVisibility(View.GONE);
        BtnTampil.setVisibility(View.VISIBLE);
        BtnReset.setVisibility(View.VISIBLE);

        EtNama.setVisibility(View.VISIBLE);
        EtEmail.setVisibility(View.VISIBLE);
        EtNim.setVisibility(View.VISIBLE);
        EtAngkatan.setVisibility(View.VISIBLE);

        lblNama.setVisibility(View.VISIBLE);
        lblEmail.setVisibility(View.VISIBLE);
        lblNim.setVisibility(View.VISIBLE);
        lblAngkatan.setVisibility(View.VISIBLE);

        TampilNama.setVisibility(View.GONE);
        TampilEmail.setVisibility(View.GONE);
        TampilNim.setVisibility(View.GONE);
        TampilAngkatan.setVisibility(View.GONE);
    }

    private void OutputBiodata() {

        BtnTampil.setVisibility(View.GONE);
        BtnReset.setVisibility(View.GONE);
        BtnKembali.setVisibility(View.VISIBLE);

        EtNama.setVisibility(View.GONE);
        EtEmail.setVisibility(View.GONE);
        EtNim.setVisibility(View.GONE);
        EtAngkatan.setVisibility(View.GONE);

        lblNama.setVisibility(View.GONE);
        lblEmail.setVisibility(View.GONE);
        lblNim.setVisibility(View.GONE);
        lblAngkatan.setVisibility(View.GONE);

        TampilNama.setVisibility(View.VISIBLE);
        TampilEmail.setVisibility(View.VISIBLE);
        TampilNim.setVisibility(View.VISIBLE);
        TampilAngkatan.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);

            imgProfile.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
