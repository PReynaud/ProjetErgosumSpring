package com.epul.ergosum.controle;

import com.epul.ergosum.metier.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.epul.ergosum.meserreurs.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MultiControlleur extends MultiActionController {

    /**
     * Simply selects the home view to render by returning its name.
     */
    private Jouet unJouet;

    @RequestMapping(value = "Accueil", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        return "/Accueil";
    }

    /**
     * Affichage de tous les jouets
     */
    @RequestMapping(value = "afficherJouets")

    public ModelAndView afficherLesJouets(HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {

        String destinationPage = "";
        GestionErgosum unService = new GestionErgosum();
        request.setAttribute("mesJouets", unService.listerTousLesJouets());
        destinationPage = "/ListeJouets";

        return new ModelAndView(destinationPage);
    }



    /**
     * Ajout d'un jouet
     */
    @RequestMapping(value = "ajouterJouet")

    public ModelAndView ajoutJouet(HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        String destinationPage = "";

        GestionErgosum unService = new GestionErgosum();

        // on passe les numéros de client et de vendeur
        request.setAttribute("jouet", new Jouet());
        request.setAttribute("categories", unService.listerToutesLesCategories());
        request.setAttribute("tranches", unService.listerToutesLesTranches());
        request.setAttribute("catalogues", unService.listerTousLesCatalogues());
        request.setAttribute("nbCatalogues", request.getParameter("nbCatalogues"));


        destinationPage = "/AjouterJouet";

        return new ModelAndView(destinationPage);
    }



    /**
     * Sélection d'une année par catégorie
     */
    @RequestMapping(value = "selectionnerAnnee")

    public ModelAndView selectionAnnee(HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {

        String destinationPage = "";
        destinationPage = "/selectionAnneeCat";
        return new ModelAndView(destinationPage);
    }

    /**
     * Sélection d'une année Ctagoriet
     */
    @RequestMapping(value = "listerCatalogue")

    public ModelAndView choixCatalogue(HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {

        String destinationPage = "/Erreur";
        GestionErgosum unService = new GestionErgosum();

        request.setAttribute("catalogues", unService.listerTousLesCatalogues());
        destinationPage = "/ChoixCatalogue";


        return new ModelAndView(destinationPage);
    }


    /**
     * Modifier Jouet
     */
    @RequestMapping(value = "modifierJouet")

    public ModelAndView modifierJouet(HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        String destinationPage = "Erreur";
        String id = request.getParameter("id");
        GestionErgosum unService = new GestionErgosum();

        Jouet unJouet = unService.rechercherJouet(id);
        request.setAttribute("jouet", unJouet);
        request.setAttribute("categories", unService.listerToutesLesCategories());
        request.setAttribute("tranches", unService.listerToutesLesTranches());
        request.setAttribute("catalogues", unService.listerTousLesCatalogues());
        destinationPage = "/ModifierJouet";


        return new ModelAndView(destinationPage);
    }


    /**
     * Sauver jouet
     */
    @RequestMapping(value = "sauverJouet")

    public ModelAndView sauverJouet(HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        String destinationPage = "/Erreur";
        try
        {
            String id = request.getParameter("id");
            GestionErgosum unService = new GestionErgosum();
            // fabrication du jouet à partir des paramètres de la requête
            // Si le jouet n'est pas à créer, il faut le récupérer de la session
            // courante
            // Ensuite on peut modifier ses champs

            if (request.getParameter("type").equals("ajout")){
                unJouet = new Jouet();
                List<Jouet>jouets = unService.listerTousLesJouets();
                unJouet.setNumero(((Integer) (Integer.parseInt(jouets.get(jouets.size() - 1).getNumero()) + 1)).toString());
        }
            else
            { // on récupère le jouet courant
                unJouet = unService.rechercherJouet(id);
            }
            unJouet.setLibelle(request.getParameter("libelle"));
            Categorie uneCateg = unService.rechercherCategorie(request.getParameter("codecateg"));
            unJouet.setCategorie(uneCateg);

            Trancheage uneTranche = unService.rechercherTrancheage(request.getParameter("codetranche"));
            unJouet.setTrancheage(uneTranche);

            // sauvegarde du jouet
            if (request.getParameter("type").equals("modif"))
            {
                unService.modifier(unJouet);
            } else
            {
                String nbCatalogues = request.getParameter("nb-catalogues");
                for(int i = 1; i <= Integer.parseInt(nbCatalogues); i++){
                    Catalogue leCatalogue = unService.rechercherCatalogue(request.getParameter("codeCatalogue" + i));

                    int quantiteDistribution = Integer.parseInt(request.getParameter("quantiteDistribution" + i));
                    if (quantiteDistribution>0)
                    {
                        leCatalogue.setQuantiteDistribuee(leCatalogue.getQuantiteDistribuee()+quantiteDistribution);
                        unService.modifierCatalogue(leCatalogue);
                    }
                    unJouet.ajouterComportes(leCatalogue, quantiteDistribution);
                }
                unService.ajouter(unJouet);
            }
            request.setAttribute("mesJouets", unService.listerTousLesJouets());
            destinationPage = "/ListeJouets";
        } catch (Exception e)
        {
            request.setAttribute("MesErreurs", e.getMessage());
        }

        return new ModelAndView(destinationPage);
    }



    /**
     *  effacer  jouet
     */
    @RequestMapping(value = "effacerJouet")
    public ModelAndView effacerJouet(HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try
        {
            String id = request.getParameter("id");
            GestionErgosum unService = new GestionErgosum();

            // recuperation de la liste des id a effacer
            String[] ids = request.getParameterValues("id");
            // effacement de la liste des id
            if (ids != null)
            {
                unService.effacer(ids);
            }
            // preparation de la liste
            request.setAttribute("mesJouets", unService.listerTousLesJouets());

            destinationPage = "/ListeJouets";
        }
        catch (Exception e)
        {
            request.setAttribute("MesErreurs", e.getMessage());
        }
        return new ModelAndView(destinationPage);
    }


    /**
     *  afficher Catalogue
     */
    @RequestMapping(value = "afficherCatalogues")
    public ModelAndView afficherCatalogue(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "/Erreur";
        GestionErgosum unService = new GestionErgosum();

        request.setAttribute("catalogues", unService.listerCatalogueQuantites());
        if(request.getParameter("anneeDebut") != null && request.getParameter("anneeFin") != null){
            request.setAttribute("mesCataloguesQuantites", unService.listerCatalogueQuantites(Integer.parseInt(request.getParameter("anneeDebut")), Integer.parseInt(request.getParameter("anneeFin"))));
        }
        else{
            request.setAttribute("mesCataloguesQuantites", unService.listerCatalogueQuantites());
        }
       destinationPage = "/AfficherCatalogues";

        return new ModelAndView(destinationPage);
    }




    /**
     *  afficher le Dictionnaire
     */
    @RequestMapping(value = "afficherDictionnaire")
    public ModelAndView afficherDictionnaire(HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {

        String destinationPage = "/Erreur";
        String annee = request.getParameter("annee");
        GestionErgosum unService = new GestionErgosum();

        HashMap<Categorie, Integer> hashCatInt = unService.rechercherDictionnaire(annee);
        request.setAttribute("mesCatalogues", unService.listerCatalogueQuantites());
        request.setAttribute("dictionnaire", hashCatInt);
        request.setAttribute("anneecatalogue", annee);
        destinationPage = "/AfficherDictionnaire";

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value="rechercherJouet")
    public ModelAndView rechercherJouet(HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
        String destinationPage = "/Erreur";
        String search = request.getParameter("search");

        GestionErgosum unService = new GestionErgosum();
        request.setAttribute("mesJouets", unService.listerTousLesJouets(search));
        destinationPage = "/ListeJouets";

        return new ModelAndView(destinationPage);
    }


}

	