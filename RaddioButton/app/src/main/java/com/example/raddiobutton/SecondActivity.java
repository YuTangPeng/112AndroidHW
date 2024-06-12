package com.example.raddiobutton;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main2);

        // 獲取上一頁傳遞過來的資訊
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String output = extras.getString("output");

            // 顯示資訊
            TextView textView = findViewById(R.id.txv);
            textView.setText(output);
        }
    }
}
