package sg.edu.rp.c346.id22021136.ndpsong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Songs> {
    Context parent_context;
    int layout_id;
    ArrayList<Songs> songArrayList;

    public CustomAdapter(Context context, int resource, ArrayList<Songs> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        songArrayList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.tvTitleRow);
        TextView tvYear = rowView.findViewById(R.id.tvYearRow);
        TextView tvStar = rowView.findViewById(R.id.tvStarsRow);
        TextView tvSinger = rowView.findViewById(R.id.tvSingerRow);

        Songs currentVersion = songArrayList.get(position);

        tvTitle.setText(currentVersion.getTitle());
        tvYear.setText(String.valueOf(currentVersion.getYear()));
        tvStar.setText(currentVersion.getStars() + " Stars");
        tvSinger.setText(currentVersion.getSingers());

        return rowView;
    }
}
