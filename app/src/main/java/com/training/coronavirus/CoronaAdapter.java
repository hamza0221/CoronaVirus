package com.training.coronavirus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoronaAdapter extends RecyclerView.Adapter<CoronaAdapter.MyViewHolder> {
public interface ItemClickListener{

    void onItemClickListener(int position);


}
private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private List<String>   coronaItems;
    private List<Integer>   coronaImages;

    public void setCoronaItems(List<String> coronaItems) {
        this.coronaItems = coronaItems;
        notifyDataSetChanged();
    }

    public void setCoronaImages(List<Integer> coronaImages) {
        this.coronaImages = coronaImages;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
public TextView textView;
public ImageView coronaImage;
        public MyViewHolder(View v) {
            super(v);
textView=v.findViewById(R.id.textView);
coronaImage=v.findViewById(R.id.corona_image);
        }
    }

    public CoronaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.corona_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

holder.textView.setText(coronaItems.get(position));

holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(itemClickListener!=null)
            itemClickListener.onItemClickListener(position);
    }
});
holder.coronaImage.setImageDrawable( holder.itemView.getContext().getDrawable( coronaImages.get(position)));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return coronaItems.size();
    }
}
