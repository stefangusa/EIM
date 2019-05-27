package ro.pub.cs.systems.eim.lab03.phonedialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PhoneDialerActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, star, hash;
    ImageButton back, hang, call;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        back = findViewById(R.id.back);
        star = findViewById(R.id.star);
        hash = findViewById(R.id.hash);
        call = findViewById(R.id.call);
        hang = findViewById(R.id.hang);

        input = findViewById(R.id.number);
    }

    public void onButtonClick(Button button, EditText input, String no) {
        String cache = input.getText().toString();
        input.setText(cache + no);
    }

    public void delete(View v) {
        String cache = input.getText().toString();
        input.setText(cache.substring(0, cache.length() - 1));
    }

    public void call(View v) {
        if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    PhoneDialerActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + input.getText().toString()));
            startActivity(intent);
        }
    }

    public void hang(View v) {
        finish();
    }

    public void one(View v) {
        onButtonClick(one, input, "1");
    }

    public void two(View v) {
        onButtonClick(two, input, "2");
    }

    public void three(View v) {
        onButtonClick(three, input, "3");
    }
    public void four(View v) {
        onButtonClick(four, input, "4");
    }
    public void five(View v) {
        onButtonClick(five, input, "5");
    }
    public void six(View v) {
        onButtonClick(six, input, "6");
    }
    public void seven(View v) {
        onButtonClick(seven, input, "7");
    }
    public void eight(View v) {
        onButtonClick(eight, input, "8");
    }
    public void nine(View v) {
        onButtonClick(nine, input, "9");
    }
    public void zero(View v) {
        onButtonClick(zero, input, "0");
    }
    public void hash(View v) {
        onButtonClick(hash, input, "#");
    }
    public void star(View v) {
        onButtonClick(star, input, "*");
    }
}
