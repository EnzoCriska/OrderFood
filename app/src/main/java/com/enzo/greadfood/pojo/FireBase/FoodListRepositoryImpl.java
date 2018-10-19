package com.enzo.greadfood.pojo.FireBase;

import android.support.annotation.NonNull;

import com.enzo.greadfood.domain.model.Category;
import com.enzo.greadfood.domain.model.Food;
import com.enzo.greadfood.domain.repository.FoodListRepository;
import com.enzo.greadfood.util.CustomLog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Queue;

public class FoodListRepositoryImpl implements FoodListRepository {
    final ArrayList<Food> list = new ArrayList<>();
    String menu_id;

    public static FoodListRepositoryImpl getInstance(String menu_id){
        return new FoodListRepositoryImpl(menu_id);
    }

    public FoodListRepositoryImpl(String menu_id){

        this.menu_id = menu_id;
        getData();
    }


    private void getData(){
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference table_food = firebaseDatabase.getReference("Food");
        list.clear();
        table_food.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                synchronized (list){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            String key = snapshot.getKey();
                            Food food = dataSnapshot.child(key).getValue(Food.class);
                            if (food.getMenu_id().equals(menu_id))
                                list.add(food);
                        }
                    list.notify();
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        CustomLog.i("GetDATA" , "Return List  get Data size :" + list.size());
    }


    @Override
    public ArrayList<Food> getFoodList() {
        synchronized (list){
            if (list.isEmpty()){
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
