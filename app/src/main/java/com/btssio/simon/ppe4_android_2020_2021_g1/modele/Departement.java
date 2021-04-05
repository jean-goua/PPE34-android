package com.btssio.simon.ppe4_android_2020_2021_g1.modele;

/**
 * Created by aydin on 07/12/2020.
 */
//i
public class Departement {
    private String numdepartement;
    private int numeroregion;
    private String nomdepartement;

    public Departement(String numdepartement,int numeroregion,String nomdepartement){
        this.numdepartement=numdepartement;
        this.numeroregion=numeroregion;
        this.nomdepartement=nomdepartement;

    }
    public String getnumdepartement() {
        return numdepartement;
    }



    public int getnumeroregion() {
        return numeroregion;

    }




    public String getnomdepartement() {
        return nomdepartement;
    }



    public String toString(){
        return "numero du departement="+numdepartement+",numero de la region="+numeroregion+",nomdepartement="+nomdepartement;
    }
}
