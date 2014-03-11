package br.fucapi.fapeam.monitori.model.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.medico.MedicoDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.Medico;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.utils.Funcoes;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class MedicoHelper extends UsuarioHelper {
		
	private Medico medico;
	private EditText crm;
	
	public MedicoHelper(MedicoDadosActivity activity){
		super(activity);
						
		//criacao do objeto paciente
		medico= new Medico();
		
		crm =(EditText) activity.findViewById(R.id.edCrm);
		
		Map<View, String> mapaDeCampos = new LinkedHashMap<View, String>();
		
		mapaDeCampos.put(getNome(), "Nome obrigatorio");
		mapaDeCampos.put(crm, "Campo obrigatorio");
		mapaDeCampos.put(getEditTextDataNascimento(), "Campo obrigatorio");
		mapaDeCampos.put(getTelefone(), "Telefone obrigatorio");		
		mapaDeCampos.put(getCep(), "Cep obrigatorio");
		
		setMapaDeCampos(mapaDeCampos);
		
	}

public Medico getMedico(){
		
		medico.setNome(getNome().getText().toString());
		medico.setEndereco(getEndereco().getText().toString());
		medico.setNumero(getNumero().getText().toString());
		medico.setCep(getCep().getText().toString());
		medico.setCelular(getCelular().getText().toString());
		medico.setTelefone(getTelefone().getText().toString());
		medico.setDataNascimento(getDataNascimento());					
		medico.setSexo( String.valueOf(getSexo().getSelectedItem()) );

		Bairro auxBairro = new Bairro();				
		TextView idBairro = (TextView)getSpinBairro().getSelectedView().findViewById(R.id.textID); 
		auxBairro.setId(Long.parseLong( idBairro.getText().toString() ) );
		auxBairro.setNome(getSpinBairro().getSelectedItem().toString());					
		medico.setBairro(auxBairro);
				
		UnidadeSaude auxUbs = new UnidadeSaude();
		TextView idUbs = (TextView)getSpinUbs().getSelectedView().findViewById(R.id.textID); 
		auxUbs.setId(Long.parseLong( idUbs.getText().toString() ) );
		auxUbs.setNome(getSpinUbs().getSelectedItem().toString());					
		medico.setUnidadeSaude(auxUbs);
		
		medico.setTipoUsuario(TipoUsuario.MEDICO);
		
		medico.setLogin(medico.getNome());
		medico.setSenha(medico.getNome());
		
		medico.setCrm(crm.getText().toString());
		return medico;		
	}
	
	public void setMedico(Medico medico){
						
		getNome().setText(medico.getNome());		
		getTelefone().setText(medico.getTelefone());
		getEndereco().setText(medico.getEndereco());
		getNumero().setText(medico.getNumero());
		getCep().setText(medico.getCep() );
		getCelular().setText(medico.getCelular());
		getTelefone().setText(medico.getTelefone());												
		setDataNascimento(medico.getDataNascimento());
		
		ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)getSexo().getAdapter();
		getSexo().setSelection(array_spinner.getPosition( medico.getSexo() ));
	    				
		ArrayAdapter<String> array_bairro=(ArrayAdapter<String>)getSpinBairro().getAdapter();
		if(medico.getBairro() !=null){
			getSpinBairro().setSelection(array_bairro.getPosition( medico.getBairro().getNome() ) );
		}
			
		ArrayAdapter<String> array_ubs=(ArrayAdapter<String>)getSpinUbs().getAdapter();
		if(medico.getUnidadeSaude() !=null){
			getSpinUbs().setSelection(array_ubs.getPosition( medico.getUnidadeSaude().getNome() ) );
		}
		
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