package com.example.test3.DB;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

public class FireBaseDataBase
{

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public void AddUser(String firstName, String lastName, String password, String email, String phone)
    {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("firstName",firstName);
        user.put("lastName",lastName);
        user.put("Password",password);
        user.put("Email",email);
        user.put("Phone",phone);

// Add a new document with a generated ID
        db.collection("users").document().collection("user1")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void AddFurniture(String name)
    {

    }

}


