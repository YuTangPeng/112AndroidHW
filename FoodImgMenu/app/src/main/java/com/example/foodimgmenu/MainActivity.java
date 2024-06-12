package com.example.foodimgmenu;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImageView image1, image2, image3, image4, image5;
    private CheckBox[] checkBoxes;
    private TextView Output;
    private int[] chkIDs = {R.id.chk1, R.id.chk2, R.id.chk3, R.id.chk4, R.id.chk5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        image1 = findViewById(R.id.output1);
        image2 = findViewById(R.id.output2);
        image3 = findViewById(R.id.output3);
        image4 = findViewById(R.id.output4);
        image5 = findViewById(R.id.output5);

        checkBoxes = new CheckBox[chkIDs.length];
        for (int i = 0; i < chkIDs.length; i++) {
            checkBoxes[i] = findViewById(chkIDs[i]);
            final int finalI = i;
            checkBoxes[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        showImage(finalI);
                        updateOrder(finalI);
                    } else {
                        hideImage(finalI);
                        checkAnyChecked();
                    }
                }
            });
        }

        Output = findViewById(R.id.showOrder);
        checkAnyChecked(); // 初始狀態檢查是否有勾選
    }

    private void showImage(int index) {
        switch (index) {
            case 0:
                image1.setVisibility(View.VISIBLE);
                break;
            case 1:
                image2.setVisibility(View.VISIBLE);
                break;
            case 2:
                image3.setVisibility(View.VISIBLE);
                break;
            case 3:
                image4.setVisibility(View.VISIBLE);
                break;
            case 4:
                image5.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void hideImage(int index) {
        switch (index) {
            case 0:
                image1.setVisibility(View.GONE);
                break;
            case 1:
                image2.setVisibility(View.GONE);
                break;
            case 2:
                image3.setVisibility(View.GONE);
                break;
            case 3:
                image4.setVisibility(View.GONE);
                break;
            case 4:
                image5.setVisibility(View.GONE);
                break;
        }
    }

    private void updateOrder(int index) {
        String[] orders = {"漢堡", "薯條", "可樂", "玉米濃湯", "咖啡"};
        if (index >= 0 && index < orders.length) {
            Output.setText("你點的餐點如下：");
        }
    }

    private void checkAnyChecked() {
        boolean anyChecked = false;
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                anyChecked = true;
                break;
            }
        }
        if (!anyChecked) {
            Output.setText("請點餐：");
        }
    }
}
