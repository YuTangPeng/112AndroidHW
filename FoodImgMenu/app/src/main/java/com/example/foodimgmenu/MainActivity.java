import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodimgmenu.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView selectedItemsTextView;
    private LinearLayout imageContainer;
    private ArrayList<CheckBox> checkBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedItemsTextView = findViewById(R.id.selectedItemsTextView);
        imageContainer = findViewById(R.id.imageContainer);

        checkBoxes = new ArrayList<>();
        checkBoxes.add(findViewById(R.id.burgerCheckBox));
        checkBoxes.add(findViewById(R.id.friesCheckBox));
        checkBoxes.add(findViewById(R.id.colaCheckBox));
        checkBoxes.add(findViewById(R.id.soupCheckBox));

        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateSelectedItems());
        }
    }

    private void updateSelectedItems() {
        ArrayList<String> selectedItems = new ArrayList<>();
        imageContainer.removeAllViews();

        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                selectedItems.add(checkBox.getText().toString());
                addImageToContainer(checkBox.getText().toString());
            }
        }

        StringBuilder selectedItemsText = new StringBuilder("你點的餐點如下:\n");
        for (String item : selectedItems) {
            selectedItemsText.append(item).append("\n");
        }
        selectedItemsTextView.setText(selectedItemsText.toString());
    }

    private void addImageToContainer(String itemName) {
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        imageView.setLayoutParams(layoutParams);

        Drawable drawable;
        switch (itemName) {
            case "漢堡":
                drawable = getResources().getDrawable(R.drawable.burger);
                break;
            case "薯條":
                drawable = getResources().getDrawable(R.drawable.frenchfry);
                break;
            case "可樂":
                drawable = getResources().getDrawable(R.drawable.softdrink);
                break;
            case "玉米濃湯":
                drawable = getResources().getDrawable(R.drawable.soup);
                break;
            default:
                drawable = null;
        }

        imageView.setImageDrawable(drawable);
        imageContainer.addView(imageView);
    }
}
