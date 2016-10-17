package nelitaaas.com.tugas3;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by nelitaaas on 17/10/16.
 */

public class SubmitPreferences {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_BARU = "BaruPref";

    public static final String KEY_NAMA = "nama";
    public static final String KEY_EMAIL2 = "email";

    public SubmitPreferences(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_BARU, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createSubmitSession(String name, String email){
        // Storing name in pref
        editor.putString(KEY_NAMA, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL2, email);

        // commit changes
        editor.commit();
    }


    public HashMap<String, String> getSubmitDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAMA, pref.getString(KEY_NAMA, null));

        // user email id
        user.put(KEY_EMAIL2, pref.getString(KEY_EMAIL2, null));

        // return user
        return user;
    }

    public void hapusSubmit(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }
}
