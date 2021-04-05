package com.btssio.simon.ppe4_android_2020_2021_g1.controleur;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.btssio.simon.ppe4_android_2020_2021_g1.R;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.Departement;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.DepartementEnLigneDAO;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.DepartementHorsLigneDAO;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.Medecin;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.MedecinEnLigneDAO;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.MedecinHorsLigneDAO;
import com.btssio.simon.ppe4_android_2020_2021_g1.utilitaire.BdSQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {
    private Button bouton_maj;
    private Button bouton_consulter_connecter;
    private Button bouton_importer;
    private Button bouton_consulter_deconnecter;
    private BdSQLiteOpenHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bouton_maj = (Button) findViewById(R.id.bouton_MAJ);
        bouton_consulter_connecter = (Button) findViewById(R.id.bouton_consultation_connecté);
        bouton_importer = (Button) findViewById(R.id.bouton_importation);
        bouton_consulter_deconnecter = (Button) findViewById(R.id.bouton_consultation_déconnecté);
       

        bouton_consulter_connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ActivityMedecinEnLigne.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "consulter via service web !", Toast.LENGTH_LONG).show();
            }


        });

        bouton_importer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DepartementHorsLigneDAO DepartementLocalAccess = new DepartementHorsLigneDAO(getApplicationContext());
                    DepartementLocalAccess.deleteDepartements();
                    MedecinHorsLigneDAO MedecinLocalAccess = new MedecinHorsLigneDAO(getApplicationContext());
                    MedecinLocalAccess.deleteMedecins();
                try {
                    Importation();
                    Toast.makeText(getApplicationContext(), "Importation !", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Importation impossible, vous n'êtes pas connecté à internet !", Toast.LENGTH_LONG).show();
                    System.out.println("printStackTrace");
                    e.printStackTrace();
                }

            }
        });

        bouton_consulter_deconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "consulter via sqllite !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), ActivityMedecinHorsLigne.class);
                startActivity(intent);

            }


        });
        bouton_maj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "MAJ dans la base sqllite", Toast.LENGTH_LONG).show();
            }


        });

    }

    // Méthode d'importation des départements et des médecins dans la bd sqlite (simon)
    public void Importation() throws Exception{

        try {
            DepartementEnLigneDAO DepartementDistantAccess = new DepartementEnLigneDAO() {
                @Override
                public void onTacheTerminee(String resultat) {

                }

                @Override
                public void onTacheTerminee(ArrayList<Departement> resultat) {
                    DepartementHorsLigneDAO DepartementLocalAccess = new DepartementHorsLigneDAO(getApplicationContext());
                    for (int i = 0; i < resultat.size(); i++) {
                        DepartementLocalAccess.addDepartement(resultat.get(i));
                        //Log.d("Departement", String.valueOf(DepartementLocalAccess.getDepartement().get(i).getnumdepartement()));
                    }
                }

                @Override
                public void onTacheTerminee(Departement resultat) {

                }
            };
            DepartementDistantAccess.getDepartements();

            MedecinEnLigneDAO MedecinDistantAccess = new MedecinEnLigneDAO() {
                @Override
                public void onTacheTerminee(String resultat) {

                }

                @Override
                public void onTacheTerminee(ArrayList<Medecin> resultat) {
                    MedecinHorsLigneDAO MedecinLocalAccess = new MedecinHorsLigneDAO(getApplicationContext());
                    for (int i = 0; i < resultat.size(); i++) {
                        MedecinLocalAccess.addMedecin(resultat.get(i));
                        //Log.d("Medecin", String.valueOf(MedecinLocalAccess.getMedecin().get(i).getNumpraticien()) + " " + String.valueOf(MedecinLocalAccess.getMedecin().get(i).getNumDepartement() + " " + String.valueOf(MedecinLocalAccess.getMedecin().get(i).getCodepostalpraticien())));
                        //Log.d("Medecin2", String.valueOf(MedecinLocalAccess.getMedecin("75").get(i).getNompraticien()));
                    }

                }

                @Override
                public void onTacheTerminee(Medecin resultat) {

                }
            };
            MedecinDistantAccess.getMedecin();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
