package com.batch.roomwordssample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "word")
    private String mWord;


    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] mImage;

    public Word(){
        this.mWord = "";
        this.mImage = new byte[20];
    }

    public void setWord( String mWord) {
        this.mWord = mWord;
    }

    public String getWord() {
        return this.mWord;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return mImage;
    }

    public void setImage(byte[] image) {
        this.mImage = image;
    }
}