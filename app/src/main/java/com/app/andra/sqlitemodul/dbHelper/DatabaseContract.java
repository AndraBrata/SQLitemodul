package com.app.andra.sqlitemodul.dbHelper;

import android.provider.BaseColumns;

/**
 * Created by Komang Candra Brata on 2/13/2018.
 */

public class DatabaseContract {

    static String TABLE_MAHASISWA = "table_mahasiswa";

    static final class MahasiswaColumns implements BaseColumns {

        // MahasiswaModel nama
        static String NAMA = "nama";
        // MahasiswaModel nim
        static String NIM = "nim";

    }
}