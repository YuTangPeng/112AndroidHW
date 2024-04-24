package com.example.midtermhw_a111221011;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.RadioGroup;



public class main extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    TextView output; // 声明TextView对象
    String gender; // 存储性别
    String ticketType; // 存储票类型

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 找到TextView对象并注册监听器
        output = findViewById(R.id.lblOutput);
        RadioGroup rgGender = findViewById(R.id.rgGender);
        rgGender.setOnCheckedChangeListener(this);

        RadioGroup rgType = findViewById(R.id.rgType);
        rgType.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        // 判断是选择哪一个单选按钮，并更新对应的性别和票类型
        if (radioGroup.getId() == R.id.rgGender) {
            if (checkedId == R.id.rdbBoy) {
                gender = "男";
            } else if (checkedId == R.id.rdbGirl) {
                gender = "女";
            }
        } else if (radioGroup.getId() == R.id.rgType) {
            if (checkedId == R.id.rdbAdult) {
                ticketType = "成人票";
            } else if (checkedId == R.id.rdbChild) {
                ticketType = "兒童票";
            } else if (checkedId == R.id.rdbStudent) {
                ticketType = "學生票";
            }
        }

        // 更新文本视图的内容
        if (gender != null && ticketType != null) {
            output.setText(gender + " " + ticketType);
        }
    }
}

