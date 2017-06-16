package ti.saude.odontec.odontec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class odontecInicio extends AppCompatActivity implements View.OnClickListener {

    Button btAnestesico, btFarmacologia, btInformacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odontec_inicio);

        btAnestesico = (Button) findViewById(R.id.btAnest);
        btFarmacologia = (Button) findViewById(R.id.btFarmaco);
        btInformacao = (Button) findViewById(R.id.btInfor);

        btAnestesico.setOnClickListener(this);
        btFarmacologia.setOnClickListener(this);

       }



    @Override
    public void onClick(View view) {

        if (view == btAnestesico) {
            Intent intent = new Intent(odontecInicio.this, tipoPaciente.class);
            startActivity(intent);
        }else if(view == btFarmacologia){
            Intent intent = new Intent(odontecInicio.this, listaFarmaco.class);
            startActivity(intent);
        }else {

        }
    }
}
