package com.deals.dealapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.deals.dealapp.MainActivity;
import com.deals.dealapp.R;
import com.deals.dealapp.Service.CheckInternetConnection;
import com.deals.dealapp.util;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.irozon.sneaker.Sneaker;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

public class LoginAcitivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;
    EditText inputEmail_et;
    AppCompatButton buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivity);
        util.blackiteamstatusbar(this, R.color.light_background);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        initview();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    private void initview() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //updateUI(account);

        GoogleSignInButton signInButton = findViewById(R.id.sign_in_button);
        buttonLogin=findViewById(R.id.buttonLogin);
        inputEmail_et=findViewById(R.id.inputEmail);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   progressDialog.show();
             //   progressDialog.hide();
                Intent in = new Intent(LoginAcitivity.this, MainActivity.class);

                startActivity(in);
                String email=inputEmail_et.getText().toString().trim();
                if (email.isEmpty())
                {
                    Sneaker.with(LoginAcitivity.this)
                            .setTitle("Enter  Mobile Number/Email")
                            .setMessage("")
                            .sneakError();

                }
            }
        });

        // signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            String personName = "", personGivenName = "", personFamilyName = "", personEmail = "", personId = "";
            Uri personPhoto = null;
            if (acct != null) {
                personName = acct.getDisplayName();
                personGivenName = acct.getGivenName();
                personFamilyName = acct.getFamilyName();
                personEmail = acct.getEmail();
                personId = acct.getId();
                personPhoto = acct.getPhotoUrl();

                Toast.makeText(this, personPhoto + "", Toast.LENGTH_SHORT).show();
            }
            Intent in = new Intent(LoginAcitivity.this, MainActivity.class);
            in.putExtra("personName", personName);
            in.putExtra("personGivenName", personGivenName);
            in.putExtra("personFamilyName", personFamilyName);
            in.putExtra("personEmail", personEmail);
            in.putExtra("personId", personId);
            in.putExtra("personPhoto", personPhoto);
            startActivity(in);

            // Signed in successfully, show authenticated UI.
            //updateUI(account);

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //  Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Log.d("signInResult:=", e.toString());

            // updateUI(null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //check Internet Connection

        new CheckInternetConnection(this).checkConnection();
    }
}