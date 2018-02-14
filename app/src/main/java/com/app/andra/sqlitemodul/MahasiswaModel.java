package com.app.andra.sqlitemodul;

/**
 * Created by Komang Candra Brata on 2/13/2018.
 */

public class MahasiswaModel {
    private int id;
    private String name;
    private String nim;

    public MahasiswaModel() {

    }

    public MahasiswaModel(String name, String nim) {
        this.name = name;
        this.nim = nim;
    }

    public MahasiswaModel(int id, String name, String nim) {
        this.id = id;
        this.name = name;
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}