package nelitaaas.com.utspraktikummobile.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nelitaaas.com.utspraktikummobile.adapter.Product;

/**
 * Created by nelitaaas on 21/10/16.
 */

public class SharedPreference {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";
    public static final String HISTORY = "History";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<Product> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addTransaksi(Context context, List<Product> favorite) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonHistory = gson.toJson(favorite);
        editor.putString(HISTORY, jsonHistory);
        editor.commit();
    }

    public void addFavorite(Context context, Product product) {
        List<Product> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Product>();
        favorites.add(product);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, Product product) {
        ArrayList<Product> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(product);
            saveFavorites(context, favorites);
        }
    }

    public void clearHistory(Context context){
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(HISTORY);
        editor.commit();
    }

    public ArrayList<Product> getHistory(Context context){
        SharedPreferences settings;
        List<Product> products;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if(settings.contains(HISTORY)){
            String jsonHistory = settings.getString(HISTORY, null);
            Gson gson = new Gson();
            Product[] historyItems = gson.fromJson(jsonHistory, Product[].class);

            products = Arrays.asList(historyItems);
            products = new ArrayList<>(products);
        }else
            return null;

        return (ArrayList<Product>) products;
    }

    public ArrayList<Product> getFavorites(Context context) {
        SharedPreferences settings;
        List<Product> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Product[] favoriteItems = gson.fromJson(jsonFavorites,
                    Product[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Product>(favorites);
        } else
            return null;

        return (ArrayList<Product>) favorites;
    }
}