package nelitaaas.com.utspraktikummobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by nelitaaas on 24/10/16.
 */
public class DetailItem extends AppCompatActivity{

    private ImageView imgItem;
    private TextView tvNama, tvDesc, tvPrice;
    String nama, desc;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);

        nama = getIntent().getStringExtra("nama");
        desc = getIntent().getStringExtra("desc");
        Double price = Double.parseDouble(getIntent().getStringExtra("price"));
        int pos = getIntent().getIntExtra("pos", 0);

        imgItem = (ImageView)findViewById(R.id.imgProduk);
        tvNama = (TextView) findViewById(R.id.namaItem);
        tvDesc = (TextView) findViewById(R.id.descItem);
        tvPrice = (TextView) findViewById(R.id.price);

        java.text.NumberFormat format = java.text.NumberFormat.getCurrencyInstance(Locale.GERMANY);

        imgItem.setImageResource(resources[pos]);
        tvNama.setText(nama);
        tvDesc.setText(desc);
        tvPrice.setText("Rp. " + String.format("%,.2f", price));
    }
}
