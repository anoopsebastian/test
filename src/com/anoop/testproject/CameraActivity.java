package com.anoop.testproject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends Activity {
	
	ImageView imagepreview;
	Button takephoto;
	final String TAG = "CameraActivity";
	private static final int CAMERA_PIC_REQUEST = 1337;  
	Bitmap imageCaptured = null;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		
		imagepreview = (ImageView)findViewById(R.id.imagepreview);
		takephoto = (Button)findViewById(R.id.takepicture);
	}
	
	// method to handle button click events
		public void ButtonClicked(View view) {
			if(view == takephoto) {
				Log.d(TAG, "CAMERA BUTTON CLICKED");
				TakePicture();
				SavePicture();
			}
		}

	private void SavePicture() {
		File PictureDir = getDir();
		if(!PictureDir.exists()) {
			Toast.makeText(getApplicationContext(), "Can't create directory to save image.",
			          Toast.LENGTH_LONG).show();
			return;
		}
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyymmddhhmmss");
		String date = dateformat.format(new Date());
		
		String photofile = "photo_"+date+".jpg";
		
		String filename = PictureDir.getPath() + File.separator + photofile;

	    File pictureFile = new File(filename);
			
	}
	
	private File getDir() {
		File sdDir = Environment
	      .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
	    return new File(sdDir, "CameraAPIDemo");
	  }

	private void TakePicture() {
		//Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		//startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
		
		Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath());
	    startActivityForResult(intent, 1);
		Log.d(TAG, "TAKING PICTURE");
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		/*if(requestCode == CAMERA_PIC_REQUEST) {
			imageCaptured = (Bitmap)data.getExtras().get("data");
			imagepreview.setImageBitmap(imageCaptured);
			Log.d(TAG, "IMAGE PREVIEW");
		}*/
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode)
        {
           case -1:     Log.i(TAG, "Photo Captured");
                        imageCaptured=(Bitmap) data.getExtras().get("data");
                        MediaStore.Images.Media.insertImage(getContentResolver(),imageCaptured,null,null);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        imageCaptured.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        imagepreview.setImageBitmap(imageCaptured);
                        break;
          default:      Log.i(TAG, "Cancelled Camera"); break;
        }
		
	}
	
	private boolean isSdPresent()
	{
	      return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}
	
	private String getPublic() {
		String s = Environment.DIRECTORY_PICTURES;
		return "";
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_camera, menu);
		return true;
	}

}
