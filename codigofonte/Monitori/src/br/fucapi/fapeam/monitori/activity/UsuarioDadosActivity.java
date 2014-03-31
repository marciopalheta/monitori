package br.fucapi.fapeam.monitori.activity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.fucapi.fapeam.monitori.R;
import br.fucapi.fapeam.monitori.model.dao.UnidadeSaudeDAO;
import br.fucapi.fapeam.monitori.model.helper.UsuarioHelper;
import br.fucapi.fapeam.monitori.utils.Funcoes;
import br.fucapi.fapeam.monitori.utils.RequestCodes;
import br.fucapi.fapeam.monitori.utils.SpinnerAdapter;
import br.fucapi.fapeam.monitori.utils.SpinnerObject;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public abstract class UsuarioDadosActivity extends FragmentActivity {

		private Spinner spinUbs;
		private ImageView foto;				    	
		private String localArquivo;
		
		private SpinnerAdapter adapter = null;	
		private FragmentActivity fragmentActivity = this;
		public UsuarioHelper helper;
		
		// directory name to store captured images and videos
		private static final String IMAGE_DIRECTORY_NAME = "Monitori";
		public static final int MEDIA_TYPE_IMAGE = 1;
		public static final int MEDIA_TYPE_VIDEO = 2;
		
		
		public abstract UsuarioHelper abstractHelper();				
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			helper = abstractHelper();
												
			foto = (ImageView) findViewById(R.id.foto);
						
			if (savedInstanceState != null) {
				localArquivo = (String) savedInstanceState
						.getSerializable("localArquivo");
			}
			if (localArquivo != null) {
				helper.carregarFoto(localArquivo);
			}
			
			foto.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {					
					
					
					// Checking camera availability
					if (!isDeviceSupportCamera()) {
						Toast.makeText(getApplicationContext(),
								"Sorry! Your device doesn't support camera",
								Toast.LENGTH_LONG).show();
						// will close the app if the device does't have camera
						finish();
					}else{
						selectImage();
					}
				}
			});
			
			
			
			spinUbs = (Spinner) findViewById(R.id.spinUbs);		
			
			atualizarListaUbs();
						
			spinUbs.setOnTouchListener(new View.OnTouchListener() {            
				@Override
				public boolean onTouch(View view, MotionEvent event) {
					if(view !=null){					
						Funcoes.hideKeyboard(fragmentActivity );						            
					}
					return false;
				}
	        });		
			
			
}

		private void atualizarListaUbs(){
			
			UnidadeSaudeDAO daoUbs = new UnidadeSaudeDAO(this);
			List<SpinnerObject> listaUbs = daoUbs.getUbsForSpinner();
			
								
			String[] StringArray = new String[listaUbs.size()];
			int index=0;
			
			for (SpinnerObject ubs : listaUbs) {				
				StringArray[index++] = ubs.toString();			
			}
			
			adapter = new SpinnerAdapter(fragmentActivity, R.layout.spinner_generic, StringArray ,listaUbs);
			spinUbs.setAdapter(adapter);
								    			
					   	    
		}

		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			//Definicao do menu inflater
			MenuInflater inflater = this.getMenuInflater();
			
			//Inflar um XML
			inflater.inflate(R.menu.menu_formularios, menu);
			
			return true;
		}
		
		public UsuarioHelper getHelper() {
			return helper;
		}

		public Spinner getSpinUbs() {
			return spinUbs;
		}

		public void setSpinUbs(Spinner spinUbs) {
			this.spinUbs = spinUbs;
		}		
		
		public void startActivityCamera(String localArquivo){
				File arquivo = new File(localArquivo);
				//URI que informa onde o arquivo resultado deve ser salvo
				Uri localFoto = Uri.fromFile(arquivo);

				Intent irParaCamera = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);

				irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);
				
				startActivityForResult(irParaCamera, RequestCodes.CAPTURE_IMAGE_CAMERA);

		}

		private void capturePhotoFromCamera() {
			
			Uri fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
			localArquivo = fileUri.getPath();
			
			
			Intent irParaCamera = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			

			if(fileUri != null){
				irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);				
				startActivityForResult(irParaCamera, RequestCodes.CAPTURE_IMAGE_CAMERA);				
			}

						
			
		}

/*		
		public void capturePhotoFromCamera(){
			boolean success = false;
			
			Log.d("STATE", Environment.getExternalStorageState());
			File myFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), IMAGE_DIRECTORY_NAME);
			
			Log.d("PATH", myFolder.getAbsolutePath());
			localArquivo = myFolder.getAbsolutePath()+"/"+ System.currentTimeMillis() + ".jpg";
			
			
			
			if( myFolder.exists() ){	    	    	    	    	    	    	    	    	    	    	    	    	   
			    startActivityCamera(localArquivo);	    	    
			}else{
				
			    success = myFolder.mkdirs();	    	    
			    if( success ){	    	    		    	    	
			    	startActivityCamera(localArquivo);	    	    	
			    } else {
			        // Do something else on failure	    	    	
			        throw new RuntimeException("File Error in writing new folder");
			    }
			}
		}
*/
		public void capturePhotoFromGallery(){
			Intent intent = new Intent(
		            Intent.ACTION_PICK,
		            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		    intent.setType("image/*");
		    startActivityForResult(
		            Intent.createChooser(intent, "Select File"),
		            RequestCodes.CAPTURE_IMAGE_GALLERY);
		}


		private void selectImage() {
		    final CharSequence[] items = { getResources().getString(R.string.camera_foto) , getResources().getString(R.string.camera_galeria),
		    		getResources().getString(R.string.camera_cancelar) };

		    AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    builder.setTitle(getResources().getString(R.string.camera_add_foto));
		    builder.setItems(items, new DialogInterface.OnClickListener() {
		        @Override
		        public void onClick(DialogInterface dialog, int item) {
		            if (items[item].equals( getResources().getString(R.string.camera_foto) )) {
		                
		            	capturePhotoFromCamera();
		                
		            } else if (items[item].equals( getResources().getString(R.string.camera_galeria) )) {
		                capturePhotoFromGallery();
		                
		            } else if (items[item].equals(getResources().getString(R.string.camera_cancelar) )) {
		                dialog.dismiss();
		            }
		        }
		    });
		    builder.show();
		}
		
			
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		        super.onActivityResult(requestCode, resultCode, data);
		               
		        if (resultCode == RESULT_OK) {
		            if (requestCode == RequestCodes.CAPTURE_IMAGE_CAMERA) {            	
		    			//Log.i("Cap Foto", this.localArquivo );
		            	helper.carregarFoto(this.localArquivo);
		            	
		            	//update gallery
		            	sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + File.separator + IMAGE_DIRECTORY_NAME  )  ));
		            	
		            }else if (requestCode == RequestCodes.CAPTURE_IMAGE_GALLERY && null != data) {
		    					            	
		            	Uri selectedImage = data.getData();
		    			String[] filePathColumn = {MediaStore.Images.Media.DATA};
		    					    					    			
		    			Cursor cursor = getContentResolver().query(selectedImage,
		    					filePathColumn, null, null, null);
		    			cursor.moveToFirst();
		    			
		    			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		    			String picturePath = cursor.getString(columnIndex);
		    			cursor.close();
		    			localArquivo = picturePath;
		    			helper.carregarFoto(localArquivo);    			
		    		
		    		}             	            	
		            	
		        }else{
		        	localArquivo = null;
		        }
		    }
			
			
			@Override
			protected void onSaveInstanceState(Bundle outState) {
				super.onSaveInstanceState(outState);
				outState.putSerializable("localArquivo", localArquivo);
			}
											
			
			/**
			 * Checking device has camera hardware or not
			 * */
			private boolean isDeviceSupportCamera() {
				if (getApplicationContext().getPackageManager().hasSystemFeature(
						PackageManager.FEATURE_CAMERA)) {
					// this device has a camera
					return true;
				} else {
					// no camera on this device
					return false;
				}
			}
			
			

			/*
			 * Creating file uri to store image/video
			 */
			public Uri getOutputMediaFileUri(int type) {
				//file = getOutputMediaFile(type);
				return Uri.fromFile(getOutputMediaFile(type));
			}

			/*
			 * returning image / video
			 */
			private static File getOutputMediaFile(int type) {

				// External sdcard location
				File mediaStorageDir = new File(
						Environment
								.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
						IMAGE_DIRECTORY_NAME);

				// Create the storage directory if it does not exist
				if (!mediaStorageDir.exists()) {
					if (!mediaStorageDir.mkdirs()) {
						Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
								+ IMAGE_DIRECTORY_NAME + " directory");
						return null;
					}
				}

				// Create a media file name
				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
						Locale.getDefault()).format(new Date());
				File mediaFile;
				if (type == MEDIA_TYPE_IMAGE) {
					mediaFile = new File(mediaStorageDir.getPath() + File.separator
							+ "IMG_" + timeStamp + ".jpg");
				} else if (type == MEDIA_TYPE_VIDEO) {
					mediaFile = new File(mediaStorageDir.getPath() + File.separator
							+ "VID_" + timeStamp + ".mp4");
				} else {
					return null;
				}

				return mediaFile;
			}
			
			
}		
		
