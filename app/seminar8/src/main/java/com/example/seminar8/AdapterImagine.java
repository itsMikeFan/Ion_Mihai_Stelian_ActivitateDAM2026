package com.example.seminar8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterImagine extends BaseAdapter {
    private List<ImagineSmoothie> imagini;
    private Context ctx;
    private int resursaLayout;

    public AdapterImagine(List<ImagineSmoothie> imagini, Context ctx, int resursaLayout) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int position) {
        return imagini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(resursaLayout, parent, false);

        ImagineSmoothie imagine = imagini.get(position);

        TextView tvDescriere = view.findViewById(R.id.TV_descriere);
        tvDescriere.setText(imagine.getTextAfisat());

        ImageView ivImagine = view.findViewById(R.id.IV_imagine);
        ivImagine.setImageBitmap(imagine.getImagine());

        return view;
    }
}
