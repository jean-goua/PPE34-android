package com.btssio.simon.ppe4_android_2020_2021_g1.modele;

/**
 * Created by aydin on 07/12/2020.
 */

public class Medecin {
    private int numpraticien;
    private String nompraticien;
    private String prenom;
    private String adressepraticien;
    private String codepostalpraticien;
    private String villepraticien;
    private Float coefpraticien;
    private String typecode;
    private String telephonepraticien;
    private String numDepartement;

    public Medecin(){

    }

    // contructeur utilisé création d'un médecin dans la bdsqlite
    public Medecin(  int lenumpraticien,String nom,String prenom,String adresse, String codepostal, String ville, Float coef, String letypecode,String telephone, String lenumdepartement){
        this.numpraticien=lenumpraticien;
        this.nompraticien=nom;
        this.prenom=prenom;
        this.adressepraticien=adresse;
        this.codepostalpraticien=codepostal;
        this.villepraticien=ville;
        this.coefpraticien=coef;
        this.typecode=letypecode;
        this.telephonepraticien=telephone;
        this.numDepartement=lenumdepartement;
    }

    // surcharge du contructeur utilisé sans le numdepartement
    public Medecin(  int lenumpraticien,String nom,String prenom,String adresse, String codepostal, String ville, Float coef, String letypecode,String telephone){
        this.numpraticien=lenumpraticien;
        this.nompraticien=nom;
        this.prenom=prenom;
        this.adressepraticien=adresse;
        this.codepostalpraticien=codepostal;
        this.villepraticien=ville;
        this.coefpraticien=coef;
        this.typecode=letypecode;
        this.telephonepraticien=telephone;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumpraticien() {
        return numpraticien;
    }

    public void setNumpraticien(int numpraticien) {
        this.numpraticien = numpraticien;
    }

    public String getNompraticien() {
        return nompraticien;
    }

    public void setNompraticien(String nompraticien) {
        this.nompraticien = nompraticien;
    }

    public String getAdressepraticien() {
        return adressepraticien;
    }

    public void setAdressepraticien(String adressepraticien) {
        this.adressepraticien = adressepraticien;
    }

    public String getCodepostalpraticien() {
        return codepostalpraticien;
    }

    public void setCodepostalpraticien(String codepostalpraticien) {
        this.codepostalpraticien = codepostalpraticien;
    }

    public String getVillepraticien() {
        return villepraticien;
    }

    public void setVillepraticien(String villepraticien) {
        this.villepraticien = villepraticien;
    }

    public Float getCoefpraticien() {
        return coefpraticien;
    }

    public void setCoefpraticien(Float coefpraticien) {
        this.coefpraticien = coefpraticien;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public String getTelephonepraticien() {
        return telephonepraticien;
    }

    public void setTelephonepraticien(String telephonepraticien) {
        this.telephonepraticien = telephonepraticien;
    }

    public String getNumDepartement(){
        return numDepartement;
    }

    public void setNumDepartement(String lenumdepartement) {
        this.numDepartement = lenumdepartement;
    }
}
