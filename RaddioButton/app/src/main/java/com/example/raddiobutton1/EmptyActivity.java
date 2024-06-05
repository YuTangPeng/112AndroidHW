package com.example.raddiobutton1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EmptyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity);

        // 获取购票信息
        Intent intent = getIntent();
        String gender = intent.getStringExtra("gender");
        String ticketType = intent.getStringExtra("ticketType");
        int numberOfTickets = intent.getIntExtra("numberOfTickets", 0);

        // 在 TextView 中显示购票信息
        int price = 0;
        if ("全票".equals(ticketType)) {
            price = 500;
        } else if ("兒童票".equals(ticketType)) {
            price = 250;
        } else if ("學生票".equals(ticketType)) {
            price = 400;
        }
        int totalPrice = price * numberOfTickets;

        TextView textView = findViewById(R.id.textView4);
        String displayText = "姓別:"+gender + " " + "\n票別"+ticketType + " " + numberOfTickets + " 張\n總金額: " + totalPrice + "元";
        textView.setText(displayText);
    }

}
