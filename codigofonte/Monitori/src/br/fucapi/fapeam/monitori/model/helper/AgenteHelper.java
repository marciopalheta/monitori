package br.fucapi.fapeam.monitori.model.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.agente.AgenteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Agente;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import android.view.View;
import android.widget.EditText;

public class AgenteHelper extends UsuarioHelper {
		
	private Agente agente;
	private EditText matricula;
	
	
	
	public EditText getMatricula() {
		return matricula;
	}

	public void setMatricula(EditText matricula) {
		this.matricula = matricula;
	}

	public AgenteHelper(AgenteDadosActivity activity){
		super(activity,TipoUsuario.AGENTE);
						
		//criacao do objeto paciente
		agente= new Agente();	
		matricula =(EditText) activity.findViewById(R.id.edMatricula);
		
		
		Map<View, String> mapaDeCampos = new LinkedHashMap<View, String>();
		
		mapaDeCampos.put(getNome(), "Nome Obrigatorio");
		mapaDeCampos.put(getCpf(), "CPF Obrigatorio");
		mapaDeCampos.put(matricula, "Campo Obrigatorio");
		mapaDeCampos.put(getEditTextDataNascimento(), "Campo Obrigatorio");
		mapaDeCampos.put(getTelefone(), "Telefone Obrigatorio");		
		mapaDeCampos.put(getCep(), "Cep Obrigatorio");
		
		
		
		setMapCamposObrigatorios(mapaDeCampos);
		setViewCamposObrigatorios();
	}
	
	public Agente getAgente(){
		agente = (Agente) getUsuario();
				
		agente.setTipoUsuario(TipoUsuario.AGENTE);
				
		agente.setMatricula(matricula.getText().toString());
		
		//agente.setLogin(matricula.getText().toString());
		//agente.setSenha(matricula.getText().toString());
		
		return agente;		
	}
	
	public void setAgente(Agente agente){
		setUsuario(agente);		
		
		matricula.setText(agente.getMatricula());
		this.agente= agente;
	}
	
	
}