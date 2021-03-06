package nl.ragingmashers.cimsfieldoperations.fiop;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Rick on 18-5-2016.
 */
public class ApiManager {

    private static Gson gsonFactory(){
        GsonBuilder builder = new GsonBuilder();
        return builder.serializeNulls().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    }
    private static String ip = "http://145.93.88.121:8080/";

    private static String uploadUrl = ip + "MediaUpload.ashx?id=%s";
    private static String baseUrl = ip + "api/%s/%s/%s";

    private static ApiManager instance;
    public synchronized static ApiManager getInstance(){
        if(instance == null)
            instance = new ApiManager();
        return instance;
    }

    public ApiManager() {
        loadPersist();
    }

    public void loadPersist(){
        Log.d("API/PERSIST","load not implemented");
    }

    public void savePersist(){
        Log.d("API/PERSIST","save not implemented");
    }

    private String username;
    private String token;
    private int teamId;

    public boolean isLoggedIn(){
        return username!=null && token!=null;
    }

    public boolean login(String username, String password){

        String url = String.format(baseUrl+"?username=%s&password=%s","Authentication","Login","",username,password);
        String result = getRequest(url);
        if(result == null)
            return false;
        JSONObject obj;
        try {
            obj = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        try {
            boolean succes = obj.getBoolean("loggedin");
            if(!succes) {
                Log.d("REQUEST",obj.toString());
                return false;
            }

            this.username = obj.getString("username");
            this.token = obj.getString("token");
            this.teamId = obj.getInt("teamId");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        savePersist();
        return true;
    }

    public boolean logout() {
        String url = String.format(baseUrl+"?username=%s&token=%s","Authentication","Logout","",username,token);
        String result = getRequest(url);
        if(result == null)
            return false;
        JSONObject obj;
        try {
            obj = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        try {
            boolean succes = obj.getBoolean("succes");
            if(!succes) {
                Log.d("REQUEST",obj.toString());
                return false;
            }

            this.username = null;
            this.token=null;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        savePersist();
        return true;
    }

    public List<Message> getMessages() {
        String url = String.format(baseUrl+"?username=%s&token=%s","Messages","GetMessages","",username,token);
        String result = getRequest(url);
        if(result == null)
            return null;
        JSONObject obj;
        try {
            obj = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        try {
            boolean succes = obj.getBoolean("succes");
            if(!succes) {
                Log.d("REQUEST",obj.toString());
                return null;
            }

            if(!obj.has("messages"))
                return null;

            List<Message> messages;
            messages = gsonFactory().fromJson(obj.getJSONArray("messages").toString(),new TypeToken<List<Message>>(){}.getType());

            if(messages == null)
                return null;
            return messages;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean postMessge(String message, String title, int team){
        return postMessage(message,title,team,-1);
    }

    public boolean postMessage(String message, String title, int team, final int mediaid) {
        String url = String.format(baseUrl+"?username=%s&token=%s","Messages","PostMessage","",username,token);

        Message message1 = new Message(message,title,team,"E");
        if (mediaid > 0)
            message1.setMedia(new ArrayList<Media>(){{
                add(new Media(mediaid,null,null,null,null,null));//rest is ignored
            }});
        else message1.setMedia(new ArrayList<Media>());
        String messageJ = gsonFactory().toJson(message1);
        String result = postRequest(url,messageJ.getBytes());

        if(result == null)
            return false;
        JSONObject obj;
        try {
            obj = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        try {
            boolean succes = obj.getBoolean("succes");
            if(!succes) {
                Log.d("REQUEST",obj.toString());
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String getRequest(String uri) {
        Log.d("HTTP GET","pending URL " + uri);
        try {
            URL url = new URL(uri);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type","application/json");

            try {
                Log.d("HTTP GET","Response code: " + urlConnection.getResponseCode());
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                StringBuilder responseOutput = new StringBuilder();
                while((line = in.readLine()) != null ) {
                    responseOutput.append(line);
                }
                in.close();
                String result = responseOutput.toString();
                Log.d("HTTP GET","Response: " + result);
                return result;
            } finally {
                urlConnection.disconnect();
                Log.d("HTTP GET","disconnect");
            }
        } catch (IOException ex){
            Log.d("HTTP GET","error IO");
            return null;
        }
    }

    private String postRequest(String uri, byte[] body){
        Log.d("HTTP POST","pending URL " + uri);
        try {
            URL url = new URL(uri);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(body);
            urlConnection.getOutputStream().flush();

            try {
                Log.d("HTTP GET","Response code: " + urlConnection.getResponseCode());
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                StringBuilder responseOutput = new StringBuilder();
                while((line = in.readLine()) != null ) {
                    responseOutput.append(line);
                }
                in.close();
                String result = responseOutput.toString();
                Log.d("HTTP GET","Response: " + result);
                return result;
            } finally {
                urlConnection.disconnect();
                Log.d("HTTP GET","disconnect");
            }
        } catch (IOException ex){
            Log.d("HTTP GET","error IO");
            return null;
        }
    }

    public int uploadImage(String imagefilename, int incidentId){
        Log.d("HTTP UPLOAD","Uploading " + imagefilename);

        String boundary = "*************";
        String[] split = imagefilename.split(File.pathSeparator);
        String exsistingFileName = split[split.length-1];//get file name
        String twoHyphens = "--";
        String lineEnd = "\r\n";
        try {
            URL url = new URL(String.format(uploadUrl, incidentId+""));

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);

            DataOutputStream dos = new DataOutputStream( urlConnection.getOutputStream() );

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + exsistingFileName +"\"" + lineEnd);
            dos.writeBytes(lineEnd);

            FileInputStream fin = new FileInputStream(imagefilename);
            byte[] buffer = new byte[1024];
            int read;
            while((read = fin.read(buffer))>0){
                dos.write(buffer,0,read);
            }
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            fin.close();
            dos.flush();

            try {
                Log.d("HTTP GET","Response code: " + urlConnection.getResponseCode());
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                StringBuilder responseOutput = new StringBuilder();
                while((line = in.readLine()) != null ) {
                    responseOutput.append(line);
                }
                in.close();
                String result = responseOutput.toString();
                Log.d("HTTP GET","Response: " + result);
                JSONObject obj;
                try {
                    obj = new JSONObject(result);
                    if(!obj.getBoolean("succes")){
                        Log.w("HTTP UPLOAD", "Error code: " + obj.getInt("errorCode"));
                        Log.w("HTTP UPLOAD", "Error text: " + obj.getString("errorText"));
                    }
                    return obj.getInt("mediaId");
                } catch (JSONException e) {
                    e.printStackTrace();
                    return -1;
                }
            } finally {
                urlConnection.disconnect();
                Log.d("HTTP GET","disconnect");
            }
        } catch (IOException ex){
            Log.d("HTTP GET","error IO");
            Log.d("HTTP GET",ex.getMessage());
            return -1;
        }
    }

    public String getUsername() {
        return username;
    }

    public int getTeamId() {
        return teamId;
    }
}
