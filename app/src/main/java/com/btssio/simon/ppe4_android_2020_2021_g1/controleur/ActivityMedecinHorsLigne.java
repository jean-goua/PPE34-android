package com.btssio.simon.ppe4_android_2020_2021_g1.controleur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.btssio.simon.ppe4_android_2020_2021_g1.R;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.Departement;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.DepartementHorsLigneDAO;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.Medecin;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.MedecinHorsLigneDAO;

import java.util.ArrayList;

public class ActivityMedecinHorsLigne extends AppCompatActivity {

    private Spinner spinner_departement;
    private Spinner spinner_medecin;
    private Departement departementSelectionne;
    private Medecin medecinSelectionne;

    //Déclaration des caractéristiques d'un praticien
    private TextView num;
    private TextView nom;
    private TextView prenom;
    private TextView adresse;
    private TextView codepostal;
    private TextView ville;
    private TextView coef;
    private TextView typecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecin_hors_ligne);



        remplirSpinDepartement();
        initialiseMedecin();
    }



    public void initialiseMedecin(){
        num = (TextView) findViewById(R.id.num);
        nom = (TextView) findViewById(R.id.nom);
        prenom = (TextView) findViewById(R.id.prenom);
        adresse = (TextView) findViewById(R.id.adresse);
        codepostal = (TextView) findViewById(R.id.codepostal);
        ville = (TextView) findViewById(R.id.ville);
        coef = (TextView) findViewById(R.id.coef);
        typecode = (TextView) findViewById(R.id.typecode);
    }


    public void remplirSpinDepartement(){

        DepartementHorsLigneDAO departementAcces = new DepartementHorsLigneDAO(this); {



            final ArrayList<Departement> resultat = departementAcces.getDepartement();

            Log.d("log", "remplirSpinDepartement: " + resultat);

                // remplissage du spinner a l'aide de la liste recuperee
            spinner_departement = (Spinner) findViewById(R.id.spinner_departement);
                ArrayAdapter<String> spinDepartementsAdapter = new ArrayAdapter<String>(
                        ActivityMedecinHorsLigne.this.getBaseContext(),android.R.layout.simple_spinner_item
                );
                for(int i=0;i<resultat.size();i++){
                    spinDepartementsAdapter.add(resultat.get(i).getnomdepartement());
                }
            spinner_departement.setAdapter(spinDepartementsAdapter);

                // gestion des evenements sur le spinner de selection du viticulteur
            spinner_departement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        // TODO Auto-generated method stub
                        Log.d("log",arg2 + " "+ resultat.get(arg2));
                        departementSelectionne = resultat.get(arg2);


                        //Spinner médecin dépendant du spinner département
                        MedecinHorsLigneDAO medecinAccess = new MedecinHorsLigneDAO(getApplicationContext()); {

                            final ArrayList<Medecin> resultat = medecinAccess.getMedecinByNom(departementSelectionne.getnomdepartement());

                            Log.d("log", "remplirSpinDepartement: " + resultat);

                                // remplissage du spinner a l'aide de la liste recuperee
                                Spinner spinner_medecin = (Spinner) findViewById(R.id.spinner_medecin);
                                ArrayAdapter<String> spinMedecinAdapter = new ArrayAdapter<String>(
                                        ActivityMedecinHorsLigne.this.getBaseContext(),android.R.layout.simple_spinner_item
                                );
                                for(int i=0;i<resultat.size();i++){
                                spinMedecinAdapter.add(resultat.get(i).getNompraticien());
                                }
                                spinner_medecin.setAdapter(spinMedecinAdapter);

                                // gestion des evenements sur le spinner de selection du viticulteur
                                spinner_medecin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                                    @Override
                                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                               int arg2, long arg3) {
                                        // TODO Auto-generated method stub
                                        Log.d("log",arg2 + " "+ resultat.get(arg2));
                                        medecinSelectionne = resultat.get(arg2);

                                        if (resultat == null) {

                                            nom.setText("Aucun praticien");

                                        } else {
                                            medecinSelectionne = resultat.get(arg2);
                                            num.setText(Integer.toString((medecinSelectionne.getNumpraticien())));
                                            nom.setText(medecinSelectionne.getNompraticien());
                                            prenom.setText(medecinSelectionne.getPrenom());
                                            adresse.setText(medecinSelectionne.getAdressepraticien());
                                            codepostal.setText((medecinSelectionne.getCodepostalpraticien()));
                                            ville.setText(medecinSelectionne.getVillepraticien());
                                            coef.setText(Float.toString(medecinSelectionne.getCoefpraticien()));
                                            typecode.setText(medecinSelectionne.getTypecode());
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> arg0) {
                                        // TODO Auto-generated method stub
                                    }
                                });

                            }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                });

            }
    }


}
