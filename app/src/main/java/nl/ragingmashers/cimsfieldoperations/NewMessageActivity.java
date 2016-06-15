package nl.ragingmashers.cimsfieldoperations;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import nl.ragingmashers.cimsfieldoperations.fiop.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import nl.ragingmashers.cimsfieldoperations.fiop.ApiManager;

/**
 * Created by matth on 6/15/2016.
 */

public class NewMessageActivity extends AppCompatActivity {
    private Button btnSearchMedia, btnSend;
    private ImageView imgPreview;
    public static final int IMAGE_GALLERY_REQUEST = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        imgPreview = (ImageView)findViewById(R.id.imgPreview);
    }

    public void btnSearchMedia_Click(View v){
        // invoke the image gallery using an implict intent.
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        // where do we want to find the data?
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        // finally, get a URI representation
        Uri data = Uri.parse(pictureDirectoryPath);

        // set the data and type.  Get all image types.
        photoPickerIntent.setDataAndType(data, "image/*");

        // we will invoke this activity, and get something back from it.
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    public void btnSend_Click(View v){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_GALLERY_REQUEST){
                Uri imageUri = data.getData();
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    imgPreview.setImageBitmap(Bitmap.createScaledBitmap(image,300,200,false));
                    imgPreview.setScaleType(ImageView.ScaleType.FIT_START);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public class SendMessageTask extends AsyncTask<Void, Void, Boolean>{
        private final Message message;
        private final String imagePath;

        public SendMessageTask(Message message, String imagePath){
            this.message = message;
            this.imagePath = imagePath;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            int mediaId = ApiManager.getInstance().uploadImage(imagePath,1);
            if(mediaId < 1)return false;
            ApiManager.getInstance().postMessage(message.getMessage(),message.getTitle(),message.getTeam());
            return null;
        }

        @Override
        protected void onPostExecute(final Boolean success){

        }

        @Override
        protected void onCancelled(){
            
        }
    }
}
