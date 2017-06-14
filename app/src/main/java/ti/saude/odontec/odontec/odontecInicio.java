package ti.saude.odontec.odontec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class odontecInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odontec_inicio);

        Button btAnestesico = (Button) findViewById(R.id.btAnest);
        Button btFarmacologia = (Button) findViewById(R.id.btFarmaco);
        Button btInformacao = (Button) findViewById(R.id.btInfor);
    }
}
