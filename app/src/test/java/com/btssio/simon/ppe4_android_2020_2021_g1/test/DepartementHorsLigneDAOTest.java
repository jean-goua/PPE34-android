package com.btssio.simon.ppe4_android_2020_2021_g1.test;

import com.btssio.simon.ppe4_android_2020_2021_g1.modele.Departement;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.DepartementHorsLigneDAO;
import com.btssio.simon.ppe4_android_2020_2021_g1.modele.MedecinHorsLigneDAO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by morin on 22/01/2021.
 */

// test unitaire de DepartementHorsLigneDAO (simon)
public class DepartementHorsLigneDAOTest {

    DepartementHorsLigneDAO DepLocal;
    Departement monDep1;
    Departement monDep2;

    @Before
    public void setUp() throws Exception {
        // problème : impossible de récupérer le context
        DepLocal = new DepartementHorsLigneDAO();
        monDep1 = new Departement("24",2,"Dordogne");
        monDep2 = new Departement("48",13,"Lozère");
        DepLocal.addDepartement(monDep1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getDepartement() throws Exception {
        Assert.assertEquals("Vérifie que les départements sont bons", "24", DepLocal.getDepartement().get(0).getnumdepartement());
        DepLocal.addDepartement(monDep2);
        Assert.assertEquals("Vérifie que les départements sont bons", 2, DepLocal.getDepartement().size());
        Assert.assertEquals("Vérifie que les départements sont bons", "48", DepLocal.getDepartement().get(1).getnumdepartement());
    }

    @Test
    public void deleteDepartements() throws Exception {
        DepLocal.deleteDepartements();
        Assert.assertEquals("Vérifie que les départements sont bien supprimés", 0, DepLocal.getDepartement().size());
    }

    @Test
    public void addDepartement() throws Exception {
        DepLocal.addDepartement(monDep2);
        Assert.assertEquals("Vérifie que l'ajout de département est bien effectué", 2, DepLocal.getDepartement().size());
        Assert.assertEquals("Vérifie que l'ajout de département est bien effectué", "48", DepLocal.getDepartement().get(1).getnumdepartement());
    }
}