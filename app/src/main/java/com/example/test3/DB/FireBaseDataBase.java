package com.example.test3.DB;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.test3.repostory.FurnitureModel;
import com.example.test3.ui.profileFragment.profileFragModel;
import com.example.test3.ui.start.StartActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class FireBaseDataBase {
    // Firebase Storage and Firestore instances
   private FirebaseStorage storage = FirebaseStorage.getInstance();
   private StorageReference storageRef = storage.getReference();
   private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Context context;

    public FireBaseDataBase(Context context)
    {
        this.context = context;

    }
    public FireBaseDataBase()
    {

    }

    // Method to remove a user by their email
    public void RemoveUserByEmail(String userEmail) {
        // Check if userEmail is valid
        if (userEmail == null || userEmail.isEmpty()) {
            Log.w(TAG, "Invalid userEmail");
            return;
        }

        // Query for the user document based on the email
        Query query = db.collection("users").whereEqualTo("Email", userEmail);

        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Delete each document found (should ideally be just one)
                                document.getReference().delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "Error deleting document", e);
                                            }
                                        });
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    // Method to add a new user
    public void AddUser(String firstName, String lastName,String email,String password, String phone) {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("Email", email);
        user.put("Password", password);
        user.put("Phone", phone);

        // Add a new document with a generated ID

        db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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

    // Interface to handle found email callback
//    public interface FoundEmail {
//        void onFoundEmail(String id);
//    }
//
//    // Method to find a user ID by email
//    public void FindId(String email, FoundEmail callback) {
//        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                for (DocumentSnapshot document : task.getResult()) {
//                    if (document.getData().get("Email").equals(email)) {
//                        callback.onFoundEmail(document.getId());
//                    }
//                }
//            }
//        });
//    }

    // Method to add furniture for a user
    public void AddFurniture(String phone,String email, String Name, String Price, String Length, String Width, String Height, String Color, String Type, Bitmap picture, Context context) {
        ProgressDialog pd = new ProgressDialog(context);
        pd.setTitle("Adding Furniture");
        pd.setCancelable(false);
        pd.show();

        String picPath = "images/" + UUID.randomUUID();
        // Create a reference to the image path
        StorageReference ImagesRef = storageRef.child(picPath);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        picture.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        ImagesRef.putBytes(data).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    db.collection("users")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        boolean userFound = false;
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            if (document.getData().get("Email").toString().equals(email)) {
                                                userFound = true;
                                                String id = document.getId();
                                                Map<String, Object> user = new HashMap<>();
                                                user.put("Name", Name);
                                                user.put("Price", Price);
                                                user.put("Length", Length);
                                                user.put("Width", Width);
                                                user.put("Height", Height);
                                                user.put("Color", Color);
                                                user.put("Type", Type);
                                                user.put("Email", email);
                                                user.put("PicPath", picPath);
                                                user.put("Phone",phone);

                                                // Add a new document with a generated ID
                                                db.collection("users").document(document.getId()).collection("Furniture")
                                                        .add(user)
                                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                            @Override
                                                            public void onSuccess(DocumentReference documentReference) {
                                                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                                                pd.dismiss();
                                                                Intent intent = new Intent(context, StartActivity.class);
                                                                context.startActivity(intent);
                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Log.w(TAG, "Error adding document", e);
                                                                pd.dismiss();
                                                            }
                                                        });
                                                break; // Exit the loop once the user is found and processed
                                            }
                                        }
                                        if (!userFound) {
                                            Log.w(TAG, "User not found with email: " + email);
                                            pd.dismiss();
                                        }
                                    } else {
                                        Log.w(TAG, "Error getting documents.", task.getException());
                                        pd.dismiss();
                                    }
                                }
                            });
                } else {
                    Log.w(TAG, "Error uploading image.", task.getException());
                    pd.dismiss();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error uploading image", e);
                pd.dismiss();
            }
        });
    }


    // Interface to handle search results callback
    public interface searchDone {
        void onSearchDone(LinkedList<FurnitureModel> list);
    }

    // Method to search for furniture by name or type
    public void getSomeFurniture(String Furniture, searchDone callback) {
        LinkedList<FurnitureModel> list = new LinkedList<>();
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int index = task.getResult().size();
                    AtomicInteger index2 = new AtomicInteger(0);
                    for (DocumentSnapshot document : task.getResult()) {
                        db.collection("users").document(document.getId()).collection("Furniture").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    index2.set(index2.get() + 1);
                                    for (DocumentSnapshot document2 : task.getResult()) {
                                        if (document2.getData().get("Name").toString().equals(Furniture)) {
                                            FurnitureModel temp = new FurnitureModel(document2.getData().get("Name").toString(), document2.getData().get("Price").toString(), document2.getData().get("Length").toString(), document2.getData().get("Width").toString(), document2.getData().get("Height").toString(), document2.getData().get("Color").toString(), document2.getData().get("Type").toString(), document2.getData().get("Email").toString(), document2.getData().get("PicPath").toString(),document2.getData().get("Phone").toString().trim());
                                            list.add(temp);
                                        }
                                        if (document2.getData().get("Type").toString().equals(Furniture)) {
                                            FurnitureModel temp = new FurnitureModel(document2.getData().get("Name").toString(), document2.getData().get("Price").toString(), document2.getData().get("Length").toString(), document2.getData().get("Width").toString(), document2.getData().get("Height").toString(), document2.getData().get("Color").toString(), document2.getData().get("Type").toString(), document2.getData().get("Email").toString(), document2.getData().get("PicPath").toString(),document2.getData().get("Phone").toString().trim());
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

    // Interface to handle getting furniture info callback
    public interface Got {
        void onInfoGot(Uri photo);
    }
    // Method to get furniture information and its picture URL
    public void getInfo(String email, String PicPath, Got callback) {
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        if (document.getData().get("Email").equals(email)) {
                            db.collection("users").document(document.getId()).collection("Furniture").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    for (DocumentSnapshot furniture : task.getResult()) {

                                        if (furniture.getData().get("PicPath").equals(PicPath.toString().trim()))
                                        {
                                            StorageReference httpsReference = storage.getReferenceFromUrl("gs://yadlarahit.appspot.com/" + furniture.getData().get("PicPath"));
                                            httpsReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Uri> task)
                                                {
                                                    callback.onInfoGot(task.getResult());
                                                }
                                            });
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }
        });
    }

    public interface FoundUser
    {
        void onFoundUser(boolean flag,String Fname,String Lname, String Password ,String Email,String Phone);
    }

    // Method to find a user ID by email
    public void FindUser(String email,String password, FoundUser callback) {
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot document : task.getResult()) {
                    if (document.getData().get("Email").equals(email) && document.getData().get("Password").equals(password))
                    {
                        callback.onFoundUser(true,document.getData().get("firstName").toString().trim(),document.getData().get("lastName").toString().trim(),document.getData().get("Password").toString().trim(),document.getData().get("Email").toString().trim(),document.getData().get("Phone").toString().trim());
                    }
                }
                callback.onFoundUser(false,null,null,null,null,null);
            }
        });
    }
    public void deleteUser(String email) {

        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> taskUserList) {
                        if (taskUserList.isSuccessful()) {
                            for (QueryDocumentSnapshot document : taskUserList.getResult()) {
                                if (document.getData().get("Email").toString().equals(email)) {
                                    // Delete user from Firestore
                                    db.collection("users").document(document.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User successfully deleted from Firestore.");



                                            } else
                                            {
                                                Log.w(TAG, "Failed to delete user from Firestore: " + task.getException().getMessage());
                                            }
                                        }
                                    });
                                }
                            }
                        } else
                        {
                            Log.w(TAG, "Error getting user documents: " + taskUserList.getException().getMessage());
                        }
                    }
                });
    }
    public interface WhenDone {
        void whenDoneToUpdate();
    }
    public void GetDataToUpdate(String fname,String lname,String email,String phone,FireBaseDataBase.WhenDone callBack){


        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> taskUserList) {

                        if (taskUserList.isSuccessful()) {

                            for (QueryDocumentSnapshot document : taskUserList.getResult()) {
                                if(document.getData().get("Email").toString().equals(email)){


                                    Map<String, Object> user = new HashMap<>();
                                    user.put("firstName", fname);
                                    user.put("lastName", lname);
                                    user.put("Phone", phone);
                                        db.collection("users").document(document.getId()).update(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful())
                                                {
                                                callBack.whenDoneToUpdate();
                                                }
                                            }
                                        });
                                }
                            }

                        } else {
                            Log.w(TAG, "Error getting documents.", taskUserList.getException());
                        }
                    }
                });


    }


}
