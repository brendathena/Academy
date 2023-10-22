package com.example.c56;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    private List<ContactItem> contactList;

    public ContactAdapter(List<ContactItem> contactList){
        this.contactList = contactList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position){
        ContactItem contactItem = contactList.get(position);
        holder.contactNameTextView.setText(contactItem.getName());
        holder.contactPhoneNumberTextView.setText(contactItem.getPhoneNumber());
    }

    @Override
    public int getItemCount(){
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{

        public TextView contactNameTextView;
        public TextView contactPhoneNumberTextView;

        public ContactViewHolder(View itemView){
            super(itemView);

            contactNameTextView = itemView.findViewById(R.id.contactNameTextView);
            contactPhoneNumberTextView = itemView.findViewById(R.id.contactPhoneNumberTextView);
        }
    }
}
