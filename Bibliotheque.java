import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Bibliotheque{

    private ArrayList<Utilisateur> listeUtilisateurs;
    private ArrayList<Livre> listeLivres;
    private HashMap<Utilisateur,ArrayList<Livre>> empruntsUtilisateurs;
    private int maxEmprunt;

    public int getMaxEmprunt() {
        return maxEmprunt;
    }
    

    public ArrayList<Utilisateur> getListUtilisateurs() {
        return listeUtilisateurs;
    }

    public ArrayList<Livre> getListeLivres() {
        return listeLivres;
    }
    
    public HashMap<Utilisateur, ArrayList<Livre>> getEmpruntsUtilisateurs() {
        return empruntsUtilisateurs;
    }

    public Bibliotheque()
    {
        this.listeUtilisateurs = new ArrayList<>();
        this.listeLivres = new ArrayList<>();
        this.empruntsUtilisateurs = new HashMap<>();
        this.maxEmprunt = 5; 
    }

    private String saisirISBN(Scanner scanner){
        boolean existe;
        int nbrEssaie = 1;
        String isbn;
        do{
            existe = false;
            if (nbrEssaie > 1) {
                System.out.println("Cet ISBN existe deja veuillez saisir un autre");
            }
            isbn = scanner.next();
            for (Livre livre:listeLivres){
                if (isbn.equals(livre.getISBN())) {
                    existe = true;
                    break;
                }
            }
            nbrEssaie++;
        }while (existe);
        return isbn;
    }

    public void ajouterLivre(){
        Scanner scanner = new Scanner(System.in);
        String reponse;
        do{
            System.out.println("*-------------------------------------------------------*");
            System.out.println("|             ajout d'un livre à la bibliothèque        |");
            System.out.println("*-------------------------------------------------------*");
            System.out.println("saisir le titre du livre:");
            String titre = scanner.nextLine();
            System.out.println("saisir l'auteur du livre:");
            String auteur = scanner.nextLine();
            System.out.println("saisir l'ISBN du livre:");
            String ISBN = saisirISBN(scanner);
            
            scanner.nextLine();
            System.out.println("saisir l'année de publication:");
            int anneePublication = scanner.nextInt();
            System.out.println("saisir la quantite:");
            int quantite = scanner.nextInt();
            scanner.nextLine();
            boolean exist = false;
            for (Livre livre:listeLivres){
                if (livre.equals(new Livre(titre, auteur, ISBN, anneePublication, quantite))) {
                    livre.setQuantite(livre.getQuantite() + quantite);
                    exist = true;
                    break;
                }
            }

            if (!exist) {
                this.listeLivres.add(new Livre(titre, auteur, ISBN, anneePublication, quantite));
                System.out.println("*-------------------------------------------------------*\nLe livre a été ajouté avec succès");
            }

            System.out.print("voulez-vous ajouté un autre livre (Oui/Non) ???");
            reponse = scanner.next();
            scanner.nextLine();
            Main.nettoyerConsole();
        }while(reponse.equalsIgnoreCase("oui"));
    }
    
    public void supprimerLivre(){
        
        if(this.listeLivres.isEmpty())
        {
            System.out.println("il n'y a pas de livre dans la bibliothèque");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        String reponse;
        int number;

        do{
            System.out.println("*-------------------------------------------------------*");
            System.out.println("|        suppréssion d'un livre de la bibliothèque      |");
            System.out.println("*-------------------------------------------------------*");
            afficherLivres();
            do {
                System.out.println("saisissez le numéro du livre a supprimer:");
                number = scanner.nextInt();
                scanner.nextLine();
            } while (number > this.listeLivres.size() || number <= 0);

            if (this.listeLivres.remove(this.listeLivres.get(number - 1))) {
                System.out.println("Suppression Effectuée avec Succès!!!");
            }

            System.out.print("voulez vous supprimé un autre livre (Oui/Non) ???");
            reponse = scanner.next();
            Main.nettoyerConsole();
        }while(reponse.equalsIgnoreCase("oui"));
    }

    public Livre rechercherLivre() {
        if (this.listeLivres.isEmpty()){
            System.out.println("Il n'y a pas de livre dans la bibliothèque");
            return null;
        }
    
        System.out.println("*-------------------------------------------------------*");
        System.out.println("|         Rechercher un livre dans la bibliothèque      |");
        System.out.println("*-------------------------------------------------------*");
        System.out.println("|1- Selon son titre                                     |");
        System.out.println("|2- Selon son auteur                                    |");
        System.out.println("|3- Selon son ISBN                                      |");
        System.out.println("*-------------------------------------------------------*");
    
        Scanner scanner = new Scanner(System.in);
        ArrayList<Livre> livreRechercher=new ArrayList<>();
        boolean status = false;
        int essai = 0;
            do {
                System.out.print("Veuillez choisir une option : ");
                int choix = scanner.nextInt();
                scanner.nextLine();
                switch (choix) {
                    case 1:
                        System.out.print("Saisir le titre du livre : ");
                        String titre = scanner.nextLine();
    
                        for (Livre livre : this.listeLivres) {
                            if (titre.equalsIgnoreCase(livre.getTitre())) {
                                livreRechercher.add(livre);
                            }
                        }

                        if (livreRechercher.isEmpty()) {
                            System.out.println("Le livre ayant pour titre '" + titre + "' n'a pas été trouvé");
                        }
                        status = true;
                        break;
                    case 2:
                        System.out.print("Saisir l'auteur du livre : ");
                        String auteur = scanner.nextLine();
                        for (Livre livre : this.listeLivres) {
                            if (auteur.equalsIgnoreCase(livre.getAuteur())) {
                                livreRechercher.add(livre);
                            }
                        }
                        if (livreRechercher.isEmpty()) {
                            System.out.println("Le livre ayant pour auteur '" + auteur + "' n'a pas été trouvé");
                        }
                        status = true;
                        break;
                    case 3:
                        System.out.print("Saisir l'ISBN du livre : ");
                        String ISBN = scanner.nextLine();
    
                        for (Livre livre : this.listeLivres) {
                            if (ISBN.equalsIgnoreCase(livre.getISBN())) {
                                return livre;
                            }
                        }
    
                        System.out.println("Le livre ayant pour ISBN '" + ISBN + "' n'a pas été trouvé");
                        status = true;
                        break;
                    default:
                        essai++;
                        if (essai < 3) {
                            System.out.println("Aucune des options n'a été choisie. Réessayer.");
                        } else {
                            System.out.println("Nombre d'éssais épuisé.");
                            return null;
                        }
                }
            } while (!status && essai < 3);

            if(!livreRechercher.isEmpty()){
                int index = 1;
                System.out.println("Veuillez choisir le livre que vous voulez ");
                for (Livre livre :livreRechercher) {
                    System.out.println(index+"- Titre: "+livre.getTitre()+" Auteur :"+livre.getAuteur());
                    index++;
                }
                int numero;
                do {
                    System.out.print("Numéro du Livre: ");
                    numero = scanner.nextInt();
                } while (numero > this.listeLivres.size() || numero <= 0);
                return  livreRechercher.get(numero - 1);
            }
        return null;
    }
    

    public void enregistrerEmpruntLivre(Utilisateur utilisateur,Livre livreEmpruntee)
    {
        if (this.listeLivres.size() == 0) {
            System.out.println("Impossible de faire un emprunt car y'a aucun livre dans la bibliotheque");
            return;
        }

        if (this.listeUtilisateurs.size() == 0) {
            System.out.println("Impossible de faire un emprunt car y'a aucun utilisateur dans la bibliotheque");
            return;
        }

        if (livreEmpruntee.getQuantite() == 0) {
            System.out.println("Désolé mais ce livre n'est pas disponible");
            return;
        }

        if (!verifierEligibilite(utilisateur))
                return;
        if (utilisateur.getLivresEmpruntes().size() == this.maxEmprunt) {
            System.out.println("Désolé mais Vous ne pouvez plus emprunter de livre car vous avez atteint le maximum de livre que vous pouvez emprunter à la fois\nVeuillez en rendre certain pour pouvoir à nouveau emprunter");
            return;
        }
        
        for (Livre livre:this.listeLivres){
            if (livre.equals(livreEmpruntee)) {
                livre.setQuantite(livre.getQuantite() - 1);
                break;
            }
        }
        ArrayList<Livre> listeLivre = utilisateur.getLivresEmpruntes();
        listeLivre.add(livreEmpruntee);
        utilisateur.setLivresEmpruntes(listeLivre);

        empruntsUtilisateurs.put(utilisateur, utilisateur.getLivresEmpruntes());

        System.out.println("emprunt enregistré avec succès");
    }

    public void enregistrerRetourLivre(Utilisateur utilisateur, Livre livreRetourner)
    {
        ArrayList<Livre> listeLivre = utilisateur.getLivresEmpruntes();
        for (Livre livre:this.listeLivres){
            if (livre.equals(livreRetourner)) {
                livre.setQuantite(livre.getQuantite() + 1);
                break;
            }
        }
        listeLivre.remove(livreRetourner);
        utilisateur.setLivresEmpruntes(listeLivre);
        empruntsUtilisateurs.put(utilisateur, utilisateur.getLivresEmpruntes());

        System.out.println("retour enregistré avec succès");
    }
    
    public void modifierLivre()
    {
        if(this.listeLivres.isEmpty())
        {
            System.out.println("il n'y a pas de livre dans la bibliothèque");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int number;

        do {
            afficherLivres();
            System.out.print("veuillez saisir le numéro du livre à modifier: ");
            number = scanner.nextInt();
            Main.nettoyerConsole();
        } while (number > this.listeLivres.size() || number <= 0);

        System.out.println("*-------------------------------------------------------*");
        System.out.println("|    modifier info d'un livre de  la Bibliothèque       |");
        System.out.println("*-------------------------------------------------------*");
        System.out.println("| 1-titre                                               |");
        System.out.println("| 2-auteur                                              |");
        System.out.println("| 3-année de publication                                |");
        System.out.println("*-------------------------------------------------------*");
        int choix;
        do {
            System.out.print("saisir l'information a modifié:");
            choix = scanner.nextInt();
        } while(choix <= 0 || choix > 3);
        scanner.nextLine();
        switch (choix) {
            case 1:
                System.out.print("saisir le nouveau titre:");
                this.listeLivres.get(number - 1).setTitre(scanner.nextLine());
                break;
            case 2:
                System.out.print("saisir le nouveau auteur:");
                this.listeLivres.get(number - 1).setAuteur(scanner.nextLine());
                break;
            case 3:
                System.out.print("saisir l'année de publication:");
                this.listeLivres.get(number - 1).setAnneePublication(scanner.nextInt());
                break;
            default:
                break;
        }
        System.out.println("\n\nmodification éffectué avec succès");
    }
    public boolean verifierEligibilite(Utilisateur utilisateur){
        Scanner scanner = new Scanner(System.in);
        if (!utilisateur.isCotisationAJour()) {
            System.out.println("Désole Mais Vous n'etes pas à jour sur vos cotisations");
            
            String reponse;
            do {
                System.out.print("Voulez-vous payer??");
                reponse = scanner.next();
            } while (!reponse.equalsIgnoreCase("oui") && !reponse.equalsIgnoreCase("non"));
            if (reponse.equalsIgnoreCase("oui")) {
                utilisateur.payerCotisation(scanner);
            }
            else{
                System.out.println("D'accord Mais vous n'allez pas pouvoir Emprunter de Livre");
            }
        }
        return utilisateur.isCotisationAJour();
    }

    public void afficherLivres(){
        int numero = 1;
        System.out.println("*-------------------------------------------------------*");
        System.out.println("|        livre de la bibliothèque                       |");
        System.out.println("*-------------------------------------------------------*");
        for(Livre livre : this.listeLivres){
            System.out.println(numero+"-"+livre);
            System.out.println("-------------------------------------------------------");
            numero++;
        }
    }

    public void afficherStatistique(){
        System.out.println("Le nombre total de livre est "+listeLivres.size());
        int livreEmpruntee = 0;
        for (Utilisateur utilisateur : empruntsUtilisateurs.keySet()) {
            ArrayList<Livre> livresEmpruntes = empruntsUtilisateurs.get(utilisateur);
            livreEmpruntee += livresEmpruntes.size();
        }
        
        System.err.println("Le nombre de livre Emprunté est "+livreEmpruntee);
    }

    public void enregistrerUtilisateur(){

        Scanner scanner = new Scanner(System.in);
        String reponse;
        do {
            System.out.println("*-------------------------------------------------------*");
            System.out.println("|        enregistrement d'un utilisateur                |");
            System.out.println("*-------------------------------------------------------*");
            System.out.println("Veuillez Saisir le nom de l'utilisateur");
            String nom = scanner.nextLine();
            System.out.println("Veuillez Saisir le numéro de l'utilisateur");
            int numero = scanner.nextInt();
            Utilisateur user = new Utilisateur(nom, numero);
            this.listeUtilisateurs.add(user);

            System.out.println("Voulez-vous effectuer le paiement des cotisations(oui/non)");
            reponse = scanner.next();
            if (reponse.equalsIgnoreCase("oui")) {
                user.payerCotisation(scanner);
            }

            System.out.println("Voulez-vous enregistrer un autre utilisateur(oui/non)");
            reponse = scanner.next();
            scanner.nextLine();
            Main.nettoyerConsole();
        } while (reponse.equalsIgnoreCase("oui"));

    }

    public void afficherUtilisateur(){
        if(this.listeUtilisateurs.isEmpty())
        {
            System.out.println("il n'y a pas d'utilisateur enregistrer dans le système de gestion de la bibliotheque");
            return;
        }
        int numero = 1;
        System.out.println("*-------------------------------------------------------*");
        System.out.println("|        utilisateur de la bibliothèque                 |");
        System.out.println("*-------------------------------------------------------*");
        for(Utilisateur user : this.listeUtilisateurs){
            System.out.println(numero+"-Nom: "+user.getNom()+" Numero: "+user.getNumeroIdentification());
            System.out.println("-------------------------------------------------------");
            numero++;
        }
    }

    public void modifierMaxEmprunt(int max){
        if (max <= 0) {
            System.out.println("Erreur!!! " + max + " est trop petit pour etre maximum de livre qu'un utilisateur puisse emprunter");
            return;
        }
        this.maxEmprunt = max;
    }

    public void afficherLivreUtilisateur(){
        System.out.println("*-------------------------------------------------------*");
        System.out.println("|    utilisateur et  emprunt au sein la bibliothèque    |");
        System.out.println("*-------------------------------------------------------*");
        for(Utilisateur utilisateur : this.listeUtilisateurs){
            System.out.println(" Numéro: "+utilisateur.getNumeroIdentification()+" Nom"+ utilisateur.getNom());
            if (this.empruntsUtilisateurs.containsKey(utilisateur)) {
                utilisateur.afficherLivreEmpruntee();
            }
            System.out.println("-------------------------------------------------------");
        }
    }
}