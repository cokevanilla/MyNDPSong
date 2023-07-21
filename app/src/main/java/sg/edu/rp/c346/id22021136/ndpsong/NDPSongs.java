package sg.edu.rp.c346.id22021136.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class NDPSongs extends AppCompatActivity {
    ArrayList<String> songs;
    ListView lv;
    Button showAll, back;
    ArrayList<Songs> al;
    ArrayAdapter<Songs> aa;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndpsongs);

        lv = findViewById(R.id.lvDisplay);
        showAll = findViewById(R.id.btn5);
        back = findViewById(R.id.btnback);

        al = new ArrayList<>();
        DBHelper db = new DBHelper(NDPSongs.this);
        al.clear();
        al.addAll(db.getSongs());
        adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Songs data = al.get(position);
                Intent i = new Intent(NDPSongs.this, NDPSongsEdit.class);
                i.putExtra("ID", data);
                i.putExtra("Title", data);
                i.putExtra("Singers", data);
                i.putExtra("Year", data);
                i.putExtra("Stars", data);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(NDPSongs.this);
                al.clear();
                al.addAll(dbh.getFiveStarSongs());
                adapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the list of songs here
        refreshSongList();
    }

    private void refreshSongList() {
        DBHelper db = new DBHelper(NDPSongs.this);
        al.clear();
        al.addAll(db.getSongs());
        adapter.notifyDataSetChanged();
    }
}
