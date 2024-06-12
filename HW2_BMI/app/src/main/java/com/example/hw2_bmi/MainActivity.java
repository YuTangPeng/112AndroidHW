package com.example.hw2_bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvShow = findViewById(R.id.txvShow);
        txvShow.setTextSize(36);
        EditText edtHeight = findViewById(R.id.edtHeight);
        EditText edtWeight = findViewById(R.id.edtWeight);
        Button btnCalc = findViewById(R.id.btnCalc);
        Button btnClear = findViewById(R.id.btnClear);

        // 設置身高和體重輸入框只能輸入百位數加小數點
        edtHeight.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edtHeight.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(1)});
        edtWeight.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edtWeight.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(1)});

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heightStr = edtHeight.getText().toString();
                String weightStr = edtWeight.getText().toString();

                if (TextUtils.isEmpty(heightStr) || Double.parseDouble(heightStr) <= 0) {
                    if (TextUtils.isEmpty(weightStr) || Double.parseDouble(weightStr) <= 0) {
                        txvShow.setText("請輸入身高體重");
                    } else {
                        txvShow.setText("請輸入正確身高");
                    }
                    return; // 停止執行，因為輸入不正確
                }

                if (TextUtils.isEmpty(weightStr) || Double.parseDouble(weightStr) <= 0) {
                    txvShow.setText("請輸入正確體重");
                    return; // 停止執行，因為輸入不正確
                }


                double height = Double.parseDouble(heightStr);
                double weight = Double.parseDouble(weightStr);

                

                double bmi = weight / Math.pow(height / 100.0, 2);
                if (bmi >= 24)
                    txvShow.setTextColor(Color.RED);
                else if (bmi < 18.5)
                    txvShow.setTextColor(Color.BLUE);
                else
                    txvShow.setTextColor(Color.GREEN);

                txvShow.setText(String.format("%.1f", bmi));
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtHeight.setText("");
                edtWeight.setText("");
                txvShow.setText("");
            }
        });
    }

    // 自定義輸入過濾器，限制小數位數
    public class DecimalDigitsInputFilter implements InputFilter {
        private final int decimalDigits;

        public DecimalDigitsInputFilter(int decimalDigits) {
            this.decimalDigits = decimalDigits;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   android.text.Spanned dest, int dstart, int dend) {
            StringBuilder builder = new StringBuilder(dest);
            builder.replace(dstart, dend, source.subSequence(start, end).toString());
            if (!builder.toString().matches(
                    "(([1-9][0-9]{0," + (3 - decimalDigits) + "})?|0)?(\\.[0-9]{0," + decimalDigits + "})?"
            )) {
                if (source.length() == 0)
                    return dest.subSequence(dstart, dend);
                return "";
            }

            return null;
        }
    }
}
