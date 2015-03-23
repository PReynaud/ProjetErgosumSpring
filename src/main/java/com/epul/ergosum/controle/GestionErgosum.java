package com.epul.ergosum.controle;

import com.epul.ergosum.meserreurs.MonException;
import com.epul.ergosum.metier.Catalogue;
import com.epul.ergosum.metier.Categorie;
import com.epul.ergosum.metier.Jouet;
import com.epul.ergosum.metier.Trancheage;
import com.epul.ergosum.persistance.DialogueBd;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Pierre on 23/03/2015.
 */
public class GestionErgosum {
    public Object listerTousLesJouets(int categorieCode, int trancheCode) {
        return null;
    }

    public List<Jouet> listerTousLesJouets() throws MonException, ParseException {
        List<Object> rs;
        List<Jouet> mesJouets = new ArrayList<Jouet>();
        String request = "SELECT * FROM jouet;";
        int index = 0;
        try {
            rs = DialogueBd.lecture(request);

            while (index < rs.size()) {
                // On crée un stage
                Jouet unJ = new Jouet();
                // il faut redecouper la liste pour retrouver les lignes
                unJ.setNumero(rs.get(index + 0).toString());
                unJ.setLibelle(rs.get(index + 3).toString());
                unJ.setCategorie(new Categorie(rs.get(index + 1).toString()));
                unJ.setTrancheage(new Trancheage(rs.get(index + 2).toString()));
                // On incrémente tous les 4 champs
                index = index + 4;
                mesJouets.add(unJ);
            }
            return mesJouets;

        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<Categorie> listerToutesLesCategories() throws MonException {
        List<Object> rs;
        List<Categorie> mesCategories = new ArrayList<Categorie>();
        String request = "SELECT * FROM categorie;";
        int index = 0;
        try {
            rs = DialogueBd.lecture(request);

            while (index < rs.size()) {
                Categorie unT = new Categorie();
                unT.setCodecateg(rs.get(index + 0).toString());
                unT.setLibcateg(rs.get(index + 1).toString());
                index = index + 2;
                mesCategories.add(unT);
            }
            return mesCategories;

        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<Trancheage> listerToutesLesTranches() throws MonException {
        List<Object> rs;
        List<Trancheage> mesTranches = new ArrayList<Trancheage>();
        String request = "SELECT * FROM trancheage;";
        int index = 0;
        try {
            rs = DialogueBd.lecture(request);

            while (index < rs.size()) {
                Trancheage unT = new Trancheage();
                unT.setCodetranche(rs.get(index + 0).toString());
                unT.setAgemin(Integer.parseInt(rs.get(index + 1).toString()));
                unT.setAgemax(Integer.parseInt(rs.get(index + 2).toString()));
                index = index + 3;
                mesTranches.add(unT);
            }
            return mesTranches;

        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Object listerTousLesCatalogues() {
        return null;
    }

    public Jouet rechercherJouet(String id) {
        return null;
    }

    public Categorie rechercherCategorie(String codecateg) {
        return null;
    }


    public Trancheage rechercherTrancheage(String codetranche) {
        return null;
    }

    public void modifier(Jouet unJouet) {

    }

    public Catalogue rechercherCatalogue(String codecatalogue) {
        return null;
    }

    public void modifierCatalogue(Catalogue leCatalogue) {

    }

    public void ajouter(Jouet unJouet) {

    }


    public void effacer(String[] ids) {

    }

    public Object listerCatalogueQuantites(int anneeDebut, int nbAnnees) {
        return null;
    }

    public HashMap<Categorie, Integer> rechercherDictionnaire(String annee) {
        return null;
    }
}
