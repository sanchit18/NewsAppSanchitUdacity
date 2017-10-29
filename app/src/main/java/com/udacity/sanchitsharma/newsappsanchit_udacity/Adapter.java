package com.udacity.sanchitsharma.newsappsanchit_udacity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
/**
 * Created by sanchitsharma on 10/29/17.
 */
public class Adapter extends ArrayAdapter<NewsClass> {
    public Adapter(Context context, ArrayList<NewsClass> newsArrayList) {
        super(context, 0, newsArrayList);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsClass news = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView section = (TextView) convertView.findViewById(R.id.section);
        TextView author = (TextView) convertView.findViewById(R.id.author);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        title.setText("Title: " + news.getTitle());
        section.setText("Section: " + news.getSection());
        author.setText("Author(s): " + news.getAuthor());
        date.setText("Date: " + news.getDate());
        return convertView;
    }
}