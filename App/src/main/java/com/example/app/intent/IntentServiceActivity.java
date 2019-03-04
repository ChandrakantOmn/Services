package com.example.app.intent;

/**
 * Created by Chandra Kant on 4/3/19.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.app.R;


public class IntentServiceActivity extends AppCompatActivity {

    private CashbackReciver cashbackReciver;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intentservice);

        tv = (TextView) findViewById(R.id.cb_results);
        registerCashbackReceiver();
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(cashbackReciver);
    }
    public void startCashbackService(View view){
        EditText et = (EditText) findViewById(R.id.cashback_cat);
        Intent cbIntent =  new Intent();
        cbIntent.setClass(this, CashbackIntentService.class);
        cbIntent.putExtra("cashback_cat", et.getText().toString());
        startService(cbIntent);
    }
    private void registerCashbackReceiver(){
        cashbackReciver = new CashbackReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CashbackIntentService.CASHBACK_INFO);
        registerReceiver(cashbackReciver, intentFilter);
    }
    private class CashbackReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String cbinfo = intent.getStringExtra("cashback");
            tv.setText(cbinfo);
        }
    }

}