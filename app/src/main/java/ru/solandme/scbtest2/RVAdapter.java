package ru.solandme.scbtest2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private List<String> listItems;
    private OnRowSelectedListener listener;
    private ArrayList<Integer> images;

    RVAdapter(List<String> listItems, OnRowSelectedListener listener, ArrayList<Integer> images) {
        this.listItems = listItems;
        this.listener = listener;
        this.images = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_row, parent, false);

        final RVAdapter.ViewHolder h = new RVAdapter.ViewHolder(v) {
        };
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = h.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onRowSelected(adapterPosition);
                }
            }
        });
        return h;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(listItems.get(position));
        holder.image.setImageResource(images.get(position%4));
    }

    @Override
    public int getItemCount() {
        if (null == listItems) {
            return 0;
        } else {
            return listItems.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.rv_image);
            text = (TextView) v.findViewById(R.id.rv_text);
        }
    }

    interface OnRowSelectedListener {
        void onRowSelected(int position);
    }
}


