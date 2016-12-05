package es.esy.hangatsenja.post;

/**
 * Created by MUL 10 on 11/29/2016.
 */
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
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
import java.util.ArrayList;
import java.util.List;
public class AddBarang extends AppCompatActivity implements LoaderCallbacks<Cursor>
{
    private AddBarangTask aksiSimpan = null;
    // UI references.
    private EditText kodeBarang,namaBarang,hargaBarang;
    private View mProgressView;
    private View mLoginFormView;
    private String  kode_barang,nama_barang,harga_barang;
    private URL url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barang);
// Set up the login form.
        kodeBarang = (EditText) findViewById(R.id.kode_barang_add);
        namaBarang = (EditText) findViewById(R.id.nama_barang_add);
        hargaBarang = (EditText) findViewById (R.id.harga_add);
        Button btnSimpan = (Button) findViewById(R.id.btnsimpan);
        btnSimpan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanData();
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }
    private void simpanData() {
        kode_barang = kodeBarang.getText().toString();
        nama_barang = namaBarang.getText().toString();
        harga_barang = hargaBarang.getText().toString();
        showProgress(true);
        aksiSimpan = new AddBarangTask(kode_barang, nama_barang,harga_barang);
        aksiSimpan.execute((Void) null);
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
// for very easy animations. If available, use these APIs to fade-in
// the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime =
                    getResources().getInteger(android.R.integer.config_shortAnimTime);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
// The ViewPropertyAnimator APIs are not available, so simply show
// and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
// Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY),
                ProfileQuery.PROJECTION,
// Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},
// Show primary email addresses first. Note that there won't be
// a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }
    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
    }
    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
    }
    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };
        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }
    public class AddBarangTask extends AsyncTask<Void, Void, Boolean> {
        String mKodeBarang;
        String mNamaBarang;
        String mHargaBarang;
        AddBarangTask(String kode_b, String nama_b,String harga_b) {
            mKodeBarang = kode_b;
            mNamaBarang = nama_b;
            mHargaBarang = harga_b;
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
                URL url = new URL("http://dthan.net/add_barang.php");
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
            showProgress(false);
            if (success) {
                kembaliKeHome();
            } else {
                Toast.makeText(
                        getApplicationContext(),"gagal",Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected void onCancelled() {
            aksiSimpan = null;
            showProgress(false);
        }
    }
    protected void kembaliKeHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}