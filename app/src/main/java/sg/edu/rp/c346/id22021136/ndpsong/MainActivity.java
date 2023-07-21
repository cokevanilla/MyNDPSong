package sg.edu.rp.c346.id22021136.ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button insert, display;
    EditText title, singers, year;
    RadioGroup starsGroup;
    ArrayList<Songs> al;
    ArrayAdapter<Songs> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.btnInsert);
        display = findViewById(R.id.btnDisplay);
        title = findViewById(R.id.title);
        singers = findViewById(R.id.singers);
        year = findViewById(R.id.year);
        starsGroup = findViewById(R.id.stars);
        al = new ArrayList<>();
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                String stTitle = String.valueOf(title.getText());
                String stSingers = String.valueOf(singers.getText());
                int iYear = Integer.parseInt(String.valueOf(year.getText()));
                int starss = 0;
                int checkedRadioId = starsGroup.getCheckedRadioButtonId();
                if (checkedRadioId == R.id.star1) {
                    starss += 1;
                } else if (checkedRadioId == R.id.star2) {
                    starss += 2;
                } else if (checkedRadioId == R.id.star3) {
                    starss += 3;
                } else if (checkedRadioId == R.id.star4) {
                    starss += 4;
                } else if (checkedRadioId == R.id.star5) {
                    starss += 5;
                }

                db.insertSong(stTitle, stSingers, iYear, starss);
            }
        });

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NDPSongs.class);
                startActivity(i);
            }
        });
    }
}
