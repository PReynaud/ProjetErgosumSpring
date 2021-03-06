package com.epul.ergosum.controle;

import com.epul.ergosum.meserreurs.MonException;
import com.epul.ergosum.metier.*;
import com.epul.ergosum.persistance.DialogueBd;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
        String request = "SELECT * FROM jouet order by numero*1;";
        int index = 0;
        try {
            rs = DialogueBd.lecture(request);

            while (index < rs.size()) {
                // On crée un stage
                Jouet unJ = new Jouet();
                // il faut redecouper la liste pour retrouver les lignes
                unJ.setNumero(rs.get(index).toString());
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

    public List<Jouet> listerTousLesJouets(String search) throws MonException{
        List<Object> rs;
        List<Jouet> mesJouets = new ArrayList<Jouet>();
        String request = "SELECT * FROM jouet WHERE libelle LIKE '%" + search + "%';";
        int index = 0;
        try {
            rs = DialogueBd.lecture(request);

            while (index < rs.size()) {
                // On crée un stage
                Jouet unJ = new Jouet();
                // il faut redecouper la liste pour retrouver les lignes
                unJ.setNumero(rs.get(index).toString());
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
                Categorie unC = new Categorie();
                unC.setCodecateg(rs.get(index).toString());
                unC.setLibcateg(rs.get(index + 1).toString());

                index = index + 2;
                mesCategories.add(unC);
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
                unT.setCodetranche(rs.get(index).toString());
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

    public List<Catalogue> listerTousLesCatalogues() throws MonException {
        List<Object> rc;
        List<Catalogue> mesCatalogues = new ArrayList<Catalogue>();
        String request = "SELECT * FROM catalogue;";
        int index = 0;
        try {
            rc = DialogueBd.lecture(request);

            while (index < rc.size()) {
                Catalogue unC = new Catalogue();
                unC.setAnnee(Integer.parseInt(rc.get(index).toString()));
                unC.setQuantiteDistribuee(Integer.parseInt(rc.get(index + 1).toString()));
                index = index + 2;
                mesCatalogues.add(unC);
            }
            return mesCatalogues;

        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Jouet rechercherJouet(String id) throws MonException {
        List<Object> rs, rs2;
        Jouet monJouet = new Jouet();
        String request = "SELECT * FROM jouet where numero=" + id + ";",
            request2 = "SELECT b.annee, quantite, b.quantiteDistribuee from comporte natural join catalogue b where numero='"+id+"';";
        int index = 0;
        try {
            rs = DialogueBd.lecture(request);
            rs2 = DialogueBd.lecture(request2);
            if (rs.size()>=3) {
                // il faut redecouper la liste pour retrouver les lignes
                monJouet.setNumero(id);
                monJouet.setLibelle(rs.get(3).toString());
                monJouet.setCategorie(new Categorie(rs.get(1).toString()));
                monJouet.setTrancheage(new Trancheage(rs.get(2).toString()));
                while (index<rs2.size()){
                    Catalogue catalogue = new Catalogue(Integer.parseInt(rs2.get(index).toString()),Integer.parseInt(rs2.get(index + 2).toString()));
                    monJouet.ajouterComportes(catalogue,Integer.parseInt(rs2.get(index+1).toString()));
                    index+=3;
                }
            }
            return monJouet;

        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Categorie rechercherCategorie(String codecateg) throws MonException {
        List<Object> rs;
        Categorie maCategorie = new Categorie();
        String request = "SELECT * FROM categorie where codecateg=" + codecateg + ";";
        int index = 0;
        try {
            rs = DialogueBd.lecture(request);

            if (index < rs.size()) {
                maCategorie.setCodecateg(codecateg);
                maCategorie.setLibcateg(rs.get(index + 1).toString());
            }
            return maCategorie;

        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public Trancheage rechercherTrancheage(String codetranche) throws MonException {
        List<Object> rs;
        Trancheage maTranche = new Trancheage();
        String request = "SELECT * FROM trancheage where codetranche=" + codetranche + ";";
        int index = 0;
        try {
            rs = DialogueBd.lecture(request);

            if (index < rs.size()) {
                maTranche.setCodetranche(codetranche);
                maTranche.setAgemin(Integer.parseInt(rs.get(index + 1).toString()));
                maTranche.setAgemax(Integer.parseInt(rs.get(index + 2).toString()));
            }
            return maTranche;

        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void modifier(Jouet unJouet) throws MonException {
        StringBuilder request = new StringBuilder();
        request.append("UPDATE jouet SET codecateg=");
        request.append(unJouet.getCategorie().getCodecateg());
        request.append(", codetranche");
        request.append(unJouet.getTrancheage().getCodetranche());
        request.append(", libelle=");
        request.append(unJouet.getLibelle());
        request.append(" where numero=");
        request.append(unJouet.getNumero());
        try {
            DialogueBd.insertionBD(request.toString());
        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Catalogue rechercherCatalogue(String codecatalogue) throws MonException {
        List<Object> rc;
        Catalogue monCatalogue = new Catalogue();
        String request = "SELECT * FROM catalogue where annee=" + codecatalogue + ";";
        int index = 0;
        try {
            rc = DialogueBd.lecture(request);

            if (index < rc.size()) {
                monCatalogue.setAnnee(Integer.parseInt(codecatalogue));
                monCatalogue.setQuantiteDistribuee(Integer.parseInt(rc.get(index + 1).toString()));
            }
            return monCatalogue;

        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void modifierCatalogue(Catalogue leCatalogue) throws MonException{
        StringBuilder request = new StringBuilder();
        request.append("UPDATE catalogue SET quantiteDistribuee=");
        request.append(leCatalogue.getQuantiteDistribuee());
        request.append(" where annee=");
        request.append(leCatalogue.getAnnee());
        try {
            DialogueBd.insertionBD(request.toString());
        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void ajouter(Jouet unJouet) throws MonException{
        ArrayList<StringBuilder> requests = new ArrayList<StringBuilder>();
        StringBuilder request2 = new StringBuilder();
        Iterator i=unJouet.getComportes().iterator();
        while (i.hasNext()){
            Comporte comporte = (Comporte)i.next();
            StringBuilder request = new StringBuilder();
            request.append("INSERT INTO comporte (annee,numero,quantite) values (");
            request.append(comporte.getId().getAnnee());
            request.append(", '");
            request.append(comporte.getId().getNumero());
            request.append("', ");
            request.append(comporte.getQuantite());
            request.append(");");
            requests.add(request);
        }

        request2.append("INSERT INTO jouet (numero,codecateg,codetranche,libelle) values ('");
        request2.append(unJouet.getNumero());
        request2.append("', '");
        request2.append(unJouet.getCategorie().getCodecateg());
        request2.append("', '");
        request2.append(unJouet.getTrancheage().getCodetranche());
        request2.append("', '");
        request2.append(unJouet.getLibelle());
        request2.append("');");

        //TODO faire l'ajout aux catalogues du champ comportes
        try {
            DialogueBd.insertionBD(request2.toString());
            for(StringBuilder request:requests)
                DialogueBd.insertionBD(request.toString());
        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    public void effacer(String[] ids)throws MonException{
        List<Object> rc;
        int index = 0;
        try {
            for (String id : ids) {
                rc = DialogueBd.lecture("SELECT annee, quantite from comporte where numero='"+id+"';");
                while (index<rc.size()){
                    DialogueBd.insertionBD("UPDATE catalogue SET quantiteDistribuee=quantiteDistribuee-"+rc.get(index+1).toString()+" WHERE annee =" + rc.get(index).toString() + ";");
                    index+=2;
                }
                DialogueBd.insertionBD("DELETE FROM comporte WHERE numero = '" + id + "';");
                DialogueBd.insertionBD("DELETE FROM jouet WHERE numero ='" + id + "';");
            }
        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Object listerCatalogueQuantites(int anneeDebut, int anneeFin) throws MonException{
        List<Object> rc;
        List<CatalogueQuantites> mesCatalogues = new ArrayList<CatalogueQuantites>();
        String request = "SELECT DISTINCT c.annee, c.numero, c.quantite, ca.quantiteDistribuee FROM comporte c JOIN catalogue ca ON c.annee=ca.annee" +
                " where ca.annee>='" + anneeDebut + "' AND ca.annee<='"+anneeFin+"' group by annee;";
        int index = 0;
        try {
            rc = DialogueBd.lecture(request);

            while (index < rc.size()) {
                CatalogueQuantites monCatalogue = new CatalogueQuantites();
                monCatalogue.setId(rc.get(index).toString());
                monCatalogue.setQuantite(rc.get(index + 2).toString());
                monCatalogue.setQuantiteDistribuee(rc.get(index + 3).toString());
                index = index + 4;
                mesCatalogues.add(monCatalogue);
            }
            return mesCatalogues;

        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<CatalogueQuantites> listerCatalogueQuantites() throws MonException{
        List<Object> rc;
        List<CatalogueQuantites> mesCatalogues = new ArrayList<CatalogueQuantites>();
        String request1 = "SELECT Distinct c.annee, c.numero, c.quantite, ca.quantiteDistribuee FROM comporte c JOIN catalogue ca ON c.annee=ca.annee group by annee";
        int index = 0;
        try {
            rc = DialogueBd.lecture(request1);
            while (index < rc.size()) {
                CatalogueQuantites monCatalogue = new CatalogueQuantites();
                monCatalogue.setId(rc.get(index).toString());
                monCatalogue.setQuantite(rc.get(index + 2).toString());
                monCatalogue.setQuantiteDistribuee(rc.get(index + 3).toString());
                index = index + 4;
                mesCatalogues.add(monCatalogue);
            }
            return mesCatalogues;

        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public HashMap<Categorie, Integer> rechercherDictionnaire(String annee)throws MonException {
        HashMap<Categorie,Integer> res = new HashMap<Categorie, Integer>();
        List<Object> rc;
        int index = 0;
        String request = "SELECT codecateg, SUM(b.quantite) " +
                         "FROM categorie NATURAL JOIN (" +
                                " SELECT codecateg, a.quantite " +
                                "FROM jouet natural join ( " +
                                    "SELECT numero, quantite " +
                                    "FROM comporte where annee="+annee+") a) b group by codecateg;";
        try {
            Catalogue monCatalogue = rechercherCatalogue(annee);
            rc = DialogueBd.lecture(request);
            while (index < rc.size()) {
                res.put(rechercherCategorie(rc.get(index).toString()), Integer.parseInt(rc.get(index+1).toString()));
                index+=2;
            }
            return res;
        } catch (MonException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
