package com.example.shekoofeh.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by SHEKOOFEH on 10/19/2015.
 */
public class BuiltInIntentsActivity extends Activity {
    static final int reqCode=1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_built_in_intents);

    }
    public void chooseContacts ( View v){
        Intent intent = new Intent (Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        startActivityForResult(intent, reqCode);
    }
    public void showMap ( View v){
        Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse("geo:41.8880144, -87.6340133"));
        startActivity(intent);

    }
    public void browseWeb( View v){
        Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse("https://youtube.com"));
        startActivity(intent);
    }
    public void makeCall ( View v){
        Intent intent = new Intent (Intent.ACTION_DIAL,  Uri.parse("tel:+6309290727"));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( resultCode== RESULT_OK  &&  requestCode==reqCode)
        {
            Toast.makeText(this,data.getData().toString(),Toast.LENGTH_LONG).show();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(data.getData().toString())));
            /*
            Uri contactUri = data.getData();
            // We only need the NUMBER column, because there will be only one row in the result
            String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

            // Perform the query on the contact to get the NUMBER column
            // We don't need a selection or sort order (there's only one result for the given URI)
            // CAUTION: The query() method should be called from a separate thread to avoid blocking
            // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
            // Consider using CursorLoader to perform the query.
            Cursor cursor = getContentResolver()
                    .query(contactUri, projection, null, null, null);
            cursor.moveToFirst();

            // Retrieve the phone number from the NUMBER column
            int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String number = cursor.getString(column);

            // Do something with the phone number...
            // startActivity(new Intent (Intent.ACTION_VIEW, data));*/
        }

    }
}
