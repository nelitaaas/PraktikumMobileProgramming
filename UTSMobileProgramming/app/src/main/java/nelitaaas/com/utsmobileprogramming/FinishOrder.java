package nelitaaas.com.utsmobileprogramming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by amarullah87 on 30/10/16.
 */
public class FinishOrder extends AppCompatActivity{

    String ucapan, dari, pilihan;
    TextView tampilUntuk, tampilUcapan , tampilDari;
    ImageView tampilImage;
    Button btFinish;

    public static int[] image = {
            R.drawable.tiramisu_cake,
            R.drawable.red_cake,
            R.drawable.green_cake,
            R.drawable.blackforest_cake,
            R.drawable.taro_cake
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_order);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        ucapan = getIntent().getStringExtra("ucapan");
        dari = getIntent().getStringExtra("dari");
        pilihan = getIntent().getStringExtra("pilihan");
        int position = getIntent().getIntExtra("position",0);

        tampilUntuk = (TextView) findViewById(R.id.untuk);
        tampilUntuk.setText(pilihan);

        tampilUcapan = (TextView) findViewById(R.id.ucapan);
        tampilUcapan.setText(ucapan);

        tampilDari = (TextView) findViewById(R.id.dari);
        tampilDari.setText("From: " + dari);

        tampilImage = (ImageView) findViewById(R.id.imgKue);
        tampilImage.setImageResource(image[position]);

        btFinish = (Button) findViewById(R.id.selesai);
        btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FinishOrder.this, "Terimakasih atas Pesanan anda.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
