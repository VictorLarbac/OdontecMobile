package ti.saude.odontec.odontec;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;


public class login extends AppCompatActivity implements View.OnClickListener {

    static FirebaseAuth mAuth;
    private EditText edtLogin, edtSenha;
    private ImageView btentrar;
    private TextView tvCadastro;
    private ImageView  btGmail;
    private LoginButton btFace;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();


        btentrar = (ImageView) findViewById(R.id.btEntrar);
        tvCadastro = (TextView) findViewById(R.id.tvCadastro);
        edtLogin = (EditText) findViewById(R.id.editText);
        edtSenha = (EditText) findViewById(R.id.editText2);
        btGmail = (ImageView) findViewById(R.id.btGmail);
        btFace = (LoginButton) findViewById(R.id.btFace);

        btentrar.setOnClickListener(this);
        tvCadastro.setOnClickListener(this);
        btFace.setOnClickListener(this);
        btGmail.setOnClickListener(this);


        btFace.setReadPermissions(Arrays.asList("email", "public_profile"));
        btFace.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacacebookAcessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };


     }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuth.addAuthStateListener(mAuthListener);
        updateUI(currentUser);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void goMainScreen(){
        Intent intent = new Intent(this, odontecInicio.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void handleFacacebookAcessToken(AccessToken accessToken) {
        Log.d(TAG, "handleFacebookAccessToken:" + accessToken);

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());


                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }
    public void onClick(View v) {
        if (v == tvCadastro) {

                Intent it = new Intent(this, odontecCadastro.class);
                startActivity(it);


        } else if (v == btentrar) {
            if(edtLogin.getText().length() == 0 || edtSenha.getText().length() == 0) {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_LONG).show();
            }else {
                mAuth.signInWithEmailAndPassword(edtLogin.getText().toString(), edtSenha.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(login.this, "Login efetuado com sucesso!",
                                            Toast.LENGTH_SHORT).show();
                                    Log.d("teste", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("teste", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(login.this, "Autenticação falhou. Tente novamente.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        }

    }

    private void updateUI(FirebaseUser usuarioAtual) {
        if (usuarioAtual != null)
            startActivityForResult(new Intent(this, odontecInicio.class), 0);
    }



}
