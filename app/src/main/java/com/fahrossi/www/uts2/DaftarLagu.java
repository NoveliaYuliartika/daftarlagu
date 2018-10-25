package com.fahrossi.www.uts2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class DaftarLagu extends AppCompatActivity {

    private Button btn_logout;
    MyDataHelper dataHelper;
    public RecyclerView mRecycleView;
    public RecyclerView.LayoutManager mLayoutManager;
    public RecyclerView.Adapter mAdapter;
    List<Lagu> LLagu;

    public static DaftarLagu layarutama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_lagu);

        Button btn = findViewById(R.id.btn_post);

        //Register event onClick ke tombol btn
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(DaftarLagu.this, InsertLagu.class);
                startActivity(myintent);
            }
        });

        layarutama = this;

        RefreshData();

        LLagu = new ArrayList<>();

        LLagu.addAll(laguList());
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerView_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, laguList());
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(myAdapter);
        myrv.addOnItemTouchListener(new RecyclerTouchListener(this, mRecycleView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, final int position) {
                final CharSequence[] dialogitem = {"Lihat Lagu", "Update Lagu", "Hapus Lagu"};

                AlertDialog.Builder builder = new AlertDialog.Builder(DaftarLagu.this);

                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0 :
                                Intent intent0 = new Intent(getApplicationContext(), ViewLagu.class);
                                intent0.putExtra("judul", LLagu.get(position).getJudul());
                                startActivity(intent0);
                                break;

                            case 1 :
                                Intent intent1 = new Intent(getApplicationContext(), UpdateLagu.class);
                                intent1.putExtra("judul", LLagu.get(position).getJudul());
                                startActivity(intent1);
                                break;

                            case 2 :
                                SQLiteDatabase db = dataHelper.getWritableDatabase();
                                db.execSQL("DELETE FROM lagu WHERE id = '" + LLagu.get(position).getID() +
                                        "'");
                               RefreshData();
                                break;
                        }
                    }
                });
                builder.create().show();
            }

//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
        }));

    }

    public void RefreshData(){
        this.LLagu = new ArrayList<>();
        LLagu.addAll(laguList());
        mRecycleView = findViewById(R.id.recyclerView_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, LLagu);
        mRecycleView.setAdapter(myAdapter);
    }

    public List<Lagu> laguList(){
        dataHelper = new MyDataHelper(this);
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        List<Lagu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM lagu",null);

        if (cursor.getCount()>0){
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                Lagu l = new Lagu(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                list.add(l);
            }
        }

        return list;
    }



    public void button_onClick(View view)
    {
        SharedPreferences handler = getSharedPreferences("MainActivity", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = handler.edit();

        editor.clear();
        editor.apply();

        Intent i = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}
