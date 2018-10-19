package com.enzo.greadfood.presentation.UI.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.enzo.greadfood.R;
import com.enzo.greadfood.domain.model.Category;
import com.enzo.greadfood.domain.model.Food;
import com.squareup.picasso.Picasso;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {
    private FoodListAdapter.IData iData;
    private FoodListAdapter.OnClickFood onClickFood;
    private String TAG = "Adapter RecyclerView";
    private Context context;

    public FoodListAdapter(FoodListAdapter.OnClickFood onClickFood, Context context) {
        this.onClickFood = onClickFood;
        this.context = context;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setiData(FoodListAdapter.IData iData) {
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
            onClickFood.onClickItemFood(this.getPosition());
        }

        public void bindData(Food food) {
            mTextView.setText(food.getName());
            String url = food.getImage();
            try{
                Picasso.with(context)
                        .load(food.getImage())
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
    public FoodListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View messView = inflater.inflate(R.layout.itemrecycler, parent, false);
        FoodListAdapter.ViewHolder viewHolder = new FoodListAdapter.ViewHolder(messView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Food food = iData.getItem(i);
        viewHolder.bindData(food);
    }

    @Override
    public int getItemCount() {
        return iData.getCount();
    }


    public interface IData {
        int getCount();

        Food getItem(int pos);
    }

    public interface OnClickFood{
        void onClickItemFood(int position);
    }
}
