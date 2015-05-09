package com.komarov.cryptographer.crypto.activity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.komarov.cryptographer.crypto.R;

import java.math.BigInteger;

public class EncryptionActivity extends ActionBarActivity {


    boolean route = true;
    private Button btn;

    private TextView textLab;
    private TextView crypLab;

    private EditText inputText;
    private EditText outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);
        btn  = (Button) findViewById(R.id.rout_btn);
        textLab = (TextView) findViewById(R.id.lab1text);
        crypLab = (TextView) findViewById(R.id.lab2text);
        inputText = (EditText) findViewById(R.id.inputTextField);
        outputText = (EditText) findViewById(R.id.outputTextField);
    }



    public static boolean isDigit(String q ){
        for (int i = 0; i < q.length(); i++){
            if(!Character.isDigit(q.charAt(i)))
                return false;
        }
        return true;
    }

    public void GoToGenerate(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Are you sure?")
                .setMessage("Settings will be lost")
                .setCancelable(true)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
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

    public void SelrctRoute(View view){
        if(route) {
            btn.setText("Decryption");
            textLab.setText("Crypto-Text");
            crypLab.setText("Text");
            route = false;
        }
        else {
            btn.setText("Encryption");
            textLab.setText("Text");
            crypLab.setText("Crypto-Text");
            route = true;
        }

    }

    public void Execute(View view){
        if (MainActivity.rsa.getE() != null  && MainActivity.rsa.getN()!= null && !inputText.getText().toString().isEmpty()) {
            if (route) {
                String text = "j";
                text += inputText.getText();
                BigInteger plaintext = new BigInteger(text.getBytes());

                BigInteger ciphertext = MainActivity.partnerRsa.encrypt(plaintext);
                outputText.setText(ciphertext.toString());
            } else {
                if ( isDigit(inputText.getText().toString()) && MainActivity.rsa.getD() != null) {
                    String text = inputText.getText().toString();
                    BigInteger plaintext = new BigInteger(text);
                    plaintext = MainActivity.rsa.decrypt(plaintext);
                    String text2 = new String(plaintext.toByteArray());
                    text2 = text2.substring(1, text2.length());
                    outputText.setText(text2);
                }
                else {
                    Toast toast = Toast.makeText(this, "Bad input parameters", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }
        else {
            Toast toast = Toast.makeText(this, "Bad input parameters", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void ClearText(View view){
        inputText.setText("");
        outputText.setText("");
    }

    public void CopyText(View view){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("",outputText.getText().toString());
        clipboard.setPrimaryClip(clip);
    }
}
