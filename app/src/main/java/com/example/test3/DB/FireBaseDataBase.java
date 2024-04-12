package com.example.test3.DB;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.test3.repostory.FurnitureModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
        db.collection("users")
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

    public void AddFurniture(String email, String Name, String Price, String Length, String Width, String Height, String Color, String Type)
    {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.getData().get("Email").toString().equals(email))
                                {
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("Name",Name);
                                    user.put("Price",Price);
                                    user.put("Length",Length);
                                    user.put("Width",Width);
                                    user.put("Height",Height);
                                    user.put("Color",Color);
                                    user.put("Type", Type);

                                    // Add a new document with a generated ID
                                    db.collection("users").document(document.getId()).collection("Furniture")
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
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public interface searchDone
    {
        void onSearchDone(LinkedList<FurnitureModel> list);
    }
    public void getSomeFurniture(String Furniture, searchDone callback)
    {
        LinkedList<FurnitureModel> list = new LinkedList<>();
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    int index = task.getResult().size();
                    AtomicInteger index2 = new AtomicInteger(0);
                    for (DocumentSnapshot document : task.getResult()) {
                        db.collection("users").document(document.getId()).collection("Furniture").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()) {
                                    index2.set(index2.get() + 1);
                                    for (DocumentSnapshot document : task.getResult()) {
                                        if (document.getData().get("Name").toString().equals(Furniture)) {
                                            FurnitureModel temp = new FurnitureModel(document.getData().get("Name").toString(), document.getData().get("Price").toString(), document.getData().get("Length").toString(), document.getData().get("Width").toString(), document.getData().get("Height").toString(), document.getData().get("Color").toString(), document.getData().get("Type").toString());
                                            list.add(temp);
                                        }
                                    }
                                    if (index2.get() == index) {
                                        callback.onSearchDone(list);
                                    }
                                }
                            }
                        });
                    }

                }
            }
        });
    }

}


