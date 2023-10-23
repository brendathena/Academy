package com.example.c56;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ContactRecyclerView extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ContactAdapter contactAdapter;
    private List<ContactItem> contactList;

    String noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_recycler_view);

        noData = getResources().getString(R.string.noData);
        recyclerView = findViewById(R.id.recyclerView);

        contactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(contactList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return true;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.e("tag","on touch event");
                if (e.getAction() == MotionEvent.ACTION_UP) {
                    View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (childView != null) {
                        int position = recyclerView.getChildAdapterPosition(childView);
                        ContactItem selectedContact = contactList.get(position);

                        String selectedContactJson = selectedContact.toJsonString();

                        Intent intent = new Intent();
                        intent.putExtra("selectedContact", selectedContactJson);
                        setResult(RESULT_OK, intent);

                        finish();
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        displayContacts();
    }

    private void displayContacts() {
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
        if (cursor != null) {
            //Get column indexes
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            //Columns null protection
            if (nameIndex != -1 && numberIndex != -1) {
                while (cursor.moveToNext()) {
                    //get the strings from the column index and move to next rows
                    String name = cursor.getString(nameIndex);
                    String number = cursor.getString(numberIndex);
                    ContactItem contactItem = new ContactItem(name, number);
                    contactList.add(contactItem);
                }
                cursor.close();
                contactAdapter.notifyDataSetChanged(); // Update the list view with the new data



            } else {
                // Null columns, clear the list and display a message
                contactList.clear();
                contactList.add(new ContactItem(noData, ""));
                contactAdapter.notifyDataSetChanged();
            }
        } else{
            // Null cursor, clear the list and display a message
            contactList.clear();
            contactList.add(new ContactItem(noData, noData));
            contactAdapter.notifyDataSetChanged();
        }
    }
}
