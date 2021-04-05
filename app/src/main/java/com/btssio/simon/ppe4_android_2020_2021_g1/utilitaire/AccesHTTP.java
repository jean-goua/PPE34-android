package com.btssio.simon.ppe4_android_2020_2021_g1.utilitaire;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by morin on 14/12/2020.
 */

public class AccesHTTP extends AsyncTask<String, Integer, Long> {

    public String ret="";
    private ArrayList<NameValuePair> parametres;

    public AccesHTTP(){
        parametres = new ArrayList<NameValuePair>();
    }

    public void addParam(String nom, String valeur){
        parametres.add(new BasicNameValuePair(nom,valeur));
    }

    // demande l'execution de la page php
    @Override
    protected Long doInBackground(String... urls) {
        HttpClient cnxHttp = new DefaultHttpClient();
        HttpPost paramCnx = new HttpPost(urls[0]);
        Log.d("log","urls[0] : "+urls[0]);

        try {
            paramCnx.setEntity(new UrlEncodedFormEntity(parametres));
            Log.d("log","parametres : "+parametres);
            HttpResponse reponse = cnxHttp.execute(paramCnx);

            ret = EntityUtils.toString(reponse.getEntity());
            Log.d("log","ret : "+ret);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result) {
        // preciser le code dans la classe qui l'appelle en surchargeant la methode
    }
}
