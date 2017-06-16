package ti.saude.odontec.odontec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class tipoPaciente extends AppCompatActivity {

    TextView tvPaciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_paciente);

        tvPaciente = (TextView) findViewById(R.id.tvPaciente);
    }
}
