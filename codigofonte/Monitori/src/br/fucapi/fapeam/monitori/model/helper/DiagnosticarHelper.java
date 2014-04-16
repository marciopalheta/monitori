package br.fucapi.fapeam.monitori.model.helper;

import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Diagnosticar;
import br.fucapi.fapeam.monitori.model.bean.Usuario;

public class DiagnosticarHelper {
	
	private Diagnosticar diagnosticar;
	private EditText descrever;
	private Usuario usuario;
	
	private FragmentActivity fragmentActivity;
	
	public DiagnosticarHelper(final FragmentActivity fragmentActivity, Usuario usuario){
		this.fragmentActivity = fragmentActivity;
		this.usuario = usuario;
		//criacao do objeto paciente
		diagnosticar = new Diagnosticar();
		
		descrever = (EditText) fragmentActivity.findViewById(R.id.edDiagnosticar);
	}

	public Diagnosticar getDiagnosticar() {
		
		diagnosticar.setDescrever(getDescrever().getText().toString());
		diagnosticar.setUsuario(usuario);
		
		return (Diagnosticar) diagnosticar;
	}

	public void setDiagnosticar(Diagnosticar diagnosticar) {
		
		getDescrever().setText(diagnosticar.getDescrever());
		usuario = diagnosticar.getUsuario();
		
		
		this.diagnosticar = diagnosticar;
	}

	public EditText getDescrever() {
		return descrever;
	}

	public void setDescrever(EditText descrever) {
		this.descrever = descrever;
	}
}
