package ti.saude.odontec.odontec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btentrar = (Button) findViewById(R.id.btEntrar);

        Intent intent = new Intent(login.this, odontecMain.class);
        startActivity(intent);
    }
}
