package com.example.spandlv;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Spinner spFoodCategory; // 食物類別的下拉選單
    private ListView lvItems; // 顯示食物項目的列表視圖
    private TextView main, sidedish, drink; // 顯示選擇結果的文本視圖

    private String[] food, hamburger, snack, drinks; // 食物類別和選項
    private ArrayAdapter<String> itemsAdapter; // 列表視圖的適配器

    private String selectedMainCourse = "請選擇"; // 選擇的主餐
    private String selectedSideDish = "請選擇"; // 選擇的附餐
    private String selectedDrink = "請選擇"; // 選擇的飲料




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化UI元素
        spFoodCategory = findViewById(R.id.spinner);
        lvItems = findViewById(R.id.listview);
        main = findViewById(R.id.main);
        sidedish = findViewById(R.id.sidedish);
        drink = findViewById(R.id.drink);

        // 從資源獲取食物類別和選項
        food = getResources().getStringArray(R.array.food);
        hamburger = getResources().getStringArray(R.array.hamburger);
        snack = getResources().getStringArray(R.array.snack);
        drinks = getResources().getStringArray(R.array.drinks);

        // 設置Spinner適配器
        ArrayAdapter<String> foodCategoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, food);
        spFoodCategory.setAdapter(foodCategoryAdapter);

        // 設置ListView適配器
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lvItems.setAdapter(itemsAdapter);

        // 設置Spinner選擇監聽器
        spFoodCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 當選擇食物類別時，更新ListView
                updateListView(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 當未選擇任何食物類別時，不做任何操作
            }
        });

        // 設置ListView項目點擊監聽器
        lvItems.setOnItemClickListener((parent, view, position, id) -> {
            // 當點擊ListView項目時，更新選擇的食物
            String selectedItem = (String) parent.getItemAtPosition(position);
            updateSelectedItems(selectedItem);
        });
    }

    // 更新ListView中的食物項目
    private void updateListView(int categoryIndex) {
        String[] items = null;
        switch (categoryIndex) {
            case 0:
                items = hamburger;
                break;
            case 1:
                items = snack;
                break;
            case 2:
                items = drinks;
                break;
        }
        if (items != null) {
            itemsAdapter.clear();
            itemsAdapter.addAll(items);
            itemsAdapter.notifyDataSetChanged();
        }
    }

    // 根據選擇的類別更新相應的TextView
    private void updateSelectedItems(String item) {
        int categoryIndex = spFoodCategory.getSelectedItemPosition();
        switch (categoryIndex) {
            case 0:
                selectedMainCourse = item;
                main.setText("主餐：" + selectedMainCourse);
                break;
            case 1:
                selectedSideDish = item;
                sidedish.setText("附餐：" + selectedSideDish);
                break;
            case 2:
                selectedDrink = item;
                drink.setText("飲料：" + selectedDrink);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 創建選單
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 處理選單項目點擊事件
        int itemId = item.getItemId();
        if (itemId == R.id.send) {
            submitOrder();
            return true;
        } else if (itemId == R.id.cancel) {
            cancelOrder();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // 處理送出訂單邏輯
    private void submitOrder() {
        // 创建一个 Intent 对象，指定要启动的 Activity
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        // 将当前 TextView 的文本内容作为额外数据传递给第二个页面
        intent.putExtra("main_text", main.getText().toString());
        intent.putExtra("sidedish_text", sidedish.getText().toString());
        intent.putExtra("drink_text", drink.getText().toString());
        // 启动第二个页面
        startActivity(intent);
    }

    // 處理取消訂單邏輯
    private void cancelOrder() {
        selectedMainCourse = "主餐：" + "請選擇";
        selectedSideDish = "附餐：" + "請選擇";
        selectedDrink = "飲料：" + "請選擇";

        main.setText(selectedMainCourse);
        sidedish.setText(selectedSideDish);
        drink.setText(selectedDrink);


    }
}
