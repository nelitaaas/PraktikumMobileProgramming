package nelitaaas.com.utspraktikummobile;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by nelitaaas on 24/10/16.
 */

public class Booking extends AppCompatActivity {

    private Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9;
    int btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9 = 0;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);
        context = this;

        bt1 = (Button) findViewById(R.id.bt1); assert bt1 != null;
        bt2 = (Button) findViewById(R.id.bt2); assert bt2 != null;
        bt3 = (Button) findViewById(R.id.bt3); assert bt3 != null;
        bt4 = (Button) findViewById(R.id.bt4); assert bt4 != null;
        bt5 = (Button) findViewById(R.id.bt5); assert bt5 != null;
        bt6 = (Button) findViewById(R.id.bt6); assert bt6 != null;
        bt7 = (Button) findViewById(R.id.bt7); assert bt7 != null;
        bt8 = (Button) findViewById(R.id.bt8); assert bt8 != null;
        bt9 = (Button) findViewById(R.id.bt9); assert bt9 != null;

        bt1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if(btn1 == 0){
                    bt1.setBackground(ContextCompat.getDrawable(context, R.drawable.booked));
                    btn1 = 1;
                }else{
                    bt1.setBackground(ContextCompat.getDrawable(context, R.drawable.kosong));
                    btn1 = 0;
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if(btn2 == 0){
                    bt2.setBackground(ContextCompat.getDrawable(context, R.drawable.booked));
                    btn2 = 1;
                }else{
                    bt2.setBackground(ContextCompat.getDrawable(context, R.drawable.kosong));
                    btn2 = 0;
                }
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if(btn3 == 0){
                    bt3.setBackground(ContextCompat.getDrawable(context, R.drawable.booked));
                    btn3 = 1;
                }else{
                    bt3.setBackground(ContextCompat.getDrawable(context, R.drawable.kosong));
                    btn3 = 0;
                }
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if(btn4 == 0){
                    bt4.setBackground(ContextCompat.getDrawable(context, R.drawable.booked));
                    btn4 = 1;
                }else{
                    bt4.setBackground(ContextCompat.getDrawable(context, R.drawable.kosong));
                    btn4 = 0;
                }
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if(btn5 == 0){
                    bt5.setBackground(ContextCompat.getDrawable(context, R.drawable.booked));
                    btn5 = 1;
                }else{
                    bt5.setBackground(ContextCompat.getDrawable(context, R.drawable.kosong));
                    btn5 = 0;
                }
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if(btn6 == 0){
                    bt6.setBackground(ContextCompat.getDrawable(context, R.drawable.booked));
                    btn6 = 1;
                }else{
                    bt6.setBackground(ContextCompat.getDrawable(context, R.drawable.kosong));
                    btn6 = 0;
                }
            }
        });

        bt7.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if(btn7 == 0){
                    bt7.setBackground(ContextCompat.getDrawable(context, R.drawable.booked));
                    btn7 = 1;
                }else{
                    bt7.setBackground(ContextCompat.getDrawable(context, R.drawable.kosong));
                    btn7 = 0;
                }
            }
        });

        bt8.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if(btn8 == 0){
                    bt8.setBackground(ContextCompat.getDrawable(context, R.drawable.booked));
                    btn8 = 1;
                }else{
                    bt8.setBackground(ContextCompat.getDrawable(context, R.drawable.kosong));
                    btn8 = 0;
                }
            }
        });

        bt9.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if(btn9 == 0){
                    bt9.setBackground(ContextCompat.getDrawable(context, R.drawable.booked));
                    btn9 = 1;
                }else{
                    bt9.setBackground(ContextCompat.getDrawable(context, R.drawable.kosong));
                    btn9 = 0;
                }
            }
        });
    }
}
