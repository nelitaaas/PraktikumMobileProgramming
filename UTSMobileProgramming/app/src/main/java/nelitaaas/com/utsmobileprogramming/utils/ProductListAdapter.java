package nelitaaas.com.utsmobileprogramming.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import nelitaaas.com.utsmobileprogramming.Product;
import nelitaaas.com.utsmobileprogramming.R;

/**
 * Created by nelitaaas on 21/10/16.
 */

public class ProductListAdapter extends ArrayAdapter<Product> {

    private Context context;
    List<Product> products;
    int[] imageId;

    public ProductListAdapter(Context context, List<Product> products, int[] resources) {
        super(context, R.layout.product_list_item, products);
        this.context = context;
        this.products = products;
        this.imageId = resources;
    }

    private class ViewHolder {
        TextView productNameTxt;
        TextView productDescTxt;
        TextView productPriceTxt;
        ImageView favoriteImg, imgItem;
        RelativeLayout parentLayout;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
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
            holder.favoriteImg = (ImageView) convertView
                    .findViewById(R.id.imgbtn_favorite);
            holder.imgItem = (ImageView) convertView
                    .findViewById(R.id.imgItem);
            holder.parentLayout = (RelativeLayout) convertView
                    .findViewById(R.id.pdt_layout_item);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Product product = getItem(position);
        assert product != null;
        holder.productNameTxt.setText(product.getName());
        holder.productDescTxt.setText(product.getDescription());

        holder.productPriceTxt.setText("Rp. " + String.format("%,.2f", product.getPrice()));

        holder.imgItem.setImageResource(imageId[position]);
        return convertView;
    }
}
