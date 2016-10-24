package nelitaaas.com.utspraktikummobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import nelitaaas.com.utspraktikummobile.R;
import nelitaaas.com.utspraktikummobile.utils.SharedPreference;

/**
 * Created by nelitaaas on 21/10/16.
 */

public class ProductHistoryAdapter extends ArrayAdapter<Product> {

    private Context context;
    List<Product> products;
    SharedPreference sharedPreference;
    int[] imageId;

    public ProductHistoryAdapter(Context context, List<Product> products, int[] resources) {
        super(context, R.layout.product_history_item, products);
        this.context = context;
        this.products = products;
        this.imageId = resources;
        sharedPreference = new SharedPreference();
    }

    private class ViewHolder {
        TextView productNameTxt;
        TextView productDescTxt;
        TextView productPriceTxt;
        ImageView imgItem;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_list_item, null);
            holder = new ViewHolder();
            holder.productNameTxt = (TextView) convertView
                    .findViewById(R.id.txt_pdt_name);
            holder.productDescTxt = (TextView) convertView
                    .findViewById(R.id.txt_pdt_desc);
            holder.productPriceTxt = (TextView) convertView
                    .findViewById(R.id.txt_pdt_price);
            holder.imgItem = (ImageView) convertView
                    .findViewById(R.id.imgItem);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Product product = (Product) getItem(position);
        holder.productNameTxt.setText(product.getName());
        holder.productDescTxt.setText("");

        holder.productPriceTxt.setText("Rp. " + String.format("%,.2f", product.getPrice()));

        holder.imgItem.setImageResource(imageId[position]);

        return convertView;
    }

    /*Checks whether a particular product exists in SharedPreferences*/
    public boolean checkFavoriteItem(Product checkProduct) {
        boolean check = false;
        List<Product> favorites = sharedPreference.getFavorites(context);
        if (favorites != null) {
            for (Product product : favorites) {
                if (product.equals(checkProduct)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    @Override
    public void add(Product product) {
        super.add(product);
        products.add(product);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Product product) {
        super.remove(product);
        products.remove(product);
        notifyDataSetChanged();
    }
}
