package br.fucapi.fapeam.monitori.model.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.TextView;
import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.Diagnosticar;
import br.fucapi.fapeam.monitori.model.bean.Usuario;

public class DiagnosticarHelper {
	
	private Diagnosticar diagnosticar;
	private EditText descrever;
	private Usuario usuario;
	private Calendar dataHoraDiagnostico = Calendar.getInstance();
	
	private SimpleDateFormat sdfDate;
	private SimpleDateFormat sdfTime;
	
	
	private TextView dataDiagnostico;
	private TextView horaDiagnostico;
	
	private FragmentActivity fragmentActivity;
	
	public DiagnosticarHelper(final FragmentActivity fragmentActivity, Usuario usuario){
		this.fragmentActivity = fragmentActivity;
		this.usuario = usuario;
		//criacao do objeto paciente
		diagnosticar = new Diagnosticar();
		
		descrever = (EditText) fragmentActivity.findViewById(R.id.edDiagnosticar);
		
		dataDiagnostico = (TextView) fragmentActivity.findViewById(R.id.dataDiagnostico);
		horaDiagnostico = (TextView) fragmentActivity.findViewById(R.id.horaDiagnostico);
		
		updateLabel();
	}

	public Diagnosticar getDiagnosticar() {
		
		diagnosticar.setDescrever(getDescrever().getText().toString());
		diagnosticar.setUsuario(usuario);
		
		diagnosticar.setDataHoraDiagnostico(dataHoraDiagnostico);	
		
		return (Diagnosticar) diagnosticar;
	}

	public void setDiagnosticar(Diagnosticar diagnosticar) {
		
		getDescrever().setText(diagnosticar.getDescrever());
		usuario = diagnosticar.getUsuario();
		
		setDataHoraDiagnostico(diagnosticar.getDataHoraDiagnostico() );
		
		this.diagnosticar = diagnosticar;
	}

	public EditText getDescrever() {
		return descrever;
	}

	public void setDescrever(EditText descrever) {
		this.descrever = descrever;
	}
	public TextView getTextViewDataDiagnostico() {
		return dataDiagnostico;
	}

	public void setTextViewDataDiagnostico(TextView dataDiagnostico) {
		this.dataDiagnostico = dataDiagnostico;
	}

	public TextView getTextViewHoraDiagnostico() {
		return horaDiagnostico;
	}

	public void setTextViewHoraDiagnostico(TextView horaDiagnostico) {
		this.horaDiagnostico = horaDiagnostico;
	}
	
	public Calendar getDataHoraDiagnostico() {											
		return dataHoraDiagnostico;
	}

	public void setDataHoraDiagnostico(Calendar dataHoraDiagnostico) {
		if(dataHoraDiagnostico!=null){
			this.dataHoraDiagnostico = dataHoraDiagnostico;			
			updateLabel();
		}	
	}

	private void updateLabel() {
		  		 			 			 			 			 	 	
		sdfDate = new SimpleDateFormat(fragmentActivity.getString(R.string.DATE_LONG_FORMAT_APLICATION));
		sdfTime = new SimpleDateFormat(fragmentActivity.getString(R.string.TIME_FORMAT_APLICATION));
		String dateFormated = sdfDate.format(dataHoraDiagnostico.getTime());
		String timeFormated = sdfTime.format(dataHoraDiagnostico.getTime());
		
		dataDiagnostico.setText(dateFormated);
		horaDiagnostico.setText(timeFormated);
	 	 	 			 			        	      
	}
}
