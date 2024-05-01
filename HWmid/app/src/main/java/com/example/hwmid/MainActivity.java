package com.example.hwmid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hwmid.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroupGender;
    Spinner spinnerTicketPrice;
    EditText editTextNumberOfTickets;
    Button buttonConfirm;

    // 定義票價對應的價格映射
    Map<String, Integer> ticketPriceMap;

    private TextView textViewSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化界面元素
        radioGroupGender = findViewById(R.id.radioGroupGender);
        textViewSelection = findViewById(R.id.textViewSelection);
        editTextNumberOfTickets = findViewById(R.id.editTextNumberOfTickets);
        buttonConfirm = findViewById(R.id.buttonConfirm);

        // 初始化票價對應的價格映射
        ticketPriceMap = new HashMap<>();
        ticketPriceMap.put("全票(500)", 500);
        ticketPriceMap.put("兒童票(250)", 250);
        ticketPriceMap.put("學生票(400)", 400);

        // 設置票價選擇下拉菜單
        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ticket_prices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTicketPrice.setAdapter(adapter);*/



        // 確認按鈕點擊事件
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 獲取選擇的性別
                int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
                String selectedGender = "";
                if (selectedGenderId != -1) {
                    if (selectedGenderId == R.id.radioButtonMale) {
                        selectedGender = "男";
                    } else if (selectedGenderId == R.id.radioButtonFemale) {
                        selectedGender = "女";
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "請選擇性別", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 獲取選擇的票價
                String selectedTicketPrice = spinnerTicketPrice.getSelectedItem().toString();

                // 獲取輸入的張數
                String numberOfTicketsStr = editTextNumberOfTickets.getText().toString();
                if (numberOfTicketsStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "請輸入張數", Toast.LENGTH_SHORT).show();
                    return;
                }
                int numberOfTickets = Integer.parseInt(numberOfTicketsStr);

                // 計算總價格
                int totalPrice = calculateTotalPrice(selectedTicketPrice, numberOfTickets);

                // 跳轉到結果頁面並傳遞相關數據
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("gender", selectedGender);
                intent.putExtra("ticketPrice", selectedTicketPrice);
                intent.putExtra("numberOfTickets", numberOfTickets);
                intent.putExtra("totalPrice", totalPrice);
                startActivity(intent);
            }
        });
    }

    // 計算總價格
    private int calculateTotalPrice(String ticketPrice, int numberOfTickets) {
        return ticketPriceMap.get(ticketPrice) * numberOfTickets;
    }
}
