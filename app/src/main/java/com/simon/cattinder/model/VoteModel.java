package com.simon.cattinder.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "votes")
public class VoteModel {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    public String image_id;

    public int vote_id;

    public VoteModel( String image_id, int vote_id) {
        this.image_id = image_id;
        this.vote_id = vote_id;
    }



}
