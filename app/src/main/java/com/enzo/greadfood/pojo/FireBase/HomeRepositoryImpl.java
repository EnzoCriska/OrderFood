package com.enzo.greadfood.pojo.FireBase;

import android.support.annotation.NonNull;

import com.enzo.greadfood.domain.interactors.HomeInteractor;
import com.enzo.greadfood.domain.model.Category;
import com.enzo.greadfood.domain.model.User;
import com.enzo.greadfood.domain.repository.HomeRepository;
import com.enzo.greadfood.util.CustomLog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeRepositoryImpl implements HomeRepository {
    final ArrayList<Category> list = new ArrayList<>();
    private boolean success = false;
    public static HomeRepositoryImpl getInstance(){
        return new HomeRepositoryImpl();
    }

    public HomeRepositoryImpl(){
        getData();
    }

    public void getData(){
        success = false;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference table_category = firebaseDatabase.getReference("category");

        table_category.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                synchronized (list){
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                        String key = postSnapshot.getKey();
                        Category category = dataSnapshot.child(key).getValue(Category.class);
                        category.setId(key);
                        list.add(category);
                        CustomLog.i("GetDATA" , "Key: " + key + " -- Value: "+ category.getName());
                    }
                    list.notify();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        success = true;
        CustomLog.i("GetDATA" , "Return List  get Data size :" + list.size());
    }

    @Override
    public ArrayList<Category> getCategory() {
        synchronized (list){
            if (list.isEmpty()) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            CustomLog.i("GetDATA" , "Return List size :" + list.size());
            return list;
        }

    }
}
