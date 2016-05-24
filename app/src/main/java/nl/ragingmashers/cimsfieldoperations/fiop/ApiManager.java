package nl.ragingmashers.cimsfieldoperations.fiop;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Rick on 18-5-2016.
 */
public class ApiManager {

    private Gson gsonFactory(){
        return null;
    }

    private static String baseUrl = "http://192.168.0.:15012/api/%s/%s/%s";

    private static ApiManager instance;
    public synchronized static ApiManager getInstance(){
        if(instance == null)
            instance = new ApiManager();
        return instance;
    }

    public void loadPersist(){

    }

    public void savePersist(){

    }

    private String username;
    private String token;

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
            boolean succes = obj.getBoolean("succes");
            if(!succes)
                return false;

            this.username = obj.getString("username");
            this.token = obj.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean logout() {
        String url = String.format(baseUrl+"?username=%s&password=%s","Authentication","Login","",username,token);
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
            if(!succes)
                return false;

            this.username = null;
            this.token=null;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String getRequest(String uri) {
        Log.d("HTTP GET","peningURL"+uri);
        try {
            URL url = new URL(uri);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return readStream(in);
            } finally {
                urlConnection.disconnect();
                Log.d("HTTP GET","disconnect");
            }
        } catch (IOException ex){
            Log.d("HTTP GET","error IO");
            return null;
        }
    }

    private String readStream(InputStream in) {
        Scanner scan = new Scanner(in);
        return scan.next("\\Z");
    }


}
