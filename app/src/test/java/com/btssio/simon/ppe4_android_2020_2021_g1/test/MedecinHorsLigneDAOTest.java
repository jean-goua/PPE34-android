package com.btssio.simon.ppe4_android_2020_2021_g1.test;

import android.content.Context;

import com.btssio.simon.ppe4_android_2020_2021_g1.modele.Departement;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.Medecin;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.MedecinEnLigneDAO;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.MedecinHorsLigneDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by morin on 22/01/2021.
 */

// test unitaire de MedecinHorsLigneDAO (simon)
public class MedecinHorsLigneDAOTest {

    MedecinHorsLigneDAO MedLocal;
    Medecin monMed1;
    Medecin monMed2;
    Medecin monMed3;
    Departement monDep1;

    @Before
    public void setUp() throws Exception {
        // problème : impossible de récupérer le context
        MedLocal = new MedecinHorsLigneDAO();
        monMed1 = new Medecin(1,"Dujuin","Rodolphe","Rue de la ville","24110","Saint-Astier", (float) 0.123,"CP","0123456789");
        monMed2 = new Medecin(2,"Ovier","Louis","Rue du quartier","24430","Marsac", (float) 0.456,"AU","7894653120");
        monMed3 = new Medecin(3,"Lambert","Jerome","Rue des beaux quartiers","25000","Besançon", (float) 0.789,"DY","4561237890");
        monDep1 = new Departement("24",2,"Dordogne");
        MedLocal.addMedecin(monMed1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getMedecin() throws Exception {
        Assert.assertEquals("Vérifie que les médecins sont bons", 1, MedLocal.getMedecin().get(0).getNumpraticien());
        MedLocal.addMedecin(monMed2);
        Assert.assertEquals("Vérifie que les médecins sont bons", 2, MedLocal.getMedecin().size());
        Assert.assertEquals("Vérifie que les médecins sont bons", 2, MedLocal.getMedecin().get(1).getNumpraticien());
    }

    @Test
    public void getMedecinByNum() throws Exception {
        Assert.assertEquals("Vérifie que les médecins du num département renseigné sont bons", 2, MedLocal.getMedecinByNum(monDep1.getnumdepartement()).size());
        Assert.assertEquals("Vérifie que les médecins du num département renseigné sont bons", 1, MedLocal.getMedecinByNum(monDep1.getnumdepartement()).get(0).getNumpraticien());
        Assert.assertEquals("Vérifie que les médecins du num département renseigné sont bons", 2, MedLocal.getMedecinByNum(monDep1.getnumdepartement()).get(1).getNumpraticien());
    }

    @Test
    public void getMedecinByNom() throws Exception {
        Assert.assertEquals("Vérifie que les médecins du nom département renseigné sont bons", 2, MedLocal.getMedecinByNum(monDep1.getnomdepartement()).size());
        Assert.assertEquals("Vérifie que les médecins du nom département renseigné sont bons", 1, MedLocal.getMedecinByNum(monDep1.getnomdepartement()).get(0).getNumpraticien());
        Assert.assertEquals("Vérifie que les médecins du nom département renseigné sont bons", 2, MedLocal.getMedecinByNum(monDep1.getnomdepartement()).get(1).getNumpraticien());
    }

    @Test
    public void addMedecin() throws Exception {
        MedLocal.addMedecin(monMed3);
        Assert.assertEquals("Vérifie que le médecin est ajouté", 3, MedLocal.getMedecin().size());
        Assert.assertEquals("Vérifie que le médecin est ajouté", 3, MedLocal.getMedecin().get(2).getNumpraticien());
    }

    @Test
    public void deleteMedecins() throws Exception {
        MedLocal.deleteMedecins();
        Assert.assertEquals("Vérifie qu'il n'y a plus de médecins", 0, MedLocal.getMedecin().size());
    }

}