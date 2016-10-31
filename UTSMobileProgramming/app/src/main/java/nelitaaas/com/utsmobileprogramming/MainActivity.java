package nelitaaas.com.utsmobileprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import nelitaaas.com.utsmobileprogramming.utils.ProductListAdapter;
import nelitaaas.com.utsmobileprogramming.utils.SessionManagement;

public class MainActivity extends AppCompatActivity {

    SessionManagement session;
    ListView productListView;
    List<Product> products;
    ProductListAdapter productListAdapter;

    public static int[] image = {
            R.drawable.tiramisu_cake,
            R.drawable.red_cake,
            R.drawable.green_cake,
            R.drawable.blackforest_cake,
            R.drawable.taro_cake
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            //getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        session = new SessionManagement(getApplicationContext());
        session.checkLogin();

        setProduct();

        productListView = (ListView) findViewById(R.id.list_product);
        productListAdapter = new ProductListAdapter(getApplicationContext(), products, image);
        productListView.setAdapter(productListAdapter);

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Product product = (Product)adapterView.getItemAtPosition(position);
                String price = String.valueOf(product.getPrice());

                Intent iDetail = new Intent(getApplicationContext(), Pemesanan.class);
                iDetail.putExtra("position", position);
                iDetail.putExtra("nama",product.getName());
                startActivity(iDetail);
            }
        });
    }

    private void setProduct(){

        Product product1 = new Product(1,"Cake Tiramisu","Kue dengan aroma Tiramisu",65000);
        Product product2 = new Product(2,"Cake Red Velvet","Kue dengan rasa Red Velvet",75000);
        Product product3 = new Product(3,"Cake Green Tea","Kue dengan aroma Green Tea",85000);
        Product product4 = new Product(4,"Cake Blackforest","Kue dengan rasa coklat",50000);
        Product product5 = new Product(5,"Cake Taro","Kue dengan aroma Taro",55000);

        products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                session.logoutUser();
                break;
        }
        return true;
    }
}
