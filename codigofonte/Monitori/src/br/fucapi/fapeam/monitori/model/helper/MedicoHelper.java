package br.fucapi.fapeam.monitori.model.helper;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.MedicoDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Medico;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import android.content.Context;
import android.widget.EditText;


public class MedicoHelper extends UsuarioHelper {
		
	private Medico medico;
	private EditText crm;
	
	public MedicoHelper(MedicoDadosActivity activity){
		super(activity);
						
		//criacao do objeto paciente
		medico= new Medico();
		
		crm =(EditText) activity.findViewById(R.id.edCrm);
	}

	public Medico getMedico(){
		
		medico.setNome(getNome().getText().toString());
		medico.setEndereco(getEndereco().getText().toString());		
		medico.setCep(getCep().getText().toString());
		medico.setCelular(getCelular().getText().toString());
		medico.setTelefone(getTelefone().getText().toString());
		medico.setLogin(medico.getNome());
		medico.setSenha(medico.getNome());		
		medico.setCrm(crm.getText().toString());
		medico.setTipoUsuario(TipoUsuario.MEDICO);
		
		
		return medico;		
	}
	
	public void setMedico(Medico medico){
		
		getNome().setText(medico.getNome());		
		getTelefone().setText(medico.getTelefone());
		getEndereco().setText(medico.getEndereco());
		
		this.medico = medico;
		
	}
}