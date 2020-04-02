package com.example.customer_detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    ArrayList listItem;
    ArrayAdapter adapter;
    static ListView userList;
    private long backPressedTime;
    static  int itemToDelete;
    static Intent i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listItem = new ArrayList<>();
        myDb = new DatabaseHelper(this);
        userList =findViewById(R.id.userList);

            viewData();
                                                                                                        //item click listener.
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                i1 = new Intent(MainActivity.this,update_customer.class);
                i1.putExtra("customerId",position);
               startActivity(i1);
            }
        });
                                                                                                    //long click listener to delete.
        userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {

                itemToDelete = position;

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                           listItem.remove(itemToDelete);
//                          adapter.notifyDataSetChanged();

                                Integer dataDeleted = myDb.deleteData((int) id);

                                if (dataDeleted > 1){
                                    Toast.makeText(getApplicationContext(),"Customer deleted from records",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getApplicationContext(),"Data not deleted",Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                .setNegativeButton("No",null)
                .show();
                return true;
            }
        });
    }


    private void viewData(){                                                                         //View Data into listView
        Cursor cursor = myDb.viewData();
        if (cursor.getCount() == 0){
            Toast.makeText(this,"No data to show",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
            userList.setAdapter(adapter);
        }
    }


    @Override
    public void onBackPressed(){                                                                    //Back Press to exit to get confirmation message.
        if (backPressedTime + 2000 > System.currentTimeMillis()){
        super.onBackPressed();
        return;
        }else {
            Toast.makeText(getApplicationContext(),"Press back again to exit",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

                                                                                                    //Menu Below
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addCustomer:
                Intent intent = new Intent(getApplicationContext(),addCustomer.class);
                startActivity(intent);
                return true;
            case R.id.updateCustomer:
                Intent intent1 = new Intent(getApplicationContext(),update_customer.class);
                startActivity(intent1);
                return true;
            case R.id.about:
                Intent intent2 = new Intent(getApplicationContext(),about.class);
                startActivity(intent2);
                return true;
                default:
                   return super.onOptionsItemSelected(item);
        }
    }




}
