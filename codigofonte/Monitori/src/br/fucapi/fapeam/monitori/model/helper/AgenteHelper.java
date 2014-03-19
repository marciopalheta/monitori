package br.fucapi.fapeam.monitori.model.helper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.agente.AgenteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Agente;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.utils.Funcoes;
import br.fucapi.fapeam.monitori.utils.SpinnerObject;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

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
		super(activity);
						
		//criacao do objeto paciente
		agente= new Agente();	
		matricula =(EditText) activity.findViewById(R.id.edMatricula);
		
		
		Map<View, String> mapaDeCampos = new LinkedHashMap<View, String>();
		
		mapaDeCampos.put(getNome(), "Nome obrigatorio");
		mapaDeCampos.put(matricula, "Campo obrigatorio");
		mapaDeCampos.put(getEditTextDataNascimento(), "Campo obrigatorio");
		mapaDeCampos.put(getTelefone(), "Telefone obrigatorio");		
		mapaDeCampos.put(getCep(), "Cep obrigatorio");
		
		
		
		setMapaDeCampos(mapaDeCampos);
		
	}
	
	public Agente getAgente(){
		
		agente.setNome(getNome().getText().toString());
		agente.setEndereco(getEndereco().getText().toString());
		agente.setNumero(getNumero().getText().toString());
		agente.setCep(getCep().getText().toString());
		agente.setCelular(getCelular().getText().toString());
		agente.setTelefone(getTelefone().getText().toString());
				
		agente.setDataNascimento(getDataNascimento());					
		agente.setSexo( String.valueOf(getSexo().getSelectedItem()) );

		SpinnerObject SpinAux;
		int posicao;
		
		if(!getSpinBairro().getAdapter().isEmpty()){
			
			posicao = getSpinBairro().getSelectedItemPosition();
			SpinAux = (SpinnerObject)getSpinBairro().getAdapter().getItem(posicao);
			if(SpinAux.getId() != 0){
				Bairro auxBairro = new Bairro();
				auxBairro.setId( SpinAux.getId() );
				auxBairro.setNome( SpinAux.getValue() );
				agente.setBairro(auxBairro);
			}
		}
		
		if(!getSpinUbs().getAdapter().isEmpty()){
			posicao = getSpinUbs().getSelectedItemPosition();
			 
			SpinAux = (SpinnerObject)getSpinUbs().getAdapter().getItem(posicao);				
			if(SpinAux.getId() != 0){
				UnidadeSaude auxUbs = new UnidadeSaude();
				auxUbs.setId( SpinAux.getId() );
				auxUbs.setNome( SpinAux.getValue() );						
				
				agente.setUnidadeSaude(auxUbs);
			}
		}
		
		
		agente.setTipoUsuario(TipoUsuario.AGENTE);
		
		agente.setLogin(agente.getNome());
		agente.setSenha(agente.getNome());
		
		agente.setMatricula(matricula.getText().toString());
		
		return agente;		
	}
	
	public void setAgente(Agente agente){
						
		getNome().setText(agente.getNome());		
		getTelefone().setText(agente.getTelefone());
		getEndereco().setText(agente.getEndereco());
		getNumero().setText(agente.getNumero());
		getCep().setText(agente.getCep() );
		getCelular().setText(agente.getCelular());
		getTelefone().setText(agente.getTelefone());												
		setDataNascimento(agente.getDataNascimento());
		
		
		adapter = (br.fucapi.fapeam.monitori.utils.SpinnerAdapter) getSexo().getAdapter();
		List<SpinnerObject> list_sexo = adapter.getSpinnerObjects();
		int index=0, indexKey=0;		
		for (SpinnerObject sexo : list_sexo) {
			if(sexo.getValue().equals(agente.getSexo()) ){
				indexKey = index;
				break;	
			}
			index++;					
		}
		getSexo().setSelection(indexKey);
			
		
		
		adapter = (br.fucapi.fapeam.monitori.utils.SpinnerAdapter) getSpinBairro().getAdapter();
		List<SpinnerObject> list_bairro = adapter.getSpinnerObjects();
		index=0; indexKey=0;		
		if(agente.getBairro() !=null){
			for (SpinnerObject sobairro : list_bairro) {
				if(sobairro.getId() == agente.getBairro().getId() ){
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
		if(agente.getUnidadeSaude() !=null){
			for (SpinnerObject soUbs: list_ubs) {
				if(soUbs.getId() == agente.getUnidadeSaude().getId() ){
					indexKey = index;
					break;	
				}
				index++;					
			}
		}
		getSpinUbs().setSelection(indexKey);
		
		
		
		
		matricula.setText(agente.getMatricula());
		this.agente= agente;
	}
	
	
}