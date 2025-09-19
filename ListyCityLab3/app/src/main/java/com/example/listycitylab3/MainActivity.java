package com.example.listycitylab3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public class MainActivity extends AppCompatActivity
        implements EditCityFragment.Listener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = new ArrayList<>(Arrays.asList(
                new City("Edmonton", "AB"),
                new City("Calgary", "AB"),
                new City("Toronto", "ON"),
                new City("Vancouver", "BC"),
                new City("Denver", "CO"),
                new City("Los Angeles", "CA")
        ));

        cityList = findViewById(R.id.city_list);

        cityAdapter = new CityAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemLongClickListener((parent, view, position, id) -> {
            City c = dataList.get(position);
            EditCityFragment.newInstance(position, c.getName(), c.getProvince())
                    .show(getSupportFragmentManager(), "editCity");
            return true;
        });
    }

    @Override
    public void onCityEdited(int position, String new_name, String new_province) {
        if (position < 0 || position >= dataList.size())
            return;
        City new_city = dataList.get(position);
        new_city.setName(new_name);
        new_city.setProvince(new_province);
        cityAdapter.notifyDataSetChanged();
    }
}

