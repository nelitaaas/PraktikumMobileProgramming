package nelitaaas.com.tugas3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    SessionManagement session;
    SubmitPreferences submitPreferences;

    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session class instance
        session = new SessionManagement(getApplicationContext());
        submitPreferences = new SubmitPreferences(getApplicationContext());

        TextView lblName = (TextView) findViewById(R.id.lblName);
        TextView lblEmail = (TextView) findViewById(R.id.lblEmail);
        final EditText txtnama = (EditText) findViewById(R.id.nama);
        final EditText txtemail = (EditText) findViewById(R.id.email);
        Button btSimpan = (Button) findViewById(R.id.simpan);

        // Button logout
        btnLogout = (Button) findViewById(R.id.btnLogout);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManagement.KEY_NAME);

        // email
        String email = user.get(SessionManagement.KEY_EMAIL);

        // displaying user data
        lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));
        lblEmail.setText(Html.fromHtml("Email: <b>" + email + "</b>"));


        /**
         * Logout button click event
         * */
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                session.logoutUser();
            }
        });

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = txtnama.getText().toString();
                String email = txtemail.getText().toString();

                submitPreferences.createSubmitSession(nama, email);
                Intent iSubmit = new Intent(getApplicationContext(), TampilSubmit.class);
                startActivity(iSubmit);
            }
        });
    }
}
