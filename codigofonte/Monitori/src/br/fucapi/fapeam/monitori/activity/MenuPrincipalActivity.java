package br.fucapi.fapeam.monitori.activity;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.utils.AndroidUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuPrincipalActivity extends Activity implements OnClickListener {
	private Button btEsportivos;
	private Button btClassicos;
	private Button btLuxo;
	private Button btSobre;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Layout do Dashboard
		setContentView(R.layout.menuprincipal);
		btEsportivos = (Button) findViewById(R.id.btEsportivos);
		btEsportivos.setOnClickListener(this);
		btClassicos = (Button) findViewById(R.id.btClassicos);
		btClassicos.setOnClickListener(this);
		btLuxo = (Button) findViewById(R.id.btLuxo);
		btLuxo.setOnClickListener(this);
		btSobre = (Button) findViewById(R.id.btSobre);
		btSobre.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		boolean redeOk = AndroidUtils.isNetworkAvailable(this);
        if(redeOk) {
        	Intent intent = new Intent(this, PacienteDadosActivity.class);
    		if(v == btEsportivos) {
    			//intent.putExtra(Carro.TIPO, Carro.TIPO_ESPORTIVOS);
    			startActivity(intent);
    		} else if(v == btClassicos) {
    			//intent.putExtra(Carro.TIPO, Carro.TIPO_CLASSICO);
    			startActivity(intent);
    		} else if(v == btLuxo) {
    			//intent.putExtra(Carro.TIPO, Carro.TIPO_LUXO);
    			startActivity(intent);
    		} else if(v == btSobre) {
    			startActivity(new Intent(this, AgenteActivity.class));
    		}
        } else {
        	AndroidUtils.alertDialog(this, R.string.erro_conexao_indisponivel);
        }
	}
}
