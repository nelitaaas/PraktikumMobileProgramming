package nelitaaas.com.tugas3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by nelitaaas on 17/10/16.
 */
public class TampilSubmit extends AppCompatActivity{

    SubmitPreferences submitPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tampil_submit);

        submitPreferences = new SubmitPreferences(getApplicationContext());

        TextView lblName = (TextView) findViewById(R.id.lblName);
        TextView lblEmail = (TextView) findViewById(R.id.lblEmail);

        HashMap<String, String> submit = submitPreferences.getSubmitDetails();
        String nama = submit.get(SubmitPreferences.KEY_NAMA);
        String email = submit.get(SubmitPreferences.KEY_EMAIL2);
        lblName.setText(nama);
        lblEmail.setText(email);

    }
}
