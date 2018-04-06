package com.ticker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mohit.soni on 6/4/18.
 */

public class TickeAdapter extends RecyclerView.Adapter<TickeAdapter.RecyclerViewHolder> {

    // recyclerview adapter
    private ArrayList<String> arrayList;
    private Context context;
    GetClick getClick;

    public TickeAdapter(Context context, ArrayList<String> arrayList,GetClick getClick) {
        this.context = context;
        this.arrayList = arrayList;
        this.getClick = getClick;

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        RecyclerViewHolder mainHolder = (RecyclerViewHolder) holder;
        mainHolder.title.setText(arrayList.get(position));
        mainHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send to get object value
                getClick.clickResutl(arrayList.get(position),position);
            }
        });
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.ticker_item, viewGroup, false);
        RecyclerViewHolder listHolder = new RecyclerViewHolder(mainGroup);
        return listHolder;

    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public RecyclerViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.text);
        }

    }

}
