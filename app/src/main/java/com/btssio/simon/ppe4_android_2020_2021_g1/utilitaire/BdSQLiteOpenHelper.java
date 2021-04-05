package com.btssio.simon.ppe4_android_2020_2021_g1.utilitaire;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.btssio.simon.ppe4_android_2020_2021_g1.modele.Departement;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.DepartementEnLigneDAO;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.DepartementHorsLigneDAO;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.Medecin;

import java.util.ArrayList;

/**
 * Created by morin on 14/12/2020.
 */

public class BdSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "gsb.db";

    //requete de création de la table Departement (simon)
    private String requeteDepartement="CREATE TABLE DEPARTEMENT("
            + " NUM_DEPARTEMENT TEXT PRIMARY KEY,"
            + " NUM_REGION INTEGER,"
            + " NOM_DEPARTEMENT TEXT);";

    //requete de création de la table Medecin (simon)
    private String requeteMedecin="CREATE TABLE MEDECIN("
            + " PRA_NUM INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " PRA_NOM TEXT,"
            + " PRA_PRENOM TEXT,"
            + " PRA_ADRESSE TEXT,"
            + " PRA_CP TEXT,"
            + " PRA_VILLE TEXT,"
            + " PRA_COEFNOTORIETE TEXT,"
            + " TYPE_CODE TEXT,"
            + " PRA_TELEPHONE TEXT,"
            + " NUM_DEPARTEMENT TEXT,"
            + " FOREIGN KEY(NUM_DEPARTEMENT) REFERENCES DEPARTEMENT(NUM_DEPARTEMENT));";

    public BdSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Execute les scripts sql de création des tables (simon)
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(requeteDepartement);
        Log.d("log","création réussie de la table Departement");

        db.execSQL(requeteMedecin);
        Log.d("log","création réussie de la table Medecin");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
