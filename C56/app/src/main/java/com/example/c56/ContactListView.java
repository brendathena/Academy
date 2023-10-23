package com.example.c56;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactListView extends AppCompatActivity {
    private ArrayAdapter<String> listAdapter;
    private List<String> contactData;

    private String noColumn;
    private String nullCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list_view);

        noColumn = getResources().getString(R.string.noColumn);
        nullCursor = getResources().getString(R.string.nullCursor);

        ListView listView = findViewById(R.id.listView);
        contactData = new ArrayList<>();
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactData);
        listView.setAdapter(listAdapter);

        displayContacts();
    }

    private void displayContacts(){
        //Define the name and number columns you wish to gather
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        //Query the contacts database
        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        //Cursor null protection
        if (cursor != null){
            //Get column indexes
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            //Columns null protection
            if (nameIndex != -1 && numberIndex != -1){
                while(cursor.moveToNext()){
                    //get the strings from the column index and move to next rows
                    String name = cursor.getString(nameIndex);
                    String number = cursor.getString(numberIndex);
                    contactData.add(name + ": " + number); // Add contact information to the list
                    //•
                }
                cursor.close();

                listAdapter.notifyDataSetChanged(); // Update the list view with the new data
            } else {
                // Null columns, clear the list and display a message
                listAdapter.clear();
                listAdapter.add(noColumn);
            }
        } else {
            // Null cursor, clear the list and display a message
            listAdapter.clear();
            listAdapter.add(nullCursor);
        }
    }
}