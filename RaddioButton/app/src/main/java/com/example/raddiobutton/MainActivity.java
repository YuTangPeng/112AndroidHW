package com.example.raddiobutton;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String gender;
    private String outputstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        // 宣告所有需要的元件
        RadioGroup genderGroup = findViewById(R.id.rgGender);
        RadioGroup ticketGroup = findViewById(R.id.rgType);
        EditText numEditText = findViewById(R.id.num);
        TextView outputTextView = findViewById(R.id.lblOutput);

        // 監聽性別選擇
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateOutput(outputTextView, genderGroup, ticketGroup, numEditText);
            }
        });

        // 監聽票種選擇
        ticketGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateOutput(outputTextView, genderGroup, ticketGroup, numEditText);
            }
        });

        // 監聽填寫張數
        numEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    updateOutput(outputTextView, genderGroup, ticketGroup, numEditText);
                }
            }
        });

        // 監聽按鈕
        Button confirmButton = findViewById(R.id.button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 獲取選擇的票種和填寫的張數
                RadioButton selectedTicket = findViewById(ticketGroup.getCheckedRadioButtonId());
                String ticketType = selectedTicket != null ? selectedTicket.getText().toString() : "";

                EditText numEditText = findViewById(R.id.num);
                String numString = numEditText.getText().toString();
                int numTickets = numString.isEmpty() ? 0 : Integer.parseInt(numString);

                // 計算總價
                int ticketPrice = 0;
                int checkedRadioButtonId = ticketGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId == R.id.rdbAdult) {
                    ticketPrice = 500; // 成人票价为500
                } else if (checkedRadioButtonId == R.id.rdbChild) {
                    ticketPrice = 250; // 孩童票价为250
                } else if (checkedRadioButtonId == R.id.rdbStudent) {
                    ticketPrice = 400; // 学生票价为400
                }

                int sum = ticketPrice * numTickets;

                // 顯示結果
                String output = "";
                if (!gender.isEmpty()) {
                    output += getString(R.string.gender) + gender + "\n";
                }
                if (!ticketType.isEmpty()) {
                    output += getString(R.string.ticketTYpe) + ticketType + "\n";
                }
                output += getString(R.string.numberOfTickets) + numTickets  + "\n";
                output += getString(R.string.total) + sum;
                outputTextView.setText(output);

                // 跳轉到下一頁
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("output", output);
                startActivity(intent);
            }
        });
    }

    // 更新 lblOutput
    private void updateOutput(TextView outputTextView, RadioGroup genderGroup, RadioGroup ticketGroup, EditText numEditText) {
        RadioButton selectedGender = findViewById(genderGroup.getCheckedRadioButtonId());
        gender = selectedGender != null ? selectedGender.getText().toString() : "";

        RadioButton selectedTicket = findViewById(ticketGroup.getCheckedRadioButtonId());
        String ticketType = selectedTicket != null ? selectedTicket.getText().toString() : "";

        String numString = numEditText.getText().toString();
        int numTickets = numString.isEmpty() ? 0 : Integer.parseInt(numString);

        // 計算總價
        int ticketPrice = 0;
        int checkedRadioButtonId = ticketGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.rdbAdult) {
            ticketPrice = 500; // 成人票价为500
        } else if (checkedRadioButtonId == R.id.rdbChild) {
            ticketPrice = 250; // 孩童票价为250
        } else if (checkedRadioButtonId == R.id.rdbStudent) {
            ticketPrice = 400; // 学生票价为400
        }

        int sum = ticketPrice * numTickets;

        // 顯示結果
        String output = "";
        if (!gender.isEmpty()) {
            output += getString(R.string.gender) + gender + "\n";
        }
        if (!ticketType.isEmpty()) {
            output += getString(R.string.ticketTYpe) + ticketType + "\n";
        }
        output += getString(R.string.numberOfTickets) + numTickets  + "\n";
        output += getString(R.string.total) + sum;
        outputTextView.setText(output);
    }
}
