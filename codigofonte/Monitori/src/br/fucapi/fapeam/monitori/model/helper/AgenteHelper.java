package br.fucapi.fapeam.monitori.model.helper;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.activity.AgenteDadosActivity;
import br.fucapi.fapeam.monitori.model.bean.Agente;
import br.fucapi.fapeam.monitori.model.bean.Bairro;
import br.fucapi.fapeam.monitori.model.bean.TipoUsuario;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
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

		Bairro auxBairro = new Bairro();				
		TextView idBairro = (TextView)getSpinBairro().getSelectedView().findViewById(R.id.textID); 
		auxBairro.setId(Long.parseLong( idBairro.getText().toString() ) );
		auxBairro.setNome(getSpinBairro().getSelectedItem().toString());					
		agente.setBairro(auxBairro);
		
		UnidadeSaude auxUbs = new UnidadeSaude();
		TextView idUbs = (TextView)getSpinUbs().getSelectedView().findViewById(R.id.textID); 
		auxUbs.setId(Long.parseLong( idUbs.getText().toString() ) );
		auxUbs.setNome(getSpinUbs().getSelectedItem().toString());					
		agente.setUnidadeSaude(auxUbs);
		
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
		
		ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)getSexo().getAdapter();
		getSexo().setSelection(array_spinner.getPosition( agente.getSexo() ));
	    				
		ArrayAdapter<String> array_bairro=(ArrayAdapter<String>)getSpinBairro().getAdapter();
		if(agente.getBairro() !=null){
			getSpinBairro().setSelection(array_bairro.getPosition( agente.getBairro().getNome() ) );
		}
			    						
		ArrayAdapter<String> array_ubs=(ArrayAdapter<String>)getSpinUbs().getAdapter();
		if(agente.getUnidadeSaude() !=null){
			getSpinUbs().setSelection(array_ubs.getPosition( agente.getUnidadeSaude().getNome() ) );
		}
		
		matricula.setText(agente.getMatricula());
		this.agente= agente;
	}
}