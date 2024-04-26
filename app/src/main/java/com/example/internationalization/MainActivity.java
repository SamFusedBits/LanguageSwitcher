package com.example.internationalization;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView change;
    EditText email,password;
    AlertDialog.Builder alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        change = findViewById(R.id.changeLang);
        alertDialog = new AlertDialog.Builder(this);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] languages = {"English","हिंदी", "मराठी", "ગુજરાતી"};

                alertDialog.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int langId) {
                        if(langId==1){
                            setLocale("en");
                            recreate();
                        } else if (langId==2) {
                            setLocale("hi");
                            recreate();
                        } else if (langId==3) {
                            setLocale("mr");
                            recreate();
                        } else if (langId==4) {
                            setLocale("gu");
                            recreate();
                        }
                    }
                });

                alertDialog.create();
                alertDialog.show();

            }
        });

    }
    private void setLocale(String changeLang){
        Locale locale = new Locale(changeLang);
        Configuration config = new Configuration();

        config.setLocale(locale);

        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("myLang",changeLang);
        editor.apply();
    }

    private void loadLocale(){
        SharedPreferences sharedPreferences = getSharedPreferences("Settings",MODE_PRIVATE);
        String lang = sharedPreferences.getString("myLang", "");
        setLocale(lang);
    }
}