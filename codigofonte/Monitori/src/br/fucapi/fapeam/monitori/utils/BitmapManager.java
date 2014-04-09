package br.fucapi.fapeam.monitori.utils;


import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;


public enum BitmapManager {
	 INSTANCE;
	 
	 private final Map<String, SoftReference<Bitmap>> cache;
	 private final ExecutorService pool;
	 private Map<ImageView, String> imageViews = Collections
	   .synchronizedMap(new WeakHashMap<ImageView, String>());
	 private Bitmap placeholder;
	 Context context;	 
	 BitmapManager() {
	  cache = new HashMap<String, SoftReference<Bitmap>>();
	  pool = Executors.newFixedThreadPool(5);
	 }
	 
	 public void setPlaceholder(Bitmap bmp) {
	  placeholder = bmp;
	 }
	 
	 public Bitmap getBitmapFromCache(String url) {
	  if (cache.containsKey(url)) {
	   return cache.get(url).get();
	  }
	 
	  return null;
	 }
	 
	 
	 public void queueJob(final String url, final ImageView imageView,			   	 
			   final int width, final int height) {
			  /* Create handler in UI thread. */
			  final Handler handler = new Handler() {
			   @Override
			   public void handleMessage(Message msg) {
			    String tag = imageViews.get(imageView);
			    if (tag != null && tag.equals(url)) {
			     if (msg.obj != null) {
			      imageView.setImageBitmap((Bitmap) msg.obj);	      	   	   
			     } else {
			      imageView.setImageBitmap(placeholder);	      
			      Log.d(null, "fail " + url);
			     }
			       imageView.setVisibility(View.VISIBLE);			   	   
			     
			    }
			   }
			  };
			 
			  pool.submit(new Runnable() {
			   @Override
			   public void run() {
			    final Bitmap bmp = downloadBitmap(url, width, height);
			    Message message = Message.obtain();
			    message.obj = bmp;
			    Log.d(null, "Item downloaded: " + url);
			 
			    handler.sendMessage(message);
			   }
			  });
			 }
	 
	 public void queueJob(final String url, final ImageView imageView,
	   final ProgressBar progressBar,	 
	   final int width, final int height) {
	  /* Create handler in UI thread. */
	  final Handler handler = new Handler() {
	   @Override
	   public void handleMessage(Message msg) {
	    String tag = imageViews.get(imageView);
	    if (tag != null && tag.equals(url)) {
	     if (msg.obj != null) {
	      imageView.setImageBitmap((Bitmap) msg.obj);	      	   	   
	     } else {
	      imageView.setImageBitmap(placeholder);	      
	      Log.d(null, "fail " + url);
	     }
	     	     
	    }else{
	    	imageView.setImageBitmap(placeholder);	    	
	    }
	    	imageView.setVisibility(View.VISIBLE);
	   	   progressBar.setVisibility(View.GONE);
	   }
	  };
	 
	  pool.submit(new Runnable() {
	   @Override
	   public void run() {
	    final Bitmap bmp = downloadBitmap(url, width, height);
	    Message message = Message.obtain();
	    message.obj = bmp;
	    Log.d(null, "Item downloaded: " + url);
	 
	    handler.sendMessage(message);
	   }
	  });
	 }
	  
	 			 
			 public void loadBitmap(final String url, final ImageView imageView, 					   
					   final int width, final int height) {
					  imageViews.put(imageView, url);
					  Bitmap bitmap = getBitmapFromCache(url);
					 
					  // check in UI thread, so no concurrency issues
					  if (bitmap != null) {
					   Log.d(null, "Item loaded from cache: " + url);
					   imageView.setImageBitmap(bitmap);
					   
					   imageView.setVisibility(View.VISIBLE);				   	   
					   	   
					  } else {
					   imageView.setImageBitmap(placeholder);
					   queueJob(url, imageView, width, height);
					  }
					 }
			 
			 

			 public void loadBitmap(final String url, final ImageView imageView, 
					   final ProgressBar progressBar,
					   final int width, final int height) {
					  imageViews.put(imageView, url);
					  Bitmap bitmap = getBitmapFromCache(url);
					 
					  // check in UI thread, so no concurrency issues
					  if (bitmap != null) {
					   Log.d(null, "Item loaded from cache: " + url);
					   imageView.setImageBitmap(bitmap);
					   
					   imageView.setVisibility(View.VISIBLE);
				   	   progressBar.setVisibility(View.GONE);
					   	   
					  } else {
					   imageView.setImageBitmap(placeholder);
					   queueJob(url, imageView,progressBar, width, height);
					  }
					 }
			 			 
	 private Bitmap downloadBitmap(String url, int width, int height) {
		  try {
			  if(url==null){
				  return null;
			  }
			  /*
			  BitmapFactory.Options options = new BitmapFactory.Options();														
			   // downsizing image as it throws OutOfMemory Exception for larger
			   // images
			  options.inSampleSize = 8;			
			  Bitmap bitmap  = BitmapFactory.decodeFile(url,
						options);		  
			  				  			   			   			   
			  bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
			  */
			  
			  BitmapFactory.Options options = new BitmapFactory.Options();									
				
				// downsizing image as it throws OutOfMemory Exception for larger
				// images
				options.inSampleSize = 8;			
				Bitmap bitmap = BitmapFactory.decodeFile(url,
						options);			
							
				if(bitmap==null){					
					return null;
				}else{
					
					
					bitmap = Funcoes.applyOrientation(bitmap,Funcoes.resolveBitmapOrientation(url) );
						
					if(bitmap.getWidth() < 100){
						bitmap = BitmapFactory.decodeFile(url);
					}
											
					
				}			
							  			  
			  
			  cache.put(url, new SoftReference<Bitmap>(bitmap));
		   return bitmap;
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 
		  return null;
		 }
	 		

}