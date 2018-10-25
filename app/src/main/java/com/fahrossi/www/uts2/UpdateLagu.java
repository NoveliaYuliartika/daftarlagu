package com.fahrossi.www.uts2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateLagu extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn1,btn2;
    TextView text3, text4, text5, text6, text7;
    String textID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lagu);

        dbHelper = new MyDataHelper(this);
        text3 = findViewById(R.id.editText3);
        text4 = findViewById(R.id.editText4);
        text5 = findViewById(R.id.editText5);
//        text6 = findViewById(R.id.editText6);
//        text7 = findViewById(R.id.editText7);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM lagu WHERE judul = '"
                +getIntent().getStringExtra("judul") + "'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            textID = cursor.getString(0);
            text3.setText(cursor.getString(1));
            text4.setText(cursor.getString(2));
            text5.setText(cursor.getString(3));
//            text6.setText(cursor.getString(4));
//            text7.setText(cursor.getString(5));
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE lagu SET judul='"+text3.getText().toString()+"'," +
                        "nama_band='"+text4.getText().toString()+"'," +
//                        "ukuran='"+text5.getText().toString()+"'," +
//                        "aktor='"+text6.getText().toString()+"'," +
                        "genre='"+text5.getText().toString()+"' WHERE id ="+Integer.parseInt(textID)+"");
                Toast.makeText(getApplicationContext(), "berhasil dirubah",
                        Toast.LENGTH_LONG).show();
                DaftarLagu.layarutama.RefreshData();
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}
