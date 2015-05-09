package com.komarov.cryptographer.crypto.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.komarov.cryptographer.crypto.R;
import com.komarov.cryptographer.crypto.rsa.RSA;

import java.math.BigInteger;

public class MainActivity extends ActionBarActivity {

    final int bits = 1024;
    static RSA rsa = new RSA();
    static RSA partnerRsa = new RSA();

    private EditText ekeyText;
    private EditText nkeyText;

    private EditText partnerEkeyText;
    private EditText partnerNkeyText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ekeyText = (EditText) findViewById(R.id.eKeyField);
        nkeyText = (EditText) findViewById(R.id.nKeyField);
        partnerEkeyText = (EditText) findViewById(R.id.partnerEField);
        partnerNkeyText = (EditText) findViewById(R.id.partnerNField);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void GoToEncryption(View view){
        if (!partnerEkeyText.getText().toString().isEmpty() && !partnerNkeyText.getText().toString().isEmpty()){
            if(EncryptionActivity.isDigit(partnerEkeyText.getText().toString()) && EncryptionActivity.isDigit(partnerNkeyText.getText().toString())) {
                partnerRsa.setKey(new BigInteger(partnerEkeyText.getText().toString()), new BigInteger(partnerNkeyText.getText().toString()), new BigInteger("0"));
                Intent intent = new Intent(this, EncryptionActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Bad key", Toast.LENGTH_LONG).show();
            }

        }
        else {
            Toast toast = Toast.makeText(this, "Set partner key", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void GenerateKey(View view){
        rsa.generateKeys();
        ekeyText.setText(String.valueOf(rsa.getE()));
        nkeyText.setText(String.valueOf(rsa.getN()));
    }

    public void sendSMS(View v)
    {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.putExtra("sms_body", String.valueOf(rsa.getE()) + " " + String.valueOf(rsa.getN()));
            sendIntent.setType("vnd.android-dir/mms-sms");
            startActivity(sendIntent);
    }

    public void onAbout(MenuItem item){
        Toast.makeText(getApplicationContext(), "product by komarov", Toast.LENGTH_SHORT).show();
    }

    public void onHelp(MenuItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Help")
                .setMessage(getString(R.string.HelpText))
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
