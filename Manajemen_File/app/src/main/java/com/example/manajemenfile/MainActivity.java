package com.example.manajemenfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //Membuat deklrasi object
    EditText editText;
    private int STORAGE_PERMISSION_CODE = 23;
    
    //method ini pertama kali dipanggil ketika activity pertama dimulai
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //membuat fungsi untuk akses id layout dari class
        editText = (EditText) findViewById(R.id.editText2);
    }
    //membuat fungsi public dengan nama "next" bertipe data view
    //method ini nantinya yang akan dipanggil untuk menampilkan layout "activity_main"
    public void next(View view){
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }

    //method ini untuk menyimpan data savePublic
    public void savePublic(View view){
        //permission to access external storage
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        String info = editText.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS); //deklarasi Folder Name
        File myFile = new File(folder, "mydata1.txt"); //deklarasi File name
        writeData(myFile, info); //untuk menulis data
        editText.setText(""); //mengatur tulisan
    }

    //method ini untuk menyimpan data savePrivate
    public void savePrivate(View view){
        String info = editText.getText().toString(); //deklarasi file bertipe data text
        File folder = getExternalFilesDir("Aulia"); //deklarasi Folder Name
        File myFile = new File(folder, "mydata2.txt"); //deklarasi File name
        writeData(myFile, info); //untuk menulis data
        editText.setText(""); //mengatur tulisan
    }

    //method ini untuk mendeklarasi
    private void writeData(File myFile, String data){
        FileOutputStream fileOutputStream = null;
        try {
            System.out.println("TES");
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
            Toast.makeText(this, "Done" + myFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}