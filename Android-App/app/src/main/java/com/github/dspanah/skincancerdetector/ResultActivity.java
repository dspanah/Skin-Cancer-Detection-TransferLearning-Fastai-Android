package com.github.dspanah.skincancerdetector;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private SkinLesionAdapter mAdapter;
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Detection Result");

        Intent intent = getIntent();
        List<SkinLesion> skinLesions = (ArrayList<SkinLesion>) intent.getSerializableExtra("skinLesions");

        ListView skinLesionsListView = (ListView) findViewById(R.id.list);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.listview_header, skinLesionsListView, false);
        skinLesionsListView.addHeaderView(header, null, false);
        skinLesionsListView.setEmptyView(mEmptyStateTextView);
        mAdapter = new SkinLesionAdapter(this, new ArrayList<SkinLesion>());
        skinLesionsListView.setAdapter(mAdapter);

        mAdapter.addAll(skinLesions);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}