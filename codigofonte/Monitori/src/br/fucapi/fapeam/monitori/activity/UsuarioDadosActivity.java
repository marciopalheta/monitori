package br.fucapi.fapeam.monitori.activity;

import java.io.File;
import java.util.List;
import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.bean.UnidadeSaude;
import br.fucapi.fapeam.monitori.model.dao.UnidadeSaudeDAO;
import br.fucapi.fapeam.monitori.utils.SpinnerAdapter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class UsuarioDadosActivity extends FragmentActivity {

	//Atributos para manipulacao de tela
		private Spinner spinUbs;
		private ImageView foto;
		private String localArquivo=null;

		private SpinnerAdapter adapter = null;
		
		private int selectionCurrent;

		private final int REQUEST_CODE_UBS = 12;
		private static final int REQUEST_CODE_FOTO = 123;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			foto = (ImageView) findViewById(R.id.foto);
			
			foto.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					localArquivo = Environment.getExternalStorageDirectory() 				
							+ "/" + System.currentTimeMillis() + ".jpg";
					
					File arquivo = new File(localArquivo);
					//URI que informa onde o arquivo resultado deve ser salvo
					Uri localFoto = Uri.fromFile(arquivo);

					Intent irParaCamera = new Intent(
							MediaStore.ACTION_IMAGE_CAPTURE);

					irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);
					
					startActivityForResult(irParaCamera, REQUEST_CODE_FOTO);

				}
			});
			
			spinUbs = (Spinner) findViewById(R.id.spinUbs);		
			atualizarListaUbs("");
			
			selectionCurrent = spinUbs.getSelectedItemPosition();
			
			spinUbs.setOnTouchListener(new View.OnTouchListener() {            
				@Override
				public boolean onTouch(View view, MotionEvent event) {
					if(view !=null){					
						TextView textId = (TextView)view.findViewById(R.id.textID);
						 if(event.getAction()==MotionEvent.ACTION_UP){
							 if (textId.getText().toString().equals("0") ){						
									getNovaUbs();
									return true;
							   }
			              }            
					}
					return false;
				}
	        });
			
			spinUbs.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
									
					if(selectedItemView !=null){
						if(selectionCurrent!=position){
							TextView textId = (TextView)selectedItemView.findViewById(R.id.textID);
							
							if (textId.getText().toString().equals("0") ){
								spinUbs.setSelection(0);						
								//Toast.makeText(fragmentActivity, "funcionou", Toast.LENGTH_LONG).show();
								getNovaUbs();
						    }
						}
					}
				} 			

				@Override
				public void onNothingSelected(AdapterView<?> parentView) {
				    // your code here
				}
				});
		}
		
		private void atualizarListaUbs(String nomeUBS){
			UnidadeSaudeDAO daoUbs = new UnidadeSaudeDAO(this); 
			List<UnidadeSaude> listaUbs = daoUbs.listar();										
			
			String[] stringArray = new String[listaUbs.size()+1];
			Integer[] intArray = new Integer[listaUbs.size()+1];
			int index = 0;
			int indexB = 0;
			for (UnidadeSaude ubs : listaUbs) {
				stringArray[index] = ubs.getNome();
				if(ubs.getNome().equals(nomeUBS)){
					indexB = index;	
				}
				intArray[index] = Integer.parseInt(ubs.getId().toString());
			  index++;
			}				
			
			stringArray[index] = getString(R.string.ubs_novo); 				
			intArray[index] = 0;
			adapter = new SpinnerAdapter(this, R.layout.spinner_generic,stringArray,intArray);
		    
			Log.i("Construtor teste", "Executou");
			
			spinUbs.setAdapter(adapter);	    	    
		    
		    //ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)getSpinBairro().getAdapter();
			spinUbs.setSelection(indexB);		   	    
		}

		private void getNovaUbs() {
			
			Intent intent = new Intent(this,
					UnidadeSaudeDadosActivity.class);
			
			//form.putExtra("PACIENTE_SELECIONADO", pacienteSelecionado);
			startActivityForResult(intent, REQUEST_CODE_UBS );					
		}
		
		@Override
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);

	        if(requestCode == REQUEST_CODE_UBS) {
	            if(resultCode == Activity.RESULT_OK) {
	                String nomeUBS = data.getStringExtra("NOVA_UBS");
	                //Log.e("REQUEST", nomeUBS);
	                //atualizar aqui a listagem
	                atualizarListaUbs(nomeUBS);
	            }
	        }else if (requestCode == REQUEST_CODE_FOTO) {
				if (resultCode == Activity.RESULT_OK) {
					//helper.carregarFoto(this.localArquivo);
					carregarFoto(localArquivo);
				} else {
					localArquivo = null;
				}
			}   
	    }
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			//Definicao do menu inflater
			MenuInflater inflater = this.getMenuInflater();
			
			//Inflar um XML
			inflater.inflate(R.menu.menu_formularios, menu);
			
			return true;
		}
		
		public Spinner getSpinUbs() {
			return spinUbs;
		}

		public void setSpinUbs(Spinner spinUbs) {
			this.spinUbs = spinUbs;
		}
		
		public void carregarFoto(String localFoto) {
			
			// Carregar arquivo de imagem
			Bitmap imagemFoto = BitmapFactory.decodeFile(localFoto);
			
			// Gerar imagem reduzida
			Bitmap imagemFotoReduzida = 
					Bitmap.createScaledBitmap(imagemFoto, 100,
					100, true);
			
			// Guarda o caminho da foto do aluno
			//aluno.setFoto(localFoto);
			
			// Atualiza a imagem exibida na tela de formulário
			foto.setImageBitmap(imagemFotoReduzida);
		}
		
		public String getLocalArquivo() {
			return localArquivo;
		}

		public void setLocalArquivo(String localArquivo) {
			this.localArquivo = localArquivo;
		}
}