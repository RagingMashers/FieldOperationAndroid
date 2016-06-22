package nl.ragingmashers.cimsfieldoperations;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText txtTitle, txtMessage;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        imgPreview = (ImageView)findViewById(R.id.imgPreview);
        txtTitle  = (EditText)findViewById(R.id.txtTitle);
        txtMessage  = (EditText)findViewById(R.id.txtMessage);
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
        if(txtTitle.getText().toString().equals("")){
            Toast.makeText(this,"Message needs a title!",Toast.LENGTH_LONG).show();
            return;
        }
        if(txtMessage.getText().toString().equals("")){
            Toast.makeText(this,"Message needs content!",Toast.LENGTH_LONG).show();
            return;
        }

        Message message = new Message(txtMessage.getText().toString(), txtTitle.getText().toString(), ApiManager.getInstance().getTeamId(),"E");
        String image;
        if(uri!= null)image = getRealPathFromURI(this,uri);
        else image = null;
        SendMessageTask sendMessageTask = new SendMessageTask(message,image ,this);
        sendMessageTask.execute();
    }

    private String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == IMAGE_GALLERY_REQUEST){
                uri = data.getData();
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(uri);
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
        private final Context context;

        public SendMessageTask(Message message, String imagePath, Context context){
            this.message = message;
            this.imagePath = imagePath;
            this.context = context;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if(!ApiManager.getInstance().isLoggedIn()){
                ApiManager.getInstance().login("testUser", "testPass");
            }
            int mediaId = imagePath == null ? -1 :  ApiManager.getInstance().uploadImage(imagePath,1);
            ApiManager.getInstance().postMessage(message.getMessage(),message.getTitle(),message.getTeam(),mediaId);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success){
            Toast.makeText(context,"Message posted!" + (success ? "True" : "False"),Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onCancelled(){

        }
    }
}
