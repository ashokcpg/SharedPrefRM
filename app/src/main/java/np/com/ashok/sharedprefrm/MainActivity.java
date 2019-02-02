package np.com.ashok.sharedprefrm;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtusername, txtpassword;


    Button btnlogin, btnreset;
    private CheckBox mCheckBoxRemember;
    private SharedPreferences mPrefs;
    private static final String PREFS_NAME = "PrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        bindWidget();
//        resizeIcons();


        btnlogin = findViewById(R.id.btnLogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        getPreferenceData();



    }

    public void getPreferenceData()
    {
        SharedPreferences sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (sp.contains("pref_name"))
        {
            String u = sp.getString("pref_name","Not Found.");
            txtusername.setText(u.toString());
        }
        if(sp.contains("pref_pass"))
        {
            String p = sp.getString("pref_pass","Not Found");
            txtpassword.setText(p.toString());
        }

        if (sp.contains("pref_check"))
        {
            Boolean b = sp.getBoolean("pref_check",false);
            mCheckBoxRemember.setChecked(b);
        }
    }

    private void bindWidget() {
        txtusername = findViewById(R.id.txtUsername);
        txtpassword = findViewById(R.id.txtPassword);
        btnlogin = findViewById(R.id.btnLogin);
        btnreset = findViewById(R.id.btnReset);
        mCheckBoxRemember = findViewById(R.id.checkRememberMe);
    }


    public void login() {
        String Username = txtusername.getText().toString();
        String Password = txtpassword.getText().toString();


        if (Username.equals("") && Password.equals("")) {
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();

        } else if (Username.equals("root") && Password.equals("toor")) {
            Toast.makeText(this, "Success"+"\n"+"Username: "+Username+"\n"+"Password: "+Password, Toast.LENGTH_SHORT).show();

            if (mCheckBoxRemember.isChecked()) {
                boolean boolIsChecked = mCheckBoxRemember.isChecked();
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString("pref_name", Username);
                editor.putString("pref_pass",Password);
                editor.putBoolean("pref_check",boolIsChecked);
                editor.apply();
                Toast.makeText(getApplicationContext(), "Settings Have Been Saved!!!", Toast.LENGTH_LONG).show();

            } else {
                mPrefs.edit().clear().apply();

            }

        } else {
            Toast.makeText(this, "Invalid Credentials!!!", Toast.LENGTH_SHORT).show();
        }

    }


}
