package com.example.shekoofeh.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    static final int resuest_get_content= 1;
    static final int contact_request= 1;
    ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        imageview = (ImageView) findViewById(R.id.imageView);

        if (extras != null) {
            imageview.setImageURI((Uri) getIntent().getExtras().get(Intent.EXTRA_STREAM));

        }
    }
    public void doGo (View v){
        Intent i = new Intent ( MainActivity.this, BuiltInIntentsActivity.class);
        startActivity ( i);
    }
    public void getImage (View v){
           Intent intent=  new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select an image"), resuest_get_content);
    }
    public void sendImage (View v){
        Intent intent=  new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        startActivityForResult(intent, contact_request);
    }
    private void pickContact() {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(pickContactIntent, contact_request);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( resultCode== RESULT_OK && requestCode ==resuest_get_content){
            imageview.setImageURI((Uri) data.getData());
        }
        else if ( resultCode== RESULT_OK && requestCode == contact_request){
           // startActivity  (new Intent(Intent.ACTION_VIEW , );
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
           // startActivity(new Intent (Intent.ACTION_VIEW, data));
       */ }

    }
}
