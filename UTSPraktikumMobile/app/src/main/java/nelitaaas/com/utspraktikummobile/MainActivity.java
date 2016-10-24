package nelitaaas.com.utspraktikummobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.HashMap;

import nelitaaas.com.utspraktikummobile.utils.SessionManagement;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionManagement session;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;

    int[] resources = {
            R.drawable.ayam_bumbu_rujak,
            R.drawable.cumi_hideung,
            R.drawable.es_buah,
            R.drawable.eskrim_buah,
            R.drawable.ikan_kerapu_asam_manis,
            R.drawable.jus_segar,
            R.drawable.kiwi_leci_squash,
            R.drawable.nasi_kuning_komplit,
            R.drawable.pisang_ijo,
            R.drawable.rendang
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        session = new SessionManagement(getApplicationContext());
        session.checkLogin();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent iPesan = new Intent(getApplicationContext(), Pesan.class);
                startActivity(iPesan);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mViewFlipper = (ViewFlipper) findViewById(R.id.flipper1);
        // Add all the images to the ViewFlipper
        for (int i = 0; i < resources.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(resources[i]);
            mViewFlipper.addView(imageView);
        }
        if (mViewFlipper != null) {
            mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
            mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);
            mViewFlipper.setAutoStart(true);
            mViewFlipper.setFlipInterval(5000);
        }
        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);

        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(SessionManagement.KEY_NAME);
        String email = user.get(SessionManagement.KEY_EMAIL);

        View hView = navigationView.getHeaderView(0);
        TextView username = (TextView) hView.findViewById(R.id.username); assert username != null;
        TextView emails = (TextView) hView.findViewById(R.id.email); assert emails != null;
        username.setText(name); emails.setText(email);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.pesan:
                Intent iPesan = new Intent(getApplicationContext(), Pesan.class);
                startActivity(iPesan);
                break;

            case R.id.booking:
                Intent iBooking = new Intent(getApplicationContext(), Booking.class);
                startActivity(iBooking);
                break;

            case R.id.history:
                Intent iHistory = new Intent(getApplicationContext(), History.class);
                startActivity(iHistory);
                break;

            case R.id.about:
                Intent iAbout = new Intent(getApplicationContext(), About.class);
                startActivity(iAbout);
                break;

            case R.id.logout:
                session.logoutUser();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                mViewFlipper.showNext();
            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                mViewFlipper.showPrevious();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }
}
