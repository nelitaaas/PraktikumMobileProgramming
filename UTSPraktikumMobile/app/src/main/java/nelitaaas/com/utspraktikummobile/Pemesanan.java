package nelitaaas.com.utspraktikummobile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nelitaaas.com.utspraktikummobile.adapter.Product;
import nelitaaas.com.utspraktikummobile.adapter.ProductListAdapter;
import nelitaaas.com.utspraktikummobile.utils.SharedPreference;

import static nelitaaas.com.utspraktikummobile.utils.SharedPreference.FAVORITES;
import static nelitaaas.com.utspraktikummobile.utils.SharedPreference.PREFS_NAME;

/**
 * Created by nelitaaas on 23/10/16.
 */
public class Pemesanan extends AppCompatActivity{

    public static final String ARG_ITEM_ID = "favorite_list";

    ListView favoriteList;
    SharedPreference sharedPreference;
    List<Product> favorites;

    ProductListAdapter productListAdapter;
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
        setContentView(R.layout.product_order);

        sharedPreference = new SharedPreference();
        favorites = sharedPreference.getFavorites(getApplicationContext());

        if (favorites == null) {
            showAlert(getResources().getString(R.string.no_favorites_items),
                    getResources().getString(R.string.no_favorites_msg));
        } else {

            if (favorites.size() == 0) {
                showAlert(
                        getResources().getString(R.string.no_favorites_items),
                        getResources().getString(R.string.no_favorites_msg));
            }

            favoriteList = (ListView) findViewById(R.id.list_product);
            if (favorites != null) {
                productListAdapter = new ProductListAdapter(getApplicationContext(), favorites, resources);
                favoriteList.setAdapter(productListAdapter);

                favoriteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View arg1,
                                            int position, long arg3) {

                    }
                });

                favoriteList
                        .setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                            @Override
                            public boolean onItemLongClick(
                                    AdapterView<?> parent, View view,
                                    int position, long id) {

                                ImageView button = (ImageView) view
                                        .findViewById(R.id.imgbtn_favorite);

                                String tag = button.getTag().toString();
                                if (tag.equalsIgnoreCase("grey")) {
                                    sharedPreference.addFavorite(getApplicationContext(),
                                            favorites.get(position));
                                    Toast.makeText(
                                            getApplicationContext(),
                                            getResources().getString(
                                                    R.string.add_favr),
                                            Toast.LENGTH_SHORT).show();

                                    button.setTag("green");
                                    button.setImageResource(R.drawable.ic_check);
                                } else {
                                    sharedPreference.removeFavorite(getApplicationContext(),
                                            favorites.get(position));
                                    button.setTag("grey");
                                    button.setImageResource(R.drawable.ic_check_grey);
                                    productListAdapter.remove(favorites
                                            .get(position));
                                    Toast.makeText(
                                            getApplicationContext(),
                                            getResources().getString(
                                                    R.string.remove_favr),
                                            Toast.LENGTH_SHORT).show();
                                }
                                return true;
                            }
                        });
            }
        }

        double total = 0;
        if(favorites != null) {
            for (int i = 0; i < favorites.size(); i++) {
                total += favorites.get(i).getPrice();
            }
        }

        TextView txtTotal = (TextView) findViewById(R.id.total);
        txtTotal.setText("Rp. " + String.format("%,.2f", total));

        Log.e("Value", String.valueOf(total));

        Button btSimpan = (Button) findViewById(R.id.simpan);
        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favorites = sharedPreference.getFavorites(getApplicationContext());
                sharedPreference.addTransaksi(getApplicationContext(), favorites);
                //sharedPreference.clearFavorite(activity);

                SharedPreferences settings;
                SharedPreferences.Editor editor;

                settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                editor = settings.edit();

                editor.remove(FAVORITES);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Data Telah Masuk", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void showAlert(String title, String message) {
        if (!Pemesanan.this.isFinishing()) {
            AlertDialog alertDialog = new AlertDialog.Builder(Pemesanan.this)
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

    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
        super.onBackPressed();
    }
}
