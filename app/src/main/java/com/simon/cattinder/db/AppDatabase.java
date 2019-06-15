package com.simon.cattinder.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.simon.cattinder.dao.CatsDao;
import com.simon.cattinder.model.CatModel;
import com.simon.cattinder.model.VoteModel;

@Database(entities = {VoteModel.class}, version = 1,  exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
public abstract CatsDao catsDao();

}
