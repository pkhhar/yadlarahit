package com.example.test3.ui.addItem;
import android.Manifest;
import android.app.ActionBar;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.example.test3.DB.FireBaseDataBase;
import com.example.test3.R;
import com.example.test3.databinding.ActivityAddItemBinding;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class addItemActivity extends AppCompatActivity  implements View.OnClickListener  {

    private EditText name,price,length,width,height,color;
    private Spinner spinnerType;
    private Button btnAddFurniture;
    private  static  final  int CAMERA_PERMISSION_CODE =1;
    ActivityAddItemBinding addItemBinding;

    ActivityResultLauncher<Uri> takePictureLauncher;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addItemBinding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(addItemBinding.getRoot());
        imageUri = createUri();
        registerPictureLuncher();
        name = findViewById(R.id.namePutProduct);
        price = findViewById(R.id.pricePutProduct);
        length = findViewById(R.id.lengthPutProduct);
        width = findViewById(R.id.widthPutProduct);
        height = findViewById(R.id.heightPutProduct);
        color = findViewById(R.id.colorPutProduct);
        spinnerType = findViewById(R.id.spinnerPutProduct);


        List<String> lst = new LinkedList<>();
        lst.add("בחר קטגוריה");
        lst.add("חדר שינה");
        lst.add("שירותים");
        lst.add("מטבח");
        lst.add("סלון");
        lst.add("חצר");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,lst);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        btnAddFurniture = findViewById(R.id.addFurnutureButton);
        addItemBinding.btnTakePicture.setOnClickListener(view -> {
            checkCameraPermissionAndOpenCamera();
        });
    }
    private Uri createUri() {
        File imageFile = new File(getApplicationContext().getFilesDir(), "my_photo.jpg");
        return FileProvider.getUriForFile(
                getApplicationContext(),
                "com.example.test3.fileProvider",
                imageFile
        );

    }
    private  void registerPictureLuncher() {
        takePictureLauncher= registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        try {
                            if(result)
                            {
                                addItemBinding.uploadPicture.setImageURI(null);
                                addItemBinding.uploadPicture.setImageURI(imageUri);
                            }
                        }catch (Exception exception)
                        {
                                exception.getStackTrace();

                        }
                    }
                }
        );
    }

    private void checkCameraPermissionAndOpenCamera()
    {
        if(ActivityCompat.checkSelfPermission(addItemActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(addItemActivity.this,
                    new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);

        }else {

        takePictureLauncher.launch(imageUri);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_CODE)
        {
            if(grantResults.length> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                takePictureLauncher.launch(imageUri);


            }else {
                Toast.makeText(this, "camera permission denied , please allow permission  to take a picture.", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onClick(View v)
    {
        if(btnAddFurniture == v)
        {
            SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
    String currentEmail =sharedPreferences.getString("email","");
            FireBaseDataBase fireBaseDataBase = new FireBaseDataBase();
            fireBaseDataBase.AddFurniture(currentEmail,name.getText().toString().trim(),price.getText().toString().trim(),length.getText().toString().trim(),width.getText().toString().trim(),height.getText().toString().trim(),color.getText().toString().trim(),spinnerType.getSelectedItem().toString().trim());

        }


    }
}
