package com.enzo.greadfood.pojo.FireBase;

import android.support.annotation.NonNull;

import com.enzo.greadfood.domain.model.User;
import com.enzo.greadfood.domain.repository.SignupRepository;
import com.enzo.greadfood.util.CustomLog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupRepositoryImpl implements SignupRepository {
    boolean isSigup = false, success = false;
    private String id, pass, name, message = "";
    public static SignupRepositoryImpl getInstance(String id, String name, String pass){
        return new SignupRepositoryImpl(id,name, pass);
    }

    public SignupRepositoryImpl(String id, String name, String pass){
        this.id = id;
        this.pass = pass;
        this.name = name;
        checkSigup();
    }

    private void checkSigup(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = firebaseDatabase.getReference("user");
        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(id).exists()){
                    isSigup = false;
                }else{
                    User user = new User(name, pass);
                    table_user.child(id).setValue(user);
                    isSigup = true;
                }
                success = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public boolean isSignup() {
        while (!success){

        }
        CustomLog.i("Return Signup Repository", "Is SignUp " + isSigup);
        return isSigup;
    }
}
