package com.example.c56;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
public class ContactRecyclerView extends AppCompatActivity {

    private RecyclerView contactRecyclerView;
    private ContactAdapter contactAdapter;
    private List<ContactItem> contactList;

    private String noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_recycler_view);

        noData = getResources().getString(R.string.noData);

        contactRecyclerView = findViewById(R.id.contactRecyclerView);
        contactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(contactList);

        contactRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactRecyclerView.setAdapter(contactAdapter);

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

        if (cursor != null){
            int displayNameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            if (displayNameIndex != -1 && numberIndex != -1){
                while (cursor.moveToNext()){
                    String name = cursor.getString(displayNameIndex);
                    String number = cursor.getString(numberIndex);
                    ContactItem contactItem = new ContactItem(name, number);
                    contactList.add(contactItem);
                }
                cursor.close();
                contactAdapter.notifyDataSetChanged();
            } else {
                contactList.clear();
                contactList.add(new ContactItem(noData,""));
                contactAdapter.notifyDataSetChanged();
            }
        } else {
            contactList.clear();
            contactList.add(new ContactItem(noData, noData));
            contactAdapter.notifyDataSetChanged();
        }
    }
}