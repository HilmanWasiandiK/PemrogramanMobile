package com.example.provider;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.loader.content.CursorLoader;

public class MainActivity extends ListActivity {
    final private int REQUEST_READ_CONTACTS = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_READ_CONTACTS);
        } else{
            ListContacts();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode
            , String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_CONTACTS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ListContacts();
                } else {
                    Toast.makeText(MainActivity.this
                            , "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode
                        , permissions, grantResults);
        }
    }
    protected void ListContacts(){
        Uri allContacts = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[]
                {       ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.HAS_PHONE_NUMBER};
        Cursor c;
        CursorLoader cursorLoader = new CursorLoader(
                this,
                allContacts,
                projection,
                ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?",
                new String[] {"%JTK"},
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        c = cursorLoader.loadInBackground();
        String[] columns = new String[]{
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts._ID};
        int[] views = new int[]{R.id.contactName, R.id.contactID};
        SimpleCursorAdapter adapter;
        adapter = new SimpleCursorAdapter(
                this, R.layout.activity_main, c, columns, views,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        this.setListAdapter(adapter);
    }

    @SuppressLint("Range")
    private void PrintContacts(Cursor c)
    {
        if (c.moveToFirst()) {
            do{
                String contactID = c.getString(c.getColumnIndex(
                        ContactsContract.Contacts._ID));
                String contactDisplayName =
                        c.getString(c.getColumnIndex(
                                ContactsContract.Contacts.DISPLAY_NAME));
                Log.v("Content Providers", contactID + ", " +
                        contactDisplayName);
                //---get phone number---

                Cursor phoneCursor =
                        getContentResolver().query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " +
                                        contactID, null, null);
                while (phoneCursor.moveToNext()) {
                    Log.v("Content Providers",
                            phoneCursor.getString(
                                    phoneCursor.getColumnIndex(
                                            ContactsContract.CommonDataKinds.Phone.NUMBER)));
                }
                phoneCursor.close();
            } while (c.moveToNext());
        }
    }
}