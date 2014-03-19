package br.fucapi.fapeam.monitori.model.helper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.medico.MedicoDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.Medico;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.utils.Funcoes;
import br.fucapi.fapeam.monitori.utils.SpinnerObject;
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
		
		SpinnerObject SpinAux;
		int posicao;
				
		if(!getSpinBairro().getAdapter().isEmpty()){
			
			posicao = getSpinBairro().getSelectedItemPosition();
			SpinAux = (SpinnerObject)getSpinBairro().getAdapter().getItem(posicao);
			if(SpinAux.getId() != 0){
				Bairro auxBairro = new Bairro();
				auxBairro.setId( SpinAux.getId() );
				auxBairro.setNome( SpinAux.getValue() );
				medico.setBairro(auxBairro);
			}
		}
		
		if(!getSpinUbs().getAdapter().isEmpty()){
			posicao = getSpinUbs().getSelectedItemPosition();
			 
			SpinAux = (SpinnerObject)getSpinUbs().getAdapter().getItem(posicao);				
			if(SpinAux.getId() != 0){
				UnidadeSaude auxUbs = new UnidadeSaude();
				auxUbs.setId( SpinAux.getId() );
				auxUbs.setNome( SpinAux.getValue() );						
				
				medico.setUnidadeSaude(auxUbs);
			}
		}
				
		
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
		
		
		adapter = (br.fucapi.fapeam.monitori.utils.SpinnerAdapter) getSexo().getAdapter();
		List<SpinnerObject> list_sexo = adapter.getSpinnerObjects();
		int index=0, indexKey=0;		
		for (SpinnerObject sexo : list_sexo) {
			if(sexo.getValue().equals(medico.getSexo()) ){
				indexKey = index;
				break;	
			}
			index++;					
		}
		getSexo().setSelection(indexKey);
			
		
		
		adapter = (br.fucapi.fapeam.monitori.utils.SpinnerAdapter) getSpinBairro().getAdapter();
		List<SpinnerObject> list_bairro = adapter.getSpinnerObjects();
		index=0; indexKey=0;		
		if(medico.getBairro() !=null){
			for (SpinnerObject sobairro : list_bairro) {
				if(sobairro.getId() == medico.getBairro().getId() ){
					indexKey = index;
					break;	
				}
				index++;					
			}
		}
		getSpinBairro().setSelection(indexKey);
		
		adapter = (br.fucapi.fapeam.monitori.utils.SpinnerAdapter) getSpinUbs().getAdapter();
		List<SpinnerObject> list_ubs = adapter.getSpinnerObjects();
		index=0; indexKey=0;		
		if(medico.getUnidadeSaude() !=null){
			for (SpinnerObject soUbs: list_ubs) {
				if(soUbs.getId() == medico.getUnidadeSaude().getId() ){
					indexKey = index;
					break;	
				}
				index++;					
			}
		}
		getSpinUbs().setSelection(indexKey);
		
		
		
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