package com.example.spandlv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 获取来自 MainActivity 的额外数据
        Intent intent = getIntent();
        if (intent != null) {
            String mainText = intent.getStringExtra("main_text");
            String sidedishText = intent.getStringExtra("sidedish_text");
            String drinkText = intent.getStringExtra("drink_text");

            // 设置文本内容到相应的 TextView 中
            TextView mainTextView = findViewById(R.id.txv1);
            mainTextView.setText(mainText);

            TextView sidedishTextView = findViewById(R.id.txv2);
            sidedishTextView.setText(sidedishText);

            TextView drinkTextView = findViewById(R.id.txv3);
            drinkTextView.setText(drinkText);
        }
    }
}
