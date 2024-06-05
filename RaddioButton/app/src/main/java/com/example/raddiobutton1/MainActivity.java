//a111221035
package com.example.raddiobutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.EditText;

import com.example.raddiobutton1.EmptyActivity;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    TextView output;
    RadioGroup rgGender;
    RadioGroup rgType;
    EditText editTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        output = findViewById(R.id.lblOutput);
        rgGender = findViewById(R.id.rgGender);
        rgType = findViewById(R.id.rgType);
        editTextNumber = findViewById(R.id.editTextNumber);
        Button button = findViewById(R.id.button);



        rgGender.setOnCheckedChangeListener(this);
        rgType.setOnCheckedChangeListener(this);

        Intent intent = getIntent();
        String gender = intent.getStringExtra("gender");
        String ticketType = intent.getStringExtra("ticketType");
        int numberOfTickets = intent.getIntExtra("numberOfTickets", 0);
//        TextView textView = findViewById(R.id.textView);
        String displayText = gender + " " + ticketType + " " + numberOfTickets + " 張";
//        textView.setText(displayText);



        editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {

                calculatePriceAndDisplay();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 獲取購票資訊
                String gender = "";
                int genderCheckedId = rgGender.getCheckedRadioButtonId();
                if (genderCheckedId == R.id.rdbBoy) {
                    gender = "男";
                } else if (genderCheckedId == R.id.rdbGirl) {
                    gender = "女";
                }

                String ticketType = "";
                int typeCheckedId = rgType.getCheckedRadioButtonId();
                if (typeCheckedId == R.id.rdbAdult) {
                    ticketType = "全票";
                } else if (typeCheckedId == R.id.rdbChild) {
                    ticketType = "兒童票";
                } else if (typeCheckedId == R.id.rdbStudent) {
                    ticketType = "學生票";
                }

                int numberOfTickets = 0;
                try {
                    numberOfTickets = Integer.parseInt(editTextNumber.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                // 創建 Intent 並將購票資訊帶到下一頁
                Intent intent = new Intent(MainActivity.this, EmptyActivity.class);
                intent.putExtra("gender", gender);
                intent.putExtra("ticketType", ticketType);
                intent.putExtra("numberOfTickets", numberOfTickets);
                startActivity(intent);
            }
        });



    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        calculatePriceAndDisplay();
    }

    private void calculatePriceAndDisplay() {

        int numberOfTickets = 0;
        try {
            numberOfTickets = Integer.parseInt(editTextNumber.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        String gender = "";
        int genderCheckedId = rgGender.getCheckedRadioButtonId();
        if (genderCheckedId == R.id.rdbBoy) {
            gender = "男";
        } else if (genderCheckedId == R.id.rdbGirl) {
            gender = "女";
        }


        String ticketType = "";
        int typeCheckedId = rgType.getCheckedRadioButtonId();
        int price = 0;
        if (typeCheckedId == R.id.rdbAdult) {
            ticketType = "全票(500)";
            price = 500;
        } else if (typeCheckedId == R.id.rdbChild) {
            ticketType = "兒童票(250)";
            price = 250;
        } else if (typeCheckedId == R.id.rdbStudent) {
            ticketType = "學生票(400)";
            price = 400;
        }


        int totalPrice = price * numberOfTickets;


        String displayText = gender + " "  + ticketType + " " + numberOfTickets + " 張, 總價格: " + totalPrice;
        output.setText(displayText);
    }


}


