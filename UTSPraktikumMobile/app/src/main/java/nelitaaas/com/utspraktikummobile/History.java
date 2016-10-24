package nelitaaas.com.utspraktikummobile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import nelitaaas.com.utspraktikummobile.adapter.Product;
import nelitaaas.com.utspraktikummobile.adapter.ProductHistoryAdapter;
import nelitaaas.com.utspraktikummobile.utils.SharedPreference;

/**
 * Created by nelitaaas on 23/10/16.
 */
public class History extends AppCompatActivity{

    ListView listHistory;
    SharedPreference sharedPreference;
    List<Product> products;

    Context mContext;
    ProductHistoryAdapter productHistoryAdapter;

    public static int[] resources = {
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
        setContentView(R.layout.history);
        mContext = getApplicationContext();

        sharedPreference = new SharedPreference();
        products = sharedPreference.getHistory(History.this);

        if(products == null){
            showAlert("Tidak Ada Data History",
                    "Pilih pesanan anda pada Menu 'Pesan' untuk melakukan Transaksi");
        }else{

            if(products.size() == 0){
                showAlert("Tidak Ada Data History",
                        "Pilih pesanan anda pada Menu 'Pesan' untuk melakukan Transaksi");
            }

            listHistory = (ListView) findViewById(R.id.list_product);
            if(products != null){
                productHistoryAdapter = new ProductHistoryAdapter(History.this, products, resources);
                listHistory.setAdapter(productHistoryAdapter);
            }
        }

        Button btHapus = (Button) findViewById(R.id.btHapus);
        assert btHapus != null;
        btHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreference.clearHistory(History.this);
                finish();
            }
        });

        double total = 0;
        if(products != null) {
            for (int i = 0; i < products.size(); i++) {
                total += products.get(i).getPrice();
            }
        }

        TextView txtTotal = (TextView) findViewById(R.id.total);
        txtTotal.setText("Rp. " + String.format("%,.2f", total));
    }

    public void showAlert(String title, String message) {
        if (!History.this.isFinishing()) {
            AlertDialog alertDialog = new AlertDialog.Builder(History.this)
                    .create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
            alertDialog.setCancelable(false);

            // setting OK Button
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            // activity.finish();
                            getFragmentManager().popBackStackImmediate();
                        }
                    });
            alertDialog.show();
        }
    }
}
