package com.udacity.sanchitsharma.newsappsanchit_udacity;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

/**
 * Created by sanchitsharma on 10/29/17.
 */

public class LoaderClass extends AsyncTaskLoader<List<NewsClass>> {

    public static final String LOG_TAG = LoaderClass.class.getSimpleName();

    private String mUrl;

    public LoaderClass(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsClass> loadInBackground() {
        Log.d(LOG_TAG, "loadInBackground()");
        if (mUrl == null) {
            return null;
        }

        List<NewsClass> newsList = QueryUtils.fetchNewsData(mUrl);
        if (newsList.size() == 0) {
            Log.d(LOG_TAG, "news list is empty");
        } else {
            Log.d(LOG_TAG, newsList.get(0).getTitle());
        }
        return newsList;
    }
}