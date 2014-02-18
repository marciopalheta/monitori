package br.fucapi.fapeam.monitori.model.helper;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.AgenteDadosActivity;
import br.fucapi.fapeam.monitori.activity.MedicoDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Agente;
import br.fucapi.fapeam.monitori.model.bean.Medico;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import android.content.Context;
import android.widget.EditText;


public class AgenteHelper extends UsuarioHelper {
		
	private Agente agente;
	private EditText matricula;
	
	public AgenteHelper(MedicoDadosActivity activity){
		super(activity);
						
		//criacao do objeto paciente
		agente= new Agente();
		
		matricula =(EditText) activity.findViewById(R.id.edMatricula);
	}

	public AgenteHelper(AgenteDadosActivity activity){
		super(activity);
						
		//criacao do objeto paciente
		agente= new Agente();
		
		matricula =(EditText) activity.findViewById(R.id.edMatricula);
	}

	public Agente getAgente(){
		
		agente.setNome(getNome().getText().toString());
		agente.setEndereco(getEndereco().getText().toString());		
		agente.setCep(getCep().getText().toString());
		agente.setCelular(getCelular().getText().toString());
		agente.setTelefone(getTelefone().getText().toString());
		agente.setLogin(agente.getNome());
		agente.setSenha(agente.getNome());		
		agente.setMatricula(matricula.getText().toString());
		agente.setTipoUsuario(TipoUsuario.AGENTE);
		
		
		return agente;		
	}
	
	public void setAgente(Agente agente){
		
		getNome().setText(agente.getNome());		
		getTelefone().setText(agente.getTelefone());
		getEndereco().setText(agente.getEndereco());
		
		this.agente = agente;
		
	}
}