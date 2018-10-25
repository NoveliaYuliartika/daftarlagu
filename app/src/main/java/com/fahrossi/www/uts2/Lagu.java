package com.fahrossi.www.uts2;

public class Lagu {

    private int ID;
    private String Judul;
    private String Nama_band;
    private String Genre ;


    public Lagu(int id, String judul, String nama_band, String genre){
        ID = id;
        Judul = judul;
        Nama_band = nama_band;
        Genre = genre;
    }

    public int getID(){
        return ID;
    }
    public void setID(int id){ ID = id; }

    public String getJudul(){
        return Judul;
    }
    public void setJudul(String judul){ Judul = judul;
    }

    public String getNama_band(){
        return Nama_band;
    }
    public void setNama_band(String nama_band){ Nama_band = nama_band ;
    }

    public String getGenre(){
        return Genre;
    }
    public void setGenre(String genre){ Genre = genre; }
}
