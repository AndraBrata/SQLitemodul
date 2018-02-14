package com.app.andra.sqlitemodul;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.andra.sqlitemodul.dbHelper.MahasiswaHelper;

import java.util.ArrayList;

/**
 * Created by Komang Candra Brata on 2/13/2018.
 */


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText nama, nim;
    private Button tambah;
    private MahasiswaAdapter mahasiswaAdapter;
    private MahasiswaHelper mahasiswaHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        nama = (EditText) findViewById(R.id.edit_nama);
        nim = (EditText) findViewById(R.id.edit_nim);
        tambah = (Button) findViewById(R.id.btn_tambah);


        mahasiswaHelper = new MahasiswaHelper(this);
        mahasiswaAdapter = new MahasiswaAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAllData();

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tambah.getText().equals("update")) {


                } else {
                    insertData();
                    getAllData();
                }
            }
        });


    }

    private void insertData() {
        mahasiswaHelper.open();
        MahasiswaModel mahasiswa = new MahasiswaModel(nama.getText().toString(), nim.getText().toString());
        mahasiswaHelper.insert(mahasiswa);
        mahasiswaHelper.close();
    }

    private void getAllData() {
        mahasiswaHelper.open();
        // Ambil semua data mahasiswa di database
        ArrayList<MahasiswaModel> mahasiswaModels = mahasiswaHelper.getAllData();
        mahasiswaHelper.close();
        mahasiswaAdapter.addItem(mahasiswaModels);
        recyclerView.setAdapter(mahasiswaAdapter);
    }


}
