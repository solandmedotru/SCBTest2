package ru.solandme.scbtest2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private List<String> rowItems;
    private OnRowSelectedListener listener;

    public RVAdapter(List<String> rowItems, OnRowSelectedListener listener) {
        this.rowItems = rowItems;
        this.listener = listener;
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
                    listener.onRowSelected(adapterPosition, rowItems.get(adapterPosition));
                }
            }
        });
        return h;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(rowItems.get(position));
        holder.image.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        if (null == rowItems) {
            return 0;
        } else {
            return rowItems.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.rv_image);
            text = (TextView) v.findViewById(R.id.rv_text);
        }
    }

    interface OnRowSelectedListener {
        void onRowSelected(int position, String result);
    }
}


