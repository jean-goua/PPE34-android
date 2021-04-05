package com.btssio.simon.ppe4_android_2020_2021_g1.controleur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.btssio.simon.ppe4_android_2020_2021_g1.R;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.Departement;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.DepartementEnLigneDAO;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.Medecin;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.MedecinEnLigneDAO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
//pour commit
public class ActivityMedecinEnLigne extends AppCompatActivity {
    private Departement departementSelectionne;
    private Medecin medecinSelectionne;
    private TextView num ;
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
        setContentView(R.layout.activity_medecin_en_ligne);
        remplirSpinDepartement();
        nom=(TextView) findViewById(R.id.nom);
        num=(TextView) findViewById(R.id.num);
        prenom=(TextView) findViewById(R.id.prenom);
        adresse=(TextView) findViewById(R.id.adresse);
        codepostal=(TextView) findViewById(R.id.codepostal);
        ville=(TextView) findViewById(R.id.ville);
        coef=(TextView) findViewById(R.id.coef);
        typecode=(TextView) findViewById(R.id.typecode);
    }

    public void remplirSpinDepartement() {
        Log.d("log", "methode remplirSinViticulteurs()");
        DepartementEnLigneDAO departementAcces = new DepartementEnLigneDAO() {
            @Override
            public void onTacheTerminee(String resultat) {

            }

            @Override
            public void onTacheTerminee(final ArrayList<Departement> resultat) {
                Log.d("log", "MainActivity.getViticulteurs : " + resultat.toString());

                // remplissage du spinner a l'aide de la liste recuperee
                Spinner spindepartement = (Spinner) findViewById(R.id.spindepartement);
                ArrayAdapter<String> spinDepartementAdapter = new ArrayAdapter<>(
                        ActivityMedecinEnLigne.this.getBaseContext(), android.R.layout.simple_spinner_item
                );
                for (int i = 0; i < resultat.size(); i++) {
                    spinDepartementAdapter.add(resultat.get(i).getnomdepartement());
                }
                spindepartement.setAdapter(spinDepartementAdapter);

                // gestion des evenements sur le spinner de selection du viticulteur
                spindepartement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        // TODO Auto-generated method stub
                        Log.d("log", arg2 + " " + resultat.get(arg2));
                        departementSelectionne = resultat.get(arg2);

                        MedecinEnLigneDAO MedecinAcces = new MedecinEnLigneDAO() {
                            @Override
                            public void onTacheTerminee(String resultat) {

                            }

                            @Override
                            public void onTacheTerminee(final ArrayList<Medecin> resultat) {
                                Log.d("log", "MainActivity.getViticulteurs : " + resultat.toString());

                                // remplissage du spinner a l'aide de la liste recuperee
                                final Spinner spinmedecin = (Spinner) findViewById(R.id. spinmedecin);
                                ArrayAdapter<String> spinMedecinAdapter = new ArrayAdapter<>(
                                        ActivityMedecinEnLigne.this.getBaseContext(), android.R.layout.simple_spinner_item
                                );
                                for (int i = 0; i < resultat.size(); i++) {
                                    spinMedecinAdapter.add(resultat.get(i).getNompraticien());
                                }
                                spinmedecin.setAdapter(spinMedecinAdapter);

                                // gestion des evenements sur le spinner de selection du viticulteur
                                spinmedecin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                                               int arg2, long arg3) {
                                        // TODO Auto-generated method stub
                                        Log.d("log", arg2 + " " + resultat.get(arg2));
                                        if (resultat==null) {

                                            nom.setText("ok");

                                        }


                                        else {
                                            medecinSelectionne = resultat.get(arg2);
                                            num.setText(Integer.toString((medecinSelectionne.getNumpraticien())));
                                            nom.setText(medecinSelectionne.getNompraticien());
                                            prenom.setText(medecinSelectionne.getPrenom());
                                            adresse.setText(medecinSelectionne.getAdressepraticien());
                                            codepostal.setText(medecinSelectionne.getCodepostalpraticien());
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

                            @Override
                            public void onTacheTerminee(Medecin resultat) {

                            }
                        };

                        try {
                            MedecinAcces.arraytogetMedecinpardepartement(URLEncoder.encode(departementSelectionne.getnomdepartement(), "utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }

                });


            }

            @Override
            public void onTacheTerminee(Departement resultat) {

            }

        };

        departementAcces.getDepartements();
    }

}
