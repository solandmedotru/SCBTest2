package ru.solandme.scbtest2;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RVAdapter.OnRowSelectedListener {
    ArrayList<String> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);

        rv.setHasFixedSize(true);
        rv.setItemViewCacheSize(20);
        rv.setDrawingCacheEnabled(true);
        rv.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        RVAdapter adapter = new RVAdapter(getListItems(), this, getImagesList());
        rv.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(rv.getContext(), linearLayoutManager.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);

    }

    private ArrayList<String> getListItems() {
        TypedArray array = getResources().obtainTypedArray(R.array.imagesArray);

        listItems = new ArrayList<>(1000000);
        for (int i = 0; i < 1000000; i++) {
            listItems.add("Some text for item number " + i);
        }
        array.recycle();
        return listItems;
    }

    @Override
    public void onRowSelected(int position) {
        Intent intent = new Intent(MainActivity.this, ItemInfoActivity.class);
        intent.putExtra(ItemInfoActivity.ITEM_TEXT_KEY, listItems.get(position));
        intent.putExtra(ItemInfoActivity.ITEM_POSITION_KEY, position);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listItems.clear();
    }

    public ArrayList<Integer> getImagesList() {
        TypedArray imgs = getResources().obtainTypedArray(R.array.imagesArray);
        ArrayList<Integer> images = new ArrayList<>(10);
        for (int i =0; i <10; i++){
            images.add(imgs.getResourceId(i%4, 0));
        }
        imgs.recycle();
        return images;
    }
}
