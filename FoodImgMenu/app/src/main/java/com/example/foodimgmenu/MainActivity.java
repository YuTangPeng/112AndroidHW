package com.example.foodimgmenu;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Integer> selectedFoodImages = new ArrayList<>();
    private LinearLayout imageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化 ImageView 容器
        imageContainer = findViewById(R.id.image_container);

        // 获取所有的 CheckBox
        CheckBox checkboxBurger = findViewById(R.id.checkbox_burger);
        CheckBox checkboxFrenchFry = findViewById(R.id.checkbox_frenchfry);
        CheckBox checkboxSoftDrink = findViewById(R.id.checkbox_softdrink);
        CheckBox checkboxSoup = findViewById(R.id.checkbox_soup);

        // 设置 CheckBox 的监听器
        CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateSelectedFoodImages();
            }
        };

        checkboxBurger.setOnCheckedChangeListener(checkBoxListener);
        checkboxFrenchFry.setOnCheckedChangeListener(checkBoxListener);
        checkboxSoftDrink.setOnCheckedChangeListener(checkBoxListener);
        checkboxSoup.setOnCheckedChangeListener(checkBoxListener);
    }

    // 更新选定的食物图片
    private void updateSelectedFoodImages() {
        selectedFoodImages.clear();

        // 获取所有的CheckBox
        CheckBox checkboxBurger = findViewById(R.id.checkbox_burger);
        CheckBox checkboxFrenchFry = findViewById(R.id.checkbox_frenchfry);
        CheckBox checkboxSoftDrink = findViewById(R.id.checkbox_softdrink);
        CheckBox checkboxSoup = findViewById(R.id.checkbox_soup);

        // 检查哪些食物被选中
        if (checkboxBurger.isChecked()) {
            selectedFoodImages.add(R.drawable.burger);
        }
        if (checkboxFrenchFry.isChecked()) {
            selectedFoodImages.add(R.drawable.frenchfry);
        }
        if (checkboxSoftDrink.isChecked()) {
            selectedFoodImages.add(R.drawable.softdrink);
        }
        if (checkboxSoup.isChecked()) {
            selectedFoodImages.add(R.drawable.soup);
        }

        // 更新ImageView显示
        imageContainer.removeAllViews();

        int imageSize = getResources().getDimensionPixelSize(R.dimen.image_size); // 获取定义的图片尺寸

        for (int imageResource : selectedFoodImages) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageResource);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    imageSize,
                    imageSize
            );
            layoutParams.setMargins(10, 0, 10, 0);
            imageView.setLayoutParams(layoutParams);
            imageContainer.addView(imageView);
        }
    }
}
