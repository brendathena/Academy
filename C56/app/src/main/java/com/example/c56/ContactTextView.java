package com.example.c56;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.database.Cursor;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ContactTextView extends AppCompatActivity {

    private TextView contactListTextView;
    private final String noColumnCursor = getResources().getString(R.string.noColumnCursor);
    private final String nullCursor = getResources().getString(R.string.nullCursor);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_text_view);

        contactListTextView = findViewById(R.id.contactListTextView);

        // Fetch and display contacts
        displayContacts();
    }

    @SuppressLint("SetTextI18n")
    private void displayContacts() {
        // Define the columns you want to retrieve from the Contacts database
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        // Query the Contacts database
        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        // Check if the cursor is not null
        if (cursor != null) {
            // Check if the columns exist in the cursor's result set
            int displayNameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            if (displayNameIndex != -1 && numberIndex != -1) {
                StringBuilder contactList = new StringBuilder();
                while (cursor.moveToNext()) {
                    String name = cursor.getString(displayNameIndex);
                    String number = cursor.getString(numberIndex);
                    contactList.append(name).append(": ").append(number).append("\n");
                }
                cursor.close();

                // Display the contacts in the TextView
                contactListTextView.setText(contactList.toString());
            } else {
                // Handle the case when columns don't exist
                contactListTextView.setText(noColumnCursor);
            }
        } else {
            // Handle the case when the cursor is null
            contactListTextView.setText(nullCursor);
        }
    }
}