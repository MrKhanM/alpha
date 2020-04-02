package com.example.customer_detail;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class update_customer extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName,editAddress,editPhone,editRole,editContact,idEdit;
    Button btnUpdate;
    Intent intent;
    int intentValue;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);

        idEdit= (EditText)findViewById(R.id.editText3);
        editName = (EditText)findViewById(R.id.editText);
        editAddress = (EditText)findViewById(R.id.editText2);
        editPhone = (EditText)findViewById(R.id.phoneTxt);
        editRole = (EditText)findViewById(R.id.roleEdit);
        editContact = (EditText)findViewById(R.id.contactPEdit);
        btnUpdate = (Button) findViewById(R.id.button);


         updateData();

    }

    public void updateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(idEdit.getText().toString().equals("") || editName.getText().toString().equals("") || editAddress.getText().toString().equals("")
                        || editPhone.getText().toString().equals("") || editRole.getText().toString().equals("") || editContact.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter all the Values",Toast.LENGTH_SHORT).show();
                }else{

                boolean isUpdate = myDb.updateData(idEdit.getText().toString(),editName.getText().toString(),editAddress.getText().toString(),
                        editRole.getText().toString(),editContact.getText().toString(),editContact.getText().toString());

                if (isUpdate == true){
                    Toast.makeText(getApplicationContext(),"DATA UPDATED",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "DATA NOT UPDATED", Toast.LENGTH_SHORT).show();

                }
                }
            }

        });
    }



}
