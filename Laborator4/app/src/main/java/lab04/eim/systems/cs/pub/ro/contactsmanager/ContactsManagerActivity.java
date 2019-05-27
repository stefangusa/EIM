package lab04.eim.systems.cs.pub.ro.contactsmanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {

    Button details, save, cancel;
    EditText name, phone, email, address, job, website, company, im;
    LinearLayout details_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        details = findViewById(R.id.details);
        save = findViewById(R.id.save);
        cancel = findViewById(R.id.cancel);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        job = findViewById(R.id.job_title);
        website = findViewById(R.id.website);
        company = findViewById(R.id.company);
        im = findViewById(R.id.im);

        Intent intent = getIntent();
        if (intent != null) {
            String phone = intent.getStringExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY");
            if (phone != null) {
                this.phone.setText(phone);
            } else {
                Toast.makeText(this, getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
            }
        }

        details_container = findViewById(R.id.details_container);

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (details.getText().toString().compareTo("Show additional details") == 0) {
                    details.setText("Hide additional details");
                    details_container.setVisibility(View.VISIBLE);
                }
                else {
                    details.setText("Show additional details");
                    details_container.setVisibility(View.GONE);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                if (name.getText() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
                }
                if (phone.getText() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText().toString());
                }
                if (email.getText() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText().toString());
                }
                if (address.getText() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address.getText().toString());
                }
                if (job.getText() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, job.getText().toString());
                }
                if (company.getText() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company.getText().toString());
                }
                ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                if (website.getText() != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website.getText().toString());
                    contactData.add(websiteRow);
                }
                if (im.getText() != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im.getText().toString());
                    contactData.add(imRow);
                }
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                startActivity(intent);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED, new Intent());
                finish();
            }
        });
    }


}
