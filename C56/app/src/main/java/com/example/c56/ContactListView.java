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

    private ArrayAdapter<String> contactListAdapter;
    private List<String> contactData;
    private final String noColumnCursor = getResources().getString(R.string.noColumnCursor);
    private final String nullCursor = getResources().getString(R.string.nullCursor);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list_view);

        ListView contactListView = findViewById(R.id.contactListView);
        contactData = new ArrayList<>();
        contactListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactData);
        contactListView.setAdapter(contactListAdapter);

        displayContacts();
    }

    private void displayContacts(){
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        if (cursor != null) {
            int displayNameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            if(displayNameIndex != -1 && numberIndex != -1){
                while (cursor.moveToNext()){
                    String name = cursor.getString(displayNameIndex);
                    String number = cursor.getString(numberIndex);
                    contactData.add(name + ": " + number);
                }
                cursor.close();

                contactListAdapter.notifyDataSetChanged();
            }else {
                contactListAdapter.clear();
                contactListAdapter.add(noColumnCursor);
            }
        } else {
            contactListAdapter.clear();
            contactListAdapter.add(nullCursor);
        }
    }
}