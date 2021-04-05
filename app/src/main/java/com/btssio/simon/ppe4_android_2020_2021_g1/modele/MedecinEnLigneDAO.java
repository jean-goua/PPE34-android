package com.btssio.simon.ppe4_android_2020_2021_g1.modele;

import android.util.Log;

import com.btssio.simon.ppe4_android_2020_2021_g1.utilitaire.AccesHTTP;
import com.btssio.simon.ppe4_android_2020_2021_g1.utilitaire.EventAsyncMedecin;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by morin on 14/12/2020.
 */
    //dzdzdzd
public abstract class MedecinEnLigneDAO implements EventAsyncMedecin {
    private static final String serveur = "172.24.0.33";
    private static final String chemin = "/GSB/";


    public MedecinEnLigneDAO() {
    }

    public void getMedecin() {
        AccesHTTP objAsyncTask = new AccesHTTP() {
            @Override
            protected void onPostExecute(Long result) {
                Log.d("log", "onPostExecute MedecinEnLigneDAO");
                onTacheTerminee(jsonStringToMedecinArrayList(this.ret));
            }
        };
        objAsyncTask.execute("http://"+serveur+chemin+"lesmedecins.php");
    }
    public void getMedecindepartement( String nomdepartement) {
        AccesHTTP objAsyncTask = new AccesHTTP() {
            @Override
            protected void onPostExecute(Long result) {
                Log.d("log", "onPostExecute MedecinEnLigneDAO");
                onTacheTerminee(jsonStringToMedecinArrayList(this.ret));
            }
        };
        objAsyncTask.execute("http://"+serveur+chemin+"lesmedecins_par_depart.php?libelledepartement="+nomdepartement);
    }

    private ArrayList<Medecin> jsonStringToMedecinArrayListdepartement(String jsonString) {
        Log.d("log", "conversion jsonToMedecinArray : "+jsonString);

        ArrayList<Medecin> listeMedecin = new ArrayList<Medecin>();
        int numpraticien;
        String nompraticien;
        String prenompraticien;
        String adressepraticien;
        String codepostalpraticien;
        String villepraticien;
        Float coefpraticien;
        String typecode;
        String telephonepraticien;


        try {
            JSONArray tabJson = new JSONArray(jsonString);
            for (int i = 0; i < tabJson.length(); i++) {
                numpraticien = Integer.parseInt(tabJson.getJSONObject(i).getString("PRA_NUM"));
                nompraticien = tabJson.getJSONObject(i).getString("PRA_NOM");
                prenompraticien = tabJson.getJSONObject(i).getString("PRA_NOM");
                adressepraticien = tabJson.getJSONObject(i).getString("PRA_PRENOM");
                codepostalpraticien = tabJson.getJSONObject(i).getString("PRA_ADRESSE");
                villepraticien = tabJson.getJSONObject(i).getString("PRA_CP");
                coefpraticien = Float.parseFloat(tabJson.getJSONObject(i).getString("PRA_VILLE"));
                typecode = tabJson.getJSONObject(i).getString("PRA_COEFNOTORIETE");
                telephonepraticien = tabJson.getJSONObject(i).getString("");

                listeMedecin.add(new Medecin(numpraticien, nompraticien, prenompraticien, adressepraticien, codepostalpraticien, villepraticien, coefpraticien, typecode, telephonepraticien));
            }
        } catch (JSONException e) {
            Log.d("log", "pb decodage JSON");
        }
        return listeMedecin;
    }
    private ArrayList<Medecin> jsonStringToMedecinArrayList(String jsonString) {
        Log.d("log", "conversion jsonToMedecinArray : "+jsonString);

        ArrayList<Medecin> listeMedecin = new ArrayList<Medecin>();
        int numpraticien;
        String nompraticien;
        String prenompraticien;
        String adressepraticien;
        String codepostalpraticien;
        String villepraticien;
        Float coefpraticien;
        String typecode;
        String telephonepraticien;

        try {
            JSONArray tabJson = new JSONArray(jsonString);
            for (int i = 0; i < tabJson.length(); i++) {
                numpraticien = Integer.parseInt(tabJson.getJSONObject(i).getString("PRA_NUM"));
                nompraticien = tabJson.getJSONObject(i).getString("PRA_NOM");
                prenompraticien = tabJson.getJSONObject(i).getString("PRA_PRENOM");
                adressepraticien = tabJson.getJSONObject(i).getString("PRA_ADRESSE");
                codepostalpraticien = tabJson.getJSONObject(i).getString("PRA_CP");
                villepraticien = tabJson.getJSONObject(i).getString("PRA_VILLE");
                coefpraticien = Float.parseFloat(tabJson.getJSONObject(i).getString("PRA_COEFNOTORIETE"));
                typecode = tabJson.getJSONObject(i).getString("TYP_CODE");
                telephonepraticien = tabJson.getJSONObject(i).getString("PRA_TELEPHONE");

                listeMedecin.add(new Medecin(numpraticien, nompraticien, prenompraticien, adressepraticien, codepostalpraticien, villepraticien, coefpraticien, typecode, telephonepraticien));
            }
        } catch (JSONException e) {
            Log.d("log", "pb decodage JSON");
        }
        return listeMedecin;
    }
    public void arraytogetMedecinpardepartement(String nomdepartement) {

        AccesHTTP objAsyncTask = new AccesHTTP() {
            @Override
            protected void onPostExecute(Long result) {
                Log.d("log", "onPostExecute MedecinEnLigneDAO");
                onTacheTerminee(jsonStringToMedecinArrayList(this.ret));
            }
        };
        objAsyncTask.execute("http://"+serveur+chemin+"lesmedecins_par_depart.php?libelledepartement="+nomdepartement);
    }
}
