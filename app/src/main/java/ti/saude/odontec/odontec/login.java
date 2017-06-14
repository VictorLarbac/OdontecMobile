package ti.saude.odontec.odontec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btentrar = (Button) findViewById(R.id.btEntrar);


        btentrar.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent intent = new Intent(login.this, odontecInicio.class);
                startActivity(intent);
            }
        });

    }


}
