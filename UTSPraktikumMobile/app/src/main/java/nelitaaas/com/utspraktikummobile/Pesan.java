package nelitaaas.com.utspraktikummobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nelitaaas.com.utspraktikummobile.adapter.Product;
import nelitaaas.com.utspraktikummobile.adapter.ProductListAdapter;
import nelitaaas.com.utspraktikummobile.utils.SharedPreference;

/**
 * Created by nelitaaas on 23/10/16.
 */

public class Pesan extends AppCompatActivity implements AdapterView.OnItemLongClickListener,
        AdapterView.OnItemClickListener{

    public static final String ARG_ITEM_ID = "product_list";

    ListView productListView;
    List<Product> products, favorite;
    ProductListAdapter productListAdapter;

    SharedPreference sharedPreference;
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
        setContentView(R.layout.fragment_product_list);

        setProducts();
        sharedPreference = new SharedPreference();
        productListAdapter = new ProductListAdapter(getApplicationContext(), products, resources);

        productListView = (ListView) findViewById(R.id.list_product);
        productListView.setAdapter(productListAdapter);
        productListView.setOnItemClickListener(this);
        productListView.setOnItemLongClickListener(this);

        Button btSimpan = (Button) findViewById(R.id.simpan);
        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iPemesanan = new Intent(getApplicationContext(), Pemesanan.class);
                startActivity(iPemesanan);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = (Product) parent.getItemAtPosition(position);
        String nama = product.getName();
        String desc = product.getDescription();
        String price = String.valueOf(product.getPrice());

        Intent details = new Intent(getApplicationContext(), DetailItem.class);
        details.putExtra("nama", nama);
        details.putExtra("desc", desc);
        details.putExtra("price", price);
        details.putExtra("pos", position);
        startActivity(details);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long arg3) {
        ImageView button = (ImageView) view.findViewById(R.id.imgbtn_favorite);

        String tag = button.getTag().toString();
        if (tag.equalsIgnoreCase("grey")) {
            sharedPreference.addFavorite(getApplicationContext(), products.get(position));
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.add_favr),
                    Toast.LENGTH_SHORT).show();

            button.setTag("green");
            button.setImageResource(R.drawable.ic_check);
        } else {
            sharedPreference.removeFavorite(getApplicationContext(), products.get(position));
            button.setTag("grey");
            button.setImageResource(R.drawable.ic_check_grey);
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.remove_favr),
                    Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    private void setProducts() {

        Product product1 = new Product(1, "Ayam Bumbu Rujak", "Ayam Bakar Bumbu Rujak merupakan salah satu kuliner makanan yang " +
                "sudah sangat banyak  diminati oleh para pecinta kuliner khususnya Di Indonesia. Ayam yang dilumuri bumbu rujak selalu menjadi keunikan tersendiri untuk menu kuliner satu ini.", 22500);
        Product product2 = new Product(2, "Cumi Hideung",
                "Cumi hideung adalah masakan khas tradisional sunda yang bahan utamanya terbuat dari cumi-cumi. Hideung sendiri dalam bahasa Indonesia artinya hitam, cumi hideung adalah ikan cumi yang dimasak sekaligus dengan tinta hitamnya sehingga berwarna hitam.", 16000);
        Product product3 = new Product(3, "Sop Buah",
                "Sop buah merupakan sebuah minuman yang terbuat dari bergaia macam buah dan semunanya di kombinasikan menjadi satu, sehingga rasa yang segar, nikmat dan juga rasanya yang menggoda tersebut akan menggoda setiap orang yang melihat dan merasakannya.\n" +
                        "Didalam sop buah terdapat berbagai buah contohnya seperti buah apel, semangka, mangga, anggur, buah naga, piir, alpukat dan beberapa jenis buah lainnya. Buah tersebut di potong dengan ukuran yang cukup kecil sehingga membuat buah buah tersebut bercampur dan menghasilkan rasa yang unik.", 12000);
        Product product4 = new Product(4, "Banana Split",
                "Banana split adalah makanan penutup berbasis es krim. Dalam bentuk klasik disajikan dalam sebuah piring panjang yang disebut perahu. Sebuah pisang dipotong setengah memanjang (maka perpecahan) dan diletakkan di piring. Ada banyak variasi, tapi banana split klasik dibuat dengan sendok vanili, cokelat dan es krim stroberi disajikan berturut-turut antara banana split.", 23000);
        Product product5 = new Product(5, "Ikan Kerapu Asam Manis",
                "Aroma serta citarasa ikan kerapu setelah dimasak memang menggoda selera. Apalagi bila dimasak dengan bumbu asam manis ala chinese food. Jangan khawatir, semua resep chinese food di sini Halal karena menggunakan bahan dan bumbu yang halal untuk dikonsumsi. Tak beda dengan aneka resep chinese food lain", 25000);
        Product product6 = new Product(6, "Jus Buah Segar", "Juice atau jus adalah sayur-sayuran atau buah-buahan yang dilumatkan dengan tangan maupun mesin menjadi cairan yang berisi saripati untuk diminum dan umumnya memiliki manfaat yang baik bagi kesehatan . Jus segar dapat menyerap protein, karbohidrat, dan asam lemak esensial, vitamin dan mineral dengan mudah.",
                10000);
        Product product7 = new Product(7, "Kiwi Squash",
                "Perayaan seperti pesta atau acara-acara kecil di rumah rasanya tak lengkap jika sudah ada kue dan hidangan lainnya tanpa dilengkapi dengan minuman segar. Minuman yang Anda sajikan pastikan minuman segar yang terlihat cantik, menarik dan menggoda untuk segera dinikmati. Seperti minuman segar ice kiwi squash yang satu ini.", 11000);
        Product product8 = new Product(8, "Nasi Kuning Komplit",
                "Nasi kuning adalah makanan khas Indonesia. Makanan ini terbuat dari beras yang dimasak bersama dengan kunyit serta santan dan rempah-rempah. Dengan ditambahkannya bumbu-bumbu dan santan, nasi kuning memiliki rasa yang lebih gurih daripada nasi putih.", 9500);
        Product product9 = new Product(9, "Pisang Ijo",
                "Pisang ijo atau Es pisang ijo, adalah sejenis makanan khas di Sulawesi Selatan, utamanya di kota Makassar yang terbuat dari bahan utama berupa pisang ijo. Pisang ijo berupa pisang yang dibalut dengan adonan tepung yang berwarna hijau dan cara memasaknya dengan mengkukus di sebuah dandang.", 9000);
        Product product10 = new Product(10, "Rendang Special",
                "Rendang atau randang adalah masakan daging bercita rasa pedas yang menggunakan campuran dari berbagai bumbu dan rempah-rempah. Masakan ini dihasilkan dari proses memasak yang dipanaskan berulang-ulang dengan santan kelapa.", 12000);

        products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);
    }
}
