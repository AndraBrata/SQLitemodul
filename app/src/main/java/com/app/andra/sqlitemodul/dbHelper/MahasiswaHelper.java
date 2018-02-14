package com.app.andra.sqlitemodul.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.app.andra.sqlitemodul.MahasiswaModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.app.andra.sqlitemodul.dbHelper.DatabaseContract.MahasiswaColumns.NAMA;
import static com.app.andra.sqlitemodul.dbHelper.DatabaseContract.MahasiswaColumns.NIM;
import static com.app.andra.sqlitemodul.dbHelper.DatabaseContract.TABLE_MAHASISWA;

/**
 * Created by Komang Candra Brata on 2/13/2018.
 */

public class MahasiswaHelper {

    private Context context;
    private DatabaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public MahasiswaHelper(Context context) {
        this.context = context;
    }

    public MahasiswaHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }


    public ArrayList<MahasiswaModel> getAllData() {
        Cursor cursor = database.query(TABLE_MAHASISWA, null, null, null, null, null, _ID + " DESC", null);
        cursor.moveToFirst();
        ArrayList<MahasiswaModel> arrayList = new ArrayList<>();
        MahasiswaModel mahasiswaModel;
        if (cursor.getCount() > 0) {
            do {
                mahasiswaModel = new MahasiswaModel();
                mahasiswaModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                mahasiswaModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                mahasiswaModel.setNim(cursor.getString(cursor.getColumnIndexOrThrow(NIM)));
                arrayList.add(mahasiswaModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(MahasiswaModel mahasiswaModel) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(NAMA, mahasiswaModel.getName());
        initialValues.put(NIM, mahasiswaModel.getNim());
        return database.insert(TABLE_MAHASISWA, null, initialValues);
    }


    public int update(MahasiswaModel mahasiswaModel) {
        ContentValues args = new ContentValues();
        args.put(NAMA, mahasiswaModel.getName());
        args.put(NIM, mahasiswaModel.getNim());
        return database.update(TABLE_MAHASISWA, args, _ID + "= '" + mahasiswaModel.getId() + "'", null);
    }


    public int delete(int id) {
        return database.delete(TABLE_MAHASISWA, _ID + " = '" + id + "'", null);
    }

}
