package com.enzo.greadfood.presentation.UI.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enzo.greadfood.R;
import com.enzo.greadfood.domain.model.Category;
import com.squareup.picasso.Picasso;

public class AdapterRecyclerViewCategory extends RecyclerView.Adapter<AdapterRecyclerViewCategory.ViewHolder> {
    private IData iData;
    private OnClickPhoto onClickPhoto;
    private String TAG = "Adapter RecyclerView";
    private Context context;

    public AdapterRecyclerViewCategory(OnClickPhoto onClickPhoto, Context context) {
        this.onClickPhoto = onClickPhoto;
        this.context = context;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setiData(IData iData) {
        this.iData = iData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener{
        ImageView mImageView;
        TextView mTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
            mTextView = itemView.findViewById(R.id.foodName);
            mImageView = itemView.findViewById(R.id.foodImage);
        }

        @Override
        public void onClick(View view) {
            onClickPhoto.onClickItemPhoto(this.getPosition());
        }

        public void bindData(Category category) {
            mTextView.setText(category.getName());
            String url = category.getUrl();
            try{
                Picasso.with(context)
                        .load(category.getUrl())
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .into(mImageView);
                mImageView.setOnClickListener(this);
            }catch (IllegalArgumentException e){
                Log.e("ERROR", "Path is empty");
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select the Action");
//            menu.add(Menu.NONE, R.id.deleteReport, Menu.NONE,"Delete report");
//            menu.add(Menu.NONE, R.id.changereports, Menu.NONE, "Change Report");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View messView = inflater.inflate(R.layout.itemrecycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(messView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Category category = iData.getItem(position);
        holder.bindData(category);
    }

    @Override
    public int getItemCount() {
        return iData.getCount();
    }


    public interface IData {
        int getCount();

        Category getItem(int pos);
    }

    public interface OnClickPhoto{
        void onClickItemPhoto(int position);
    }
}
