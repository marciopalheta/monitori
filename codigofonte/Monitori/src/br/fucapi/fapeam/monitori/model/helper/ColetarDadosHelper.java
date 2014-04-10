package br.fucapi.fapeam.monitori.model.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.ColetarDados;
import br.fucapi.fapeam.monitori.model.bean.Usuario;
import br.fucapi.fapeam.monitori.utils.Funcoes;

public class ColetarDadosHelper{
	
	private ColetarDados coletaDados;
	private EditText sis;
	private EditText sistole;
	private EditText glicose;
	private CheckBox jejum;
	private CheckBox pos_pandrial;
	private TextView dataColetas;
	private Calendar dataColeta = Calendar.getInstance();
	private SimpleDateFormat data = new SimpleDateFormat("d 'de' MMMM 'de' yyyy HH:mm");
	private Usuario usuario;	
	
	private FragmentActivity fragmentActivity;	
	
	public ColetarDadosHelper(final FragmentActivity fragmentActivity, Usuario usuario){
		this.fragmentActivity = fragmentActivity;
		this.usuario=usuario;
		//criacao do objeto paciente
		coletaDados = new ColetarDados();
		
		sis =(EditText) fragmentActivity.findViewById(R.id.edt_sis);
		sistole = (EditText) fragmentActivity.findViewById(R.id.edt_sistole);
		glicose = (EditText) fragmentActivity.findViewById(R.id.edt_glicose);
		jejum =(CheckBox) fragmentActivity.findViewById(R.id.chbjejum);
		pos_pandrial =(CheckBox) fragmentActivity.findViewById(R.id.chbpos_pandrial);
		dataColetas = (TextView) fragmentActivity.findViewById(R.id.dataColeta);
	
		String result = data.format(dataColeta.getTime());
		dataColetas.setText(result);
	}


	public EditText getSis() {
		return sis;
	}


	public void setSis(EditText sis) {
		this.sis = sis;
	}

	public EditText getSistole() {
		return sistole;
	}

	public void setSistole(EditText sistole) {
		this.sistole = sistole;
	}

	public EditText getGlicose() {
		return glicose;
	}

	public void setGlicose(EditText glicose) {
		this.glicose = glicose;
	}
	
	public ColetarDados getColetarDados(){
		
		coletaDados.setSis(getSis().getText().toString());
		coletaDados.setSistole(getSistole().getText().toString());
		coletaDados.setGlicose(getGlicose().getText().toString());				
											
		coletaDados.setJejum(jejum.isChecked());
		coletaDados.setPos_pandrial(pos_pandrial.isChecked());
		coletaDados.setUsuario(usuario);
		
		return (ColetarDados) coletaDados;		
	}
	
	public void setColetarDados(ColetarDados coletaDados){
						
		getSis().setText(coletaDados.getSis());	
		getSistole().setText(coletaDados.getSistole());
		getGlicose().setText(coletaDados.getGlicose());
		
		Log.e("TESTE", "is Jejum: " + coletaDados.isJejum() );
		jejum.setChecked(coletaDados.isJejum() );
		pos_pandrial.setChecked(coletaDados.isPos_pandrial() );
		
		usuario = coletaDados.getUsuario();
		
		this.coletaDados = coletaDados;	
	//Validando dados
	}
		
	
	public boolean validar(){
				
		// cria o mapa
		Map<View, String> mapaDeCampos = new LinkedHashMap<View, String>();
		mapaDeCampos.put(sis, "Campo obrigatorio");		
		mapaDeCampos.put(sistole, "Campo obrigatorio");
		mapaDeCampos.put(glicose, "Campo obrigatorio");
		
		for(View chave: mapaDeCampos.keySet()){
		    //System.out.println("chave: "+chave+", valor: "+mapaDeCampos.get(chave)+".");
		    if(Funcoes.validarDados(chave,  mapaDeCampos.get(chave) ) == false){
				return false;
			}
		}
			return true;
	}
	
}
