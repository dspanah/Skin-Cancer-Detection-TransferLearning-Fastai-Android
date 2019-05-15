package com.github.dspanah.skincancerdetector;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SkinLesionAdapter extends ArrayAdapter<SkinLesion> {

    public SkinLesionAdapter(@NonNull Context context, List<SkinLesion> skinLesions) {
        super(context, 0, skinLesions);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_listview, parent, false);
        }

        SkinLesion skinLesion = getItem(position);

        String lesion = skinLesion.getLesionType();
        TextView lesionTextView = (TextView) listItemView.findViewById(R.id.lesion);
        lesionTextView.setText(lesion);

        String probability = skinLesion.getProbability();
        probability = probability + "%";
        TextView probabilityTextView = (TextView) listItemView.findViewById(R.id.probability);
        probabilityTextView.setText(probability);

        return listItemView;
    }
}