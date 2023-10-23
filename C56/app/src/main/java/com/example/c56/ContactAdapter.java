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
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int position){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position){
        ContactItem contactItem = contactList.get(position);
        holder.name.setText(contactItem.getName());
        holder.number.setText(contactItem.getNumber());
    }

    @Override
    public int getItemCount(){
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView number;

        public ContactViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
        }
    }
}
