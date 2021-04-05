package com.btssio.simon.ppe4_android_2020_2021_g1.utilitaire;

import com.btssio.simon.ppe4_android_2020_2021_g1.modele.Departement;

import java.util.ArrayList;

/**
 * Created by morin on 14/12/2020.
 */

public interface EventAsyncDepartement {
    public void onTacheTerminee(String resultat);
    public void onTacheTerminee(ArrayList<Departement> resultat);
    public void onTacheTerminee(Departement resultat);
}
