package br.fucapi.fapeam.monitori.model.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.medico.MedicoDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Medico;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import android.view.View;
import android.widget.EditText;

public class MedicoHelper extends UsuarioHelper {
		
	private Medico medico;
	private EditText crm;
	
	public MedicoHelper(MedicoDadosActivity activity){
		super(activity,TipoUsuario.MEDICO);
						
		//criacao do objeto paciente
		medico= new Medico();
		
		crm =(EditText) activity.findViewById(R.id.edCrm);
		
		Map<View, String> mapaDeCampos = new LinkedHashMap<View, String>();
		
		mapaDeCampos.put(getNome(), "Nome Obrigatório");
		mapaDeCampos.put(crm, "Campo Obrigatório");
		mapaDeCampos.put(getEditTextDataNascimento(), "Campo Obrigatório");
		mapaDeCampos.put(getTelefone(), "Telefone Obrigatório");		
		mapaDeCampos.put(getCep(), "Cep Obrigatório");
		
		setMapaDeCampos(mapaDeCampos);
		
	}

public Medico getMedico(){
		medico = (Medico) getUsuario();
				
		medico.setTipoUsuario(TipoUsuario.MEDICO);			
		((Medico)medico).setCrm(crm.getText().toString());
		
		medico.setLogin(crm.getText().toString());
		medico.setSenha(crm.getText().toString());
		
		return (Medico) medico;		
	}
	
	public void setMedico(Medico medico){
		setUsuario(medico);				
				
		crm.setText(medico.getCrm());
		this.medico= medico;
	}

	public EditText getCrm() {
		return crm;
	}

	public void setCrm(EditText crm) {
		this.crm = crm;
	}
	
}