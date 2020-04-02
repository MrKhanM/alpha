package com.example.customer_detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class addCustomer extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editAddress,editPhone,editRole,editContact;
    Button btnAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText);
        editAddress = (EditText)findViewById(R.id.editText2);
        editPhone = (EditText)findViewById(R.id.phoneTxt);
        editRole = (EditText)findViewById(R.id.roleEdit);
        editContact = (EditText)findViewById(R.id.contactPEdit);
        btnAddData = (Button) findViewById(R.id.button);

        AddData();

    }
    public void AddData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editName.getText().toString().equals("") || editAddress.getText().toString().equals("")
                        || editPhone.getText().toString().equals("") || editRole.getText().toString().equals("") || editContact.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter all the Values", Toast.LENGTH_SHORT).show();
                } else {

                    boolean isInserted = myDb.insertData(editName.getText().toString(), editAddress.getText().toString(), editPhone.getText().toString(), editRole.getText().toString(), editContact.getText().toString());
                    if (isInserted == true) {
                        Toast.makeText(getApplicationContext(), "DATA Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "DATA is not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
