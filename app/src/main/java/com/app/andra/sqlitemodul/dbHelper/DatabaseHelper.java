package com.app.andra.sqlitemodul.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.app.andra.sqlitemodul.dbHelper.DatabaseContract.MahasiswaColumns.NAMA;
import static com.app.andra.sqlitemodul.dbHelper.DatabaseContract.MahasiswaColumns.NIM;
import static com.app.andra.sqlitemodul.dbHelper.DatabaseContract.TABLE_MAHASISWA;


/**
 * Created by dicoding on 12/1/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static String CREATE_TABLE_MAHASISWA = "create table " + TABLE_MAHASISWA +
            " (" + _ID + " integer primary key autoincrement, " +
            NAMA + " text not null, " +
            NIM + " text not null);";
    private static String DATABASE_NAME = "dbmahasiswa";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAHASISWA);

    }

    /*
    Method onUpgrade akan di panggil ketika terjadi perbedaan versi
    Gunakan method onUpgrade untuk melakukan proses migrasi data
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        Drop table tidak dianjurkan ketika proses migrasi terjadi dikarenakan data user akan hilang,
         */
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);
        onCreate(db);
    }


}
