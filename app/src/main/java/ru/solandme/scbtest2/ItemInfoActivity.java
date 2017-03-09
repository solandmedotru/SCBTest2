package ru.solandme.scbtest2;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemInfoActivity extends AppCompatActivity {

    public static final String ITEM_TEXT_KEY = "text";
    public static final String ITEM_POSITION_KEY = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        TypedArray imgs = getResources().obtainTypedArray(R.array.imagesArray);

        int position = getIntent().getIntExtra(ITEM_POSITION_KEY, 0);
        String itemText = getIntent().getStringExtra(ITEM_TEXT_KEY);

        TextView text = (TextView) findViewById(R.id.textView);
        ImageView image = (ImageView) findViewById(R.id.imageView);

        text.setText(itemText);
        image.setImageResource(imgs.getResourceId(position%4, 0));
        imgs.recycle();
    }
}
