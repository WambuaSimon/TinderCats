package com.simon.cattinder.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.simon.cattinder.R;
import com.simon.cattinder.db.DatabaseCallback;
import com.simon.cattinder.db.LocalCacheManager;
import com.simon.cattinder.model.CatModel;
import com.simon.cattinder.network.ApiClient;
import com.simon.cattinder.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements DatabaseCallback {

    private CompositeDisposable disposable = new CompositeDisposable();
    ImageView cat_image_view;
    Button dislike;
    Button like;
    ApiInterface apiInterface;
    List<CatModel> list;
    TextView imageId;
    String image_url, image_id;
    int button_clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageId = findViewById(R.id.image_id);
        cat_image_view = findViewById(R.id.cat_image);
        dislike = findViewById(R.id.dislike);
        like = findViewById(R.id.like);

        apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        list = new ArrayList<>();

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCatsData();
                button_clicked = 1;
                add(view);
            }
        });

        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCatsData();
                button_clicked = 0;
                add(view);
            }
        });

        getCatsData();
    }



    void getCatsData() {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading...");
        progress.setCancelable(false);
        progress.show();
        Call<List<CatModel>> catsCall = apiInterface.getCatModel();
        catsCall.enqueue(new Callback<List<CatModel>>() {
            @Override
            public void onResponse(Call<List<CatModel>> call, Response<List<CatModel>> response) {
                if (response.isSuccessful()) {
                    progress.dismiss();
                    List<CatModel> cats = response.body();
                    image_url = cats.get(0).image_url;
                    image_id = cats.get(0).image_id;

                    Glide.with(MainActivity.this).load(image_url).into(cat_image_view);


                }

            }

            @Override
            public void onFailure(Call<List<CatModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onCatAdded() {
        Log.d("room", "onUserAdded");
        Toasty.success(getApplicationContext(), "Vote added successfully", Toast.LENGTH_SHORT, true).show();

    }

    @Override
    public void onDataNotAvailable() {
        Log.d("room", "onDataNotAvailable");
    }

    public void add(View view) {
        LocalCacheManager.getInstance(this).addUser(this, image_id, button_clicked);


    }
}
