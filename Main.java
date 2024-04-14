import java.util.Scanner;

public class Main {

    private static Utilisateur recupererUtilisateur(Bibliotheque bibliotheque, Scanner scanner){
        int numero;
        if (bibliotheque.getListUtilisateurs().isEmpty()) {
            System.out.println("Il n'y a aucun utilisateur dans le Syteme");
            return null;
        }
        bibliotheque.afficherUtilisateur();
        do {
            System.out.print("Numéro de l'utilisateur: ");
            numero = scanner.nextInt();
        } while (numero > bibliotheque.getListUtilisateurs().size() || numero <= 0);
        return bibliotheque.getListUtilisateurs().get(numero - 1);
    }

    private static void gererAjoutEmprunt(Bibliotheque bibliotheque){
        Scanner sc = new Scanner(System.in);
        Utilisateur utilisateur = recupererUtilisateur(bibliotheque, sc);
        if (utilisateur == null ) return;
        utilisateur.emprunterLivre(bibliotheque);
    }
    
    private static void gererRetourLivre(Bibliotheque bibliotheque){
        Scanner scanner = new Scanner(System.in);
        Utilisateur utilisateur = recupererUtilisateur(bibliotheque, scanner);
        if (utilisateur == null ) return;
        utilisateur.retournerLivre(bibliotheque);
    }

    public static void nettoyerConsole() {
        try {
            String systemeExploitation = System.getProperty("os.name").toLowerCase();
            if (systemeExploitation.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {

        }
    }


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int choix;
        String retourAuMenuPrincipale,retourAuMenu;
        Bibliotheque bibliotheque = new Bibliotheque();
            do{ 
                retourAuMenuPrincipale = null;
                do{   
                    System.out.println("+----------------------------------------------------------+");
                    System.out.println("|   Bienvenue sur la platforme de Gestion de Bibliothèque  |");
                    System.out.println("+-+--------------------------------------------------------+");
                    System.out.println("|1|           GESTION DES LIVRES                           |");
                    System.out.println("+-+--------------------------------------------------------+");
                    System.out.println("|2|           GESTION DES EMPRUNTS                         |");
                    System.out.println("+-+--------------------------------------------------------+");
                    System.out.println("|3|           GESTION DES UTILISATEURS                     |");
                    System.out.println("+-+--------------------------------------------------------+");
                    System.out.println("|4|           QUITTER                                      |");
                    System.out.println("+-+--------------------------------------------------------+");
                    System.out.println("Veuiller faire votre choix:");
                    choix = scanner.nextInt();
                    scanner.nextLine();
                    nettoyerConsole();
                    switch (choix){
                        case 1:
                            do{
                                retourAuMenu = "";
                                do{

                                    System.out.println("+----------------------------------------------------------+");
                                    System.out.println("|                      GESTION DES LIVRES                  |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("|1|                       AJOUTER                          |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("|2|                       MODIFIER                         |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("|3|                       SUPPRIMER                        |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("|4|                       RECHERCHER                       |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("|5|                       QUITTER                          |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("Veuiller faire votre choix:");
                                    choix = scanner.nextInt();
                                    scanner.nextLine();
                                    nettoyerConsole();
                                } while (choix < 1 || choix > 5);

                                switch (choix) {
                                        case 1:
                                            bibliotheque.ajouterLivre();
                                            break;
                                        case 2:
                                            bibliotheque.modifierLivre();
                                            break;
                                        case 3:
                                            bibliotheque.supprimerLivre();
                                            break;
                                        case 4:
                                            Livre livre = bibliotheque.rechercherLivre();
                                            String reponse;
                                            if (livre != null) {
                                                System.out.println("Voulez-vous emprunter le livre (Oui/Non)???");
                                                reponse = scanner.next();
                                                nettoyerConsole();
                                                if (reponse.equalsIgnoreCase("oui")) {
                                                    Utilisateur utilisateur = recupererUtilisateur(bibliotheque, scanner);
                                                    if(utilisateur != null)bibliotheque.enregistrerEmpruntLivre(utilisateur, livre);
                                                }
                                            }
                                            break;
                                        case 5:
                                            retourAuMenuPrincipale = "oui";
                                            break;
                                }

                                if(retourAuMenuPrincipale == null){
                                    do {
                                        System.out.print("voulez vous choisir une autre fonctionnalité concernant la gestion des livres (oui/non):");
                                        retourAuMenu = scanner.next();
                                        nettoyerConsole();
                                    } while (!retourAuMenu.equalsIgnoreCase("oui") && !retourAuMenu.equalsIgnoreCase("non"));
                                }
                            }while(retourAuMenu.equalsIgnoreCase("oui"));    
                            break;

                        case 2:
                            do{
                                retourAuMenu = "";
                                do {
                                    System.out.println("+----------------------------------------------------------+");
                                    System.out.println("|                    GESTION DES EMPRUNTS                  |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("|1|                 ENREGISTRER LES EMPRUNTS               |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("|2|            ENREGISTRER LES RETOURS DE LIVRES           |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("|3|       AFFICHER LIVRES EMPRUNTES PAR UTILISATEUR        |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("|4|        LIMITATION DE NOMBRE DE LIVRES EMPRUNTER        |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("|5|                         QUITTER                        |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("Veuiller faire votre choix:");
                                    choix = scanner.nextInt();
                                    scanner.nextLine();
                                    nettoyerConsole();
                                } while (choix < 1 || choix > 5);

                                switch (choix) {
                                    case 1:
                                        if (bibliotheque.getListeLivres().isEmpty()) {
                                            System.out.println("il n'y a aucun livre dans la bibliothèque Veuillez les ajouter et Réessayer.");
                                            retourAuMenuPrincipale = "oui";
                                        }else if (bibliotheque.getListUtilisateurs().isEmpty()) {
                                            System.out.println("il n'y a aucun utilisateur dans la bibliothèque Veuillez les ajouter et Reessayer.");
                                            retourAuMenuPrincipale = "oui";
                                        }else{
                                            String reponse;
                                            do{
                                                gererAjoutEmprunt(bibliotheque);
                                                do{
                                                    System.out.println("voulez vous enregistrer un nouveau emprunts (oui/non):");
                                                    reponse = scanner.next();
                                                }while(!reponse.equalsIgnoreCase("oui") && !reponse.equalsIgnoreCase("non"));
                                                nettoyerConsole();
                                            }while (reponse.equalsIgnoreCase("oui"));
                                        }
                                        break;
                                    case 2:
                                        if (bibliotheque.getEmpruntsUtilisateurs().isEmpty()) {
                                            System.out.println("impossible d'enregistrer un retour car il n'y aucun emprunt en cours.");
                                            retourAuMenuPrincipale = "oui";
                                        }else{
                                                String reponse;
                                                do{
                                                    System.out.println("  enregistrer les retours des livres ");
                                                    gererRetourLivre(bibliotheque);
                                                    do{
                                                        System.out.println("voulez vous retourner un autre livre (oui/non):");
                                                        reponse = scanner.next();
                                                    }while(!reponse.equalsIgnoreCase("oui") && !reponse.equalsIgnoreCase("non"));
                                                 }while (reponse.equalsIgnoreCase("oui"));
                                        }
                                        break;
                                    case 3:
                                        if (bibliotheque.getEmpruntsUtilisateurs().isEmpty()) {
                                            System.out.println("désole aucun utilisateur n'a un emprunt en cours.");
                                            retourAuMenuPrincipale = "oui";
                                        }else{
                                                System.out.println(" AFFICHER LIVRES EMPRUNTES PAR UTILISATEUR");
                                                bibliotheque.afficherLivreUtilisateur();
                                        }
                                        break;
                                    case 4:
                                        System.out.println("LIMITATION DE NOMBRE DE LIVRES EMPRUNTER");
                                        System.out.println("La limite actuelle est de "+bibliotheque.getMaxEmprunt()+" livre a la fois");
                                        System.out.println("Veuillez saisir la nouvelle limite");
                                        bibliotheque.modifierMaxEmprunt(scanner.nextInt());
                                        break;
                                    case 5:
                                        retourAuMenuPrincipale = "oui";
                                        break;
                                }

                                if(retourAuMenuPrincipale == null){
                                    do {
                                        System.out.print("voulez vous choisir une autre fonctionnalité concernant la gestion des emprunts (oui/non):");
                                        retourAuMenu = scanner.next();
                                        nettoyerConsole();
                                    } while (!retourAuMenu.equalsIgnoreCase("oui") && !retourAuMenu.equalsIgnoreCase("non"));
                                }

                            }while(retourAuMenu.equalsIgnoreCase("oui"));  
                            break;
                        case 3:
                            do{
                                retourAuMenu = "";
                                do {
                                    System.out.println("+----------------------------------------------------------+");
                                    System.out.println("|                   GESTION DES UTILISATEURS               |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("|1|                        ENREGISTRER                     |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("|2|      VERIFICATION D'ELIGIBILITE DES UTILISATEURS       |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("|3|                         QUITTER                        |");
                                    System.out.println("+-+--------------------------------------------------------+");
                                    System.out.println("Veuiller faire votre choix:");
                                    choix = scanner.nextInt();
                                    scanner.nextLine();
                                    nettoyerConsole();
                                } while (choix < 1 || choix > 3);

                                switch (choix) {
                                    case 1:
                                        bibliotheque.enregistrerUtilisateur();
                                        break;
                                    case 2:
                                        if (bibliotheque.getListUtilisateurs().isEmpty()) {
                                            System.out.println("Il n y a aucun utilisateur dans la bibliothèque Veuillez les ajouter et Réessayer.");
                                            retourAuMenuPrincipale = "oui";
                                        }else{
                                            System.out.println("Vérification de l'éligibilite");
                                            System.err.println("Pour quel utilisateur souhaitez-vous le vérifier");
                                            Utilisateur utilisateur = recupererUtilisateur(bibliotheque, scanner);
                                            if (bibliotheque.verifierEligibilite(utilisateur)) {
                                                System.out.println("Cet Utilisateur est éligible car il est à jour sur les cotisations");
                                            }
                                        }
                                        break;
                                    case 3:
                                        retourAuMenuPrincipale = "oui";
                                        break;
                                }

                                if(retourAuMenuPrincipale == null){
                                    do {
                                        System.out.println("voulez vous choisir une autre fonctionnalité concernant la gestion des utilisateurs (oui/non):");
                                        retourAuMenu = scanner.next();
                                        nettoyerConsole();
                                    } while (!retourAuMenu.equalsIgnoreCase("oui") && !retourAuMenu.equalsIgnoreCase("non"));
                                }
                                        
                            }while(retourAuMenu.equalsIgnoreCase("oui"));  
                            break;
                        case 4:
                            retourAuMenuPrincipale = "non";
                            break;
                    }

                } while (choix < 1 || choix > 4);

                if(retourAuMenuPrincipale == null){
                    do {
                        System.out.println("voulez vous choisir une autre fonctionnalité dans le menu de gestion (oui/non):");
                        retourAuMenuPrincipale = scanner.next();
                        nettoyerConsole();                    
                    } while (!retourAuMenuPrincipale.equalsIgnoreCase("oui") && !retourAuMenuPrincipale.equalsIgnoreCase("non"));
                }   
            }while(retourAuMenuPrincipale.equalsIgnoreCase("oui"));
            System.out.println("Merci et au revoir !");
            
    }
    
}
