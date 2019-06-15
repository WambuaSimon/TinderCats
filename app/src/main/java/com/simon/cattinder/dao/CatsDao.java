package com.simon.cattinder.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.simon.cattinder.model.VoteModel;

@Dao
public interface CatsDao {

    @Insert
    void insertAll(VoteModel... voteModels);

}
