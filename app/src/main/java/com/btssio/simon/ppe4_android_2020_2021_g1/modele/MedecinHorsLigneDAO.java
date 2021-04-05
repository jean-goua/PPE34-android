package com.btssio.simon.ppe4_android_2020_2021_g1.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.btssio.simon.ppe4_android_2020_2021_g1.utilitaire.BdSQLiteOpenHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by morin on 14/12/2020.
 */

public class MedecinHorsLigneDAO {

    private static String base = "BDMedecin";
    private static int version = 1;
    private BdSQLiteOpenHelper accesBD;

    public MedecinHorsLigneDAO(){}

    public MedecinHorsLigneDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    // méthode qui récupère tout les médecins (simon)
    public ArrayList<Medecin> getMedecin(){
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        Cursor curseur;
        String req = "select * from MEDECIN;";
        curseur = bd.rawQuery(req,null);
        ArrayList<Medecin> resultat = cursorToMedecinArrayList(curseur);
        bd.close();
        curseur.close();
        return resultat;
    }

    // parcours les informations d'un médecin grâce à un curseur (simon)
    private ArrayList<Medecin> cursorToMedecinArrayList(Cursor curseur){
        ArrayList<Medecin> listeMedecin = new ArrayList<Medecin>();
        int numPraticien;
        String nomPraticien;
        String prenomPraticien;
        String adressePraticien;
        String codePostalPraticien;
        String villePraticien;
        Float coefPraticien;
        String typeCode;
        String telephonePraticien;
        String numDepartement;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            numPraticien = curseur.getInt(0);
            nomPraticien = curseur.getString(1);
            prenomPraticien = curseur.getString(2);
            adressePraticien = curseur.getString(3);
            codePostalPraticien = curseur.getString(4);
            villePraticien = curseur.getString(5);
            coefPraticien = curseur.getFloat(6);
            typeCode = curseur.getString(7);
            telephonePraticien = curseur.getString(8);
            numDepartement = curseur.getString(9);

            listeMedecin.add(new Medecin(numPraticien, nomPraticien, prenomPraticien, adressePraticien, codePostalPraticien, villePraticien, coefPraticien, typeCode, telephonePraticien, numDepartement));
            curseur.moveToNext();
        }
        return listeMedecin;
    }

    // méthode qui récupère les medecins en fonction du numero de leur departement (simon)
    public ArrayList<Medecin> getMedecinByNum(String leNumDepartement){
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        Cursor curseur;
        String req = "select * from MEDECIN where NUM_DEPARTEMENT='"+leNumDepartement+"';";
        curseur = bd.rawQuery(req,null);
        ArrayList<Medecin> resultat = cursorToMedecinArrayList(curseur);
        bd.close();
        curseur.close();
        return resultat;
    }

    // méthode qui récupère les medecins en fonction du nom d'un departement (simon)
    public ArrayList<Medecin> getMedecinByNom(String leNomDepartement){
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        Cursor curseur;
        String req = "select PRA_NUM,PRA_NOM,PRA_PRENOM,PRA_ADRESSE,PRA_CP,PRA_VILLE,PRA_COEFNOTORIETE,TYPE_CODE,PRA_TELEPHONE,MEDECIN.NUM_DEPARTEMENT from MEDECIN,DEPARTEMENT where MEDECIN.NUM_DEPARTEMENT=DEPARTEMENT.NUM_DEPARTEMENT and NOM_DEPARTEMENT='"+leNomDepartement+"';";
        curseur = bd.rawQuery(req,null);
        ArrayList<Medecin> resultat = cursorToMedecinArrayList(curseur);
        bd.close();
        curseur.close();
        return resultat;
    }

    // méthode qui permet d'ajouter un medecin à la bdsqlite (simon)
    public void addMedecin(Medecin unMedecin){
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PRA_NUM", unMedecin.getNumpraticien());
        values.put("PRA_NOM", unMedecin.getNompraticien());
        values.put("PRA_PRENOM", unMedecin.getPrenom());
        values.put("PRA_ADRESSE", unMedecin.getAdressepraticien());
        values.put("PRA_CP", unMedecin.getCodepostalpraticien());
        values.put("PRA_VILLE", unMedecin.getVillepraticien());
        values.put("PRA_COEFNOTORIETE", unMedecin.getCoefpraticien());
        values.put("TYPE_CODE", unMedecin.getTypecode());
        values.put("PRA_TELEPHONE", unMedecin.getTelephonepraticien());
        // ajout des deux premiers chiffres du code postal au champ num_departement
        values.put("NUM_DEPARTEMENT", String.valueOf(unMedecin.getCodepostalpraticien()).substring(0,2));
        bd.insert("MEDECIN",null,values);
        bd.close();
    }

    // méthode qui permet de supprimer tout les médecins (simon)
    public void deleteMedecins(){
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String req = "DELETE FROM MEDECIN;";
        bd.execSQL(req);
        bd.close();
    }



}
