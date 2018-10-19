package com.enzo.greadfood.pojo.FireBase;

import com.enzo.greadfood.domain.model.User;
import com.enzo.greadfood.domain.repository.UserRepository;

import com.enzo.greadfood.util.CustomLog;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FireBasesGetUserImpl implements UserRepository {
    boolean login, success = false;
    private String id, pass;
    public static FireBasesGetUserImpl getInstance(String id, String pass){
        return new FireBasesGetUserImpl(id, pass);
    }

    public FireBasesGetUserImpl(String id, String pass){
        this.id = id;
        this.pass = pass;
        processLogin();
    }

    public void processLogin(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference table_user = firebaseDatabase.getReference("user");

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(id).exists()){
                    User user = dataSnapshot.child(id).getValue(User.class);
                    if (user.getPassword().equals(pass)){
                        login = true;
                        CustomLog.i("CHECK LOGIN", "Login true " + login);
                    }else{
                        login = false;
                        CustomLog.i("CHECK LOGIN", "Login false");
                    }

                }else{
                    login = false;
                }
                success =true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean checkLogin(){
        while (!success){

        }
        CustomLog.i("RETURN LOGIN", "Login >>" + login);
        return login;
    }
}
