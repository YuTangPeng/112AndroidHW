package com.example.hwmid;

import static android.content.Intent.getIntent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hwmid.R;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nextactivity);

        // 獲取來自MainActivity的數據
        String gender = getIntent().getStringExtra("gender");
        String ticketPrice = getIntent().getStringExtra("ticketPrice");
        int numberOfTickets = getIntent().getIntExtra("numberOfTickets", 0);
        int totalPrice = getIntent().getIntExtra("totalPrice", 0);

        // 顯示數據
        TextView textViewGender = findViewById(R.id.textViewGender);
        textViewGender.setText("性別: " + gender);

        TextView textViewTicketPrice = findViewById(R.id.textViewTicketPrice);
        textViewTicketPrice.setText("票價: " + ticketPrice);

        TextView textViewNumberOfTickets = findViewById(R.id.textViewNumberOfTickets);
        textViewNumberOfTickets.setText("張數: " + numberOfTickets);

        TextView textViewTotalPrice = findViewById(R.id.textViewTotalPrice);
        textViewTotalPrice.setText("總價格: " + totalPrice);
    }
}
