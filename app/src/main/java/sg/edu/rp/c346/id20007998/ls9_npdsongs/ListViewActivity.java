package sg.edu.rp.c346.id20007998.ls9_npdsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    Button btnFilter;
    ListView lv;
    ArrayList<Song> songList;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        btnFilter=findViewById(R.id.btnFilter);
        lv=findViewById(R.id.lv);

        songList=new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this,android.R.layout.simple_list_item_1, songList);
        lv.setAdapter(aa);

        DBHelper dbh = new DBHelper(ListViewActivity.this);

        songList.clear();
        songList.addAll(dbh.getAllSong());
        aa.notifyDataSetChanged();
        

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = songList.get(position);
                Intent i = new Intent(ListViewActivity.this,
                        ModifyActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ListViewActivity.this);
                songList.clear();
                songList.addAll(dbh.getSongByFilter());
                aa.notifyDataSetChanged();

            }
        });



    }
    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(ListViewActivity.this);
        songList.clear();
        songList.addAll(dbh.getAllSong());
        aa.notifyDataSetChanged();
    }
}