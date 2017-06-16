package ti.saude.odontec.odontec;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class odontecCadastro extends AppCompatActivity implements View.OnClickListener {
    public usuario user;
    private DatabaseReference databaseReference
            = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usuarReference
            = databaseReference.child("user");
    static FirebaseAuth mAuth;

    private Button btSalvar;
    private EditText edtNome, edtDataNasc, edtEmail, edtSenha, edtEstado, edtCidade;
    private String sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odontec_cadastro);

        btSalvar = (Button) findViewById(R.id.btSalvar);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);


        btSalvar.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if (view == btSalvar) {
            login.mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtSenha.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                Toast.makeText(odontecCadastro.this, "Cadastrado com sucesso!!!",
                                        Toast.LENGTH_LONG).show();
                                Log.d("teste", "createUserWithEmail:success ");
                                FirebaseUser user = odontecCadastro.mAuth.getCurrentUser();
                                salvar(user);


                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(odontecCadastro.this, "Erro.",
                                        Toast.LENGTH_LONG).show();
                                Log.w("teste", "createUserWithEmail:failure", task.getException());

                                //updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }

        public void salvar(FirebaseUser user) {

            usuarReference.child("usuario");
        }

    }










