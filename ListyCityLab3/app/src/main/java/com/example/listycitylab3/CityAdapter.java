package  com.example.listycitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Modified ArrayAdapter<City> with the help ChatGPT
 * ChatGPT prompt - "Is there way to use the City class attributes
 * and have it go to the list view?," 2025-09-18
 *
 *
 *
 */
public class CityAdapter extends ArrayAdapter<City> {
    public CityAdapter(Context ctx, List<City> items) { super(ctx, 0, items); }

    @Override public View getView(int pos, View convertView, ViewGroup parent) {
        View row = (convertView != null)
                ? convertView
                : LayoutInflater.from(getContext()).inflate(R.layout.content,
                parent, false);

        City c = getItem(pos);
        ((TextView) row.findViewById(R.id.tvCityName)).setText(c.getName());
        ((TextView) row.findViewById(R.id.tvProvince)).setText(c.getProvince());
        return row;
    }
}
