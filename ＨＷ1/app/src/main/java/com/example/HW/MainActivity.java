package com.example.HW;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button_Click(View view){
        EditText edtName = (EditText) findViewById(R.id.edtName);
        TextView txvShow = txvShow = (TextView) findViewById(R.id.txvShow);
        EditText ett = (EditText) findViewById(R.id.ett); // 初始化 ett
        String name = edtName.getText().toString();
        String password = ett.getText().toString(); // 正確獲取 ett 的值
        if(name.equals("A111221041") && password.equals("1041"))
            txvShow.setText("登入成功");
        else if (name.equals("A111221041") && !password.equals("1041"))
            txvShow.setText("密碼錯誤");
        else if (!name.equals("A111221041") && password.equals("1041"))
            txvShow.setText("帳號錯誤");
        else if(!name.equals("A111221041") && !password.equals("1041"))
            txvShow.setText("帳號及密碼錯誤");
    }
}