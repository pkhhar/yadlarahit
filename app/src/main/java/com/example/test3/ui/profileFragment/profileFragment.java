package com.example.test3.ui.profileFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test3.DB.FireBaseDataBase;
import com.example.test3.DB.MyDatabaseHelper;
import com.example.test3.R;
import com.example.test3.ui.signUp.signupActivity;
import com.example.test3.ui.start.StartActivity;

import java.util.HashMap;
import java.util.Map;

public class profileFragment extends Fragment implements View.OnClickListener {

    // Declaring variables for views and other objects
    private View v;
    private  profileFragModel profileFragModel;
    private MyDatabaseHelper myDatabaseHelper;
    private AlertDialog dialog;
    private SharedPreferences sharedPreferences;
        private TextView fnameoftheAccount,lnameoftheAccount,emailoftheAccount,phoneoftheAccount,updateUser,removeUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // This method is called to create and return the view hierarchy associated with the fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Initialize the database helper
        myDatabaseHelper = new MyDatabaseHelper(requireActivity());

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize UI elements
        fnameoftheAccount = v.findViewById(R.id.textViewFname);
        lnameoftheAccount = v.findViewById(R.id.textViewLname);
        emailoftheAccount = v.findViewById(R.id.textViewEmail);
        phoneoftheAccount = v.findViewById(R.id.textViewPhone);
        updateUser = v.findViewById(R.id.updateUser);
        removeUser = v.findViewById(R.id.removeUser);
        profileFragModel = new profileFragModel(getContext());
//         Retrieve user details from shared preferences and set to TextViews
        sharedPreferences = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        fnameoftheAccount.setText(sharedPreferences.getString("fname", ""));
        lnameoftheAccount.setText(sharedPreferences.getString("lname", ""));
        emailoftheAccount.setText(sharedPreferences.getString("email", ""));
        phoneoftheAccount.setText(sharedPreferences.getString("phone", ""));
        // Set onClick listeners for buttons
        updateUser.setOnClickListener(this);
        removeUser.setOnClickListener(this);

        return v;
    }





    // Handle button clicks
    @Override
    public void onClick(View view)
    {
        if (removeUser == view)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            builder.setTitle("Confirm Removal");
            builder.setMessage("Are you sure you want to remove this user?");
            builder.setPositiveButton("Yes", (dialogInterface, i) ->
            {
                // Call method to remove the user
                profileFragModel.removeUser(sharedPreferences.getString("email",""));
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(requireContext(),signupActivity.class);
                startActivity(intent);
            });
            builder.setNegativeButton("No", (dialogInterface, i) ->
            {
                dialogInterface.dismiss();
            });
            builder.show();
        }
        else if (updateUser == view)
        {
            Dialog dialog = new Dialog(getActivity());
            dialog.setCancelable(true);

            dialog.setContentView(R.layout.dialog_update);

            EditText etName = dialog.findViewById(R.id.editTextFirstName);
            EditText etLname = dialog.findViewById(R.id.editTextLastName);
            EditText etPhone = dialog.findViewById(R.id.editTextPhone);
            Button btnOnSend = dialog.findViewById(R.id.btnsend);

            btnOnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {

                    String currentName = etName.getText().toString().trim();
                    String currentLname = etLname.getText().toString().trim();
                    String currentPhone =  etPhone.getText().toString().trim();

                    if(currentPhone.length() != 10)
                    {
                        Toast.makeText(requireActivity(), "phone must be 10 digits", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for (int i = 0; i <currentPhone.length() ; i++)
                    {
                        if(!(currentPhone.toString().trim().charAt(i) >='0'  && currentPhone.toString().trim().charAt(i) <= '9'))
                        {
                            Toast.makeText(requireActivity(), "phone must be only digits", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    profileFragModel.GetDataToUpdate(currentName, currentLname, emailoftheAccount.getText().toString(),currentPhone, new FireBaseDataBase.WhenDone() {
                        @Override
                        public void whenDoneToUpdate() {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("fname",currentName);
                            editor.putString("lname", currentLname);
                            editor.putString("phone",currentPhone);
                            editor.apply();
                            Intent intent = new Intent(requireActivity(),StartActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            });

            dialog.dismiss();
            dialog.show();
        }

        }

    }



