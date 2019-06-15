package com.simon.cattinder.db;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.view.View;

import com.simon.cattinder.model.CatModel;
import com.simon.cattinder.model.VoteModel;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class LocalCacheManager {

    private static final String DB_NAME = "cats_db";
    private Context context;
    private static LocalCacheManager _instance;
    private AppDatabase db;


    public static LocalCacheManager getInstance(Context context) {
        if (_instance == null) {
            _instance = new LocalCacheManager(context);
        }
        return _instance;
    }

    public LocalCacheManager(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public void addUser(final DatabaseCallback databaseCallback, final String image_id, final int vote_id) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                VoteModel cats = new VoteModel(image_id, vote_id);
                db.catsDao().insertAll(cats);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                databaseCallback.onCatAdded();

            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }



}
