package com.example.c56;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

public class ContactTextView extends AppCompatActivity {

    private TextView textView;
    private String noColumn;
    private String nullCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_text_view);

        textView = findViewById(R.id.textView);
        noColumn = getResources().getString(R.string.noColumn);
        nullCursor = getResources().getString(R.string.nullCursor);

        //fetch and gather contacts
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
                //String build to append the output
                StringBuilder contactList = new StringBuilder();
                while(cursor.moveToNext()){
                    //get the strings from the column index and move to next rows
                    String name = cursor.getString(nameIndex);
                    String number = cursor.getString(numberIndex);
                    contactList.append("• " + name + ": " + number + "\n");
                }
                cursor.close();
                //Display contacts
                textView.setText(contactList.toString());
            }else {
                //Null columns
                textView.setText(noColumn);
            }
        }else {
            //Null cursor
            textView.setText(nullCursor);
        }
    }
}