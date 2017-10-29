package com.udacity.sanchitsharma.newsappsanchit_udacity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.udacity.sanchitsharma.newsappsanchit_udacity.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsClass>> {

    private static final int loaderId = 1;
    private final String url = "http://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2017-01-01&api-key=test&page-size=20&show-tags=contributor";
    ActivityMainBinding binding;
    private Adapter adapter;
    //LinearLayout newsListView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);


        binding.emptyMsg.setText(getString(R.string.nonews));
        binding.list.setEmptyView(binding.emptyMsg);


        adapter = new Adapter(this, new ArrayList<NewsClass>());
        binding.list.setAdapter(adapter);

        //newsListView1 = (LinearLayout) findViewById(R.id.list);


        //newsListView1.setAdapter(adapter);

        binding.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsClass currentNews = adapter.getItem(position);

                Uri newsUri = Uri.parse(currentNews.getWebUrl());

                if (currentNews.getWebUrl() == null || TextUtils.isEmpty(currentNews.getWebUrl())) {
                    Toast.makeText(MainActivity.this, R.string.nolinkfound, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, newsUri);
                    startActivity(intent);
                }
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();

            loaderManager.initLoader(loaderId, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.spinner);
            loadingIndicator.setVisibility(View.GONE);

            binding.emptyMsg.setText(getString(R.string.nointernet));
        }
    }

    @Override
    public Loader<List<NewsClass>> onCreateLoader(int id, Bundle args) {
        return new LoaderClass(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsClass>> loader, List<NewsClass> data) {
        binding.spinner.setVisibility(View.GONE);

        binding.emptyMsg.setText(getString(R.string.nonews));

        adapter.clear();

        if (data != null && !data.isEmpty()) {
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsClass>> loader) {
        adapter.clear();
    }
}

