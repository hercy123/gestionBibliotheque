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
    //pour l'ajout des livres nous avons saisi les informations necessaires du livre 
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
            boolean exist = false;//cette variable permet de verifier si un livre a deja ete ajouter dans la collection
            for (Livre livre:listeLivres){
                if (livre.equals(new Livre(titre, auteur, ISBN, anneePublication, quantite))) {//la methode equals des livres verifie fait la verification sur le titre et l'auteur
                    livre.setQuantite(livre.getQuantite() + quantite);//si le livre il existe on ajoute la quantite saisi au livre qui existe deja 
                    exist = true;//on met a jour pour dire que le livre saisi exite 
                    break;
                }
            }

            if (!exist) {
                this.listeLivres.add(new Livre(titre, auteur, ISBN, anneePublication, quantite));//si le livre n'existe pas on l'ajoute dans la collection
                System.out.println("*-------------------------------------------------------*\nLe livre a été ajouté avec succès");
            }

            System.out.print("voulez-vous ajouté un autre livre (Oui/Non) ???");//ici on boucle pour soit ajouter plusieurs livre a la fois 
            reponse = scanner.next();
            scanner.nextLine();
            Main.nettoyerConsole();//cette methode permet de nettoyer la console (elle s'adapte au SE dela machine ou le code est excute)
        }while(reponse.equalsIgnoreCase("oui"));
    }
    //pour la suppression on affiche les livres disponibles si la bibliotheques n'est pas vide on demande de choisir le livre a supprimer et on le supprime 
    public void supprimerLivre(){
        
        if(this.listeLivres.isEmpty())
        {
            System.out.println("il n'y a pas de livre dans la bibliothèque");
            return;//on sort de la fonction en affichant le message s'il n'y a aucun livre dans la bibliothèque 
        }

        Scanner scanner = new Scanner(System.in);
        String reponse;
        int number;

        do{
            System.out.println("*-------------------------------------------------------*");
            System.out.println("|        suppréssion d'un livre de la bibliothèque      |");
            System.out.println("*-------------------------------------------------------*");
            afficherLivres();//Ici on affiche la liste des livres 
            do {
                System.out.println("saisissez le numéro du livre a supprimer:");
                number = scanner.nextInt();
                scanner.nextLine();
            } while (number > this.listeLivres.size() || number <= 0);//on fait un controle pour s'assurer que l'utilisateur ne saisisse pas n'importe quelle valeur 

            if (this.listeLivres.remove(this.listeLivres.get(number - 1))) {// on supprime le livre dans la collection avec la methode remove de la classe arrayList pour supprimer le livre de la collection
                System.out.println("Suppression Effectuée avec Succès!!!");//en faisant l'appel de la methode remove sur la liste des livres dans le if , elle renvoit true si la suppresion est effectue ce qui nous permet d'afficher le message pour le notifier 
            }

            System.out.print("voulez vous supprimé un autre livre (Oui/Non) ???");//on boucle pour supprimer un autre livre au besoin
            reponse = scanner.next();
            Main.nettoyerConsole();
        }while(reponse.equalsIgnoreCase("oui"));
    }
    //pour cette methode omn recherche selon le titre , l'auteur ou l'isbn . On renvoit le livre s'il existe si non null
    public Livre rechercherLivre() {
        if (this.listeLivres.isEmpty()){
            System.out.println("Il n'y a pas de livre dans la bibliothèque");//on s'assure que la liste des livres n'est pas vide pour continuer
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
        ArrayList<Livre> livreRechercher=new ArrayList<>();//ici on cree une collection qui va plus tart acceuillir la liste des livres que nous allons trouver car possible d'avoir plusieurs livres avec le
        boolean status = false;//meme titre ou le meme auteur mais pas les deux a la fois dans notre systeme
        int essai = 0;
            do {
                System.out.print("Veuillez choisir une option : ");
                int choix = scanner.nextInt();//on recupere le choix de l'utilisateur de faire la recherche soit par titre 1, auteur 2 , isbn 3 
                scanner.nextLine();
                switch (choix){
                    case 1:// Selon le titre 
                        System.out.print("Saisir le titre du livre : ");
                        String titre = scanner.nextLine();//on recupere le titre 
    
                        for (Livre livre : this.listeLivres) {
                            if (titre.equalsIgnoreCase(livre.getTitre())) {
                                livreRechercher.add(livre);// si on trouve un livre avec le titre saisi on l'ajoute dans la collection des livres recherche
                            }
                        }

                        if (livreRechercher.isEmpty()) {
                            System.out.println("Le livre ayant pour titre '" + titre + "' n'a pas été trouvé");//si aucun livre n'est trouve on affiche ce message et nous ferons le retour plus tard 
                        }
                        status = true;
                        break;
                    case 2://selon l'auteur 
                        System.out.print("Saisir l'auteur du livre : ");
                        String auteur = scanner.nextLine();
                        for (Livre livre : this.listeLivres) {
                            if (auteur.equalsIgnoreCase(livre.getAuteur())) {
                                livreRechercher.add(livre);//la meme logique que le case1 
                            }
                        }
                        if (livreRechercher.isEmpty()) {
                            System.out.println("Le livre ayant pour auteur '" + auteur + "' n'a pas été trouvé");
                        }
                        status = true;
                        break;
                    case 3://selon l'ISBN
                        System.out.print("Saisir l'ISBN du livre : ");
                        String ISBN = scanner.nextLine();//On recupere
    
                        for (Livre livre : this.listeLivres) {
                            if (ISBN.equalsIgnoreCase(livre.getISBN())) {// Si le ISBN existe dans la liste des livres
                                return livre;//On retourne directement la premiere occurence car l'ISBN est Unique dans notre systeme
                            }
                        }
    
                        System.out.println("Le livre ayant pour ISBN '" + ISBN + "' n'a pas été trouvé");
                        status = true;
                        break;
                    default://Si l'utisateur se trompe sur les choix on lui permet de reessayer a trois reprises 
                        essai++;
                        if (essai < 3) {
                            System.out.println("Aucune des options n'a été choisie. Réessayer.");
                        } else {
                            System.out.println("Nombre d'éssais épuisé.");
                            return null;//s'il se trompe trois fois on retourne null en considerant que le livre n'a pas ete trouve 
                        }
                }
            } while (!status && essai < 3);

            if(!livreRechercher.isEmpty()){//si la liste des livres rechercher n'est pas vide soit on en a trouve un ou plusieurs selon le titre ou l'auteur 
                int index = 1;
                System.out.println("Veuillez choisir le livre que vous voulez ");
                for (Livre livre :livreRechercher) {
                    System.out.println(index+"- Titre: "+livre.getTitre()+" Auteur :"+livre.getAuteur());//On affiche la liste des livres trouve pour demader a l'utilisateur de preciser son choix 
                    index++;
                }
                int numero;
                do {
                    System.out.print("Numéro du Livre: ");
                    numero = scanner.nextInt();//cette Variable permet de preciser le choix de l'utilisateur afin de garder un seul livre
                } while (numero > this.listeLivres.size() || numero <= 0);
                return  livreRechercher.get(numero - 1);//on retourne le livre a l'index numero -1 car la numérotation commence a 1 
            }
        return null;//Quand on arrive c'est qu'aucun livre recherche n'a ete trouve dans la bibliothèque dans ce cas on retourne null
    }
    
    //cette methode permet d'effectuer un empunt pour un seul utilisateur et un seul livre
    public void enregistrerEmpruntLivre(Utilisateur utilisateur,Livre livreEmpruntee)
    {
        if (this.listeLivres.size() == 0) {//S'il n'ya pas de livre impossible de faire donc on sort
            System.out.println("Impossible de faire un emprunt car y'a aucun livre dans la bibliotheque");
            return;
        }

        if (this.listeUtilisateurs.size() == 0) {//pareil s'il n'y a pas d'utilisateur impossible de faire un emprunt on sort aussi
            System.out.println("Impossible de faire un emprunt car y'a aucun utilisateur dans la bibliotheque");
            return;
        }

        if (livreEmpruntee.getQuantite() == 0) {//Egalement si le livre a emprunter n'est plus en stock on peut pas le preter donc on sort
            System.out.println("Désolé mais ce livre n'est pas disponible");
            return;
        }

        if (!verifierEligibilite(utilisateur)) //Si l'utilisateur n'est pas éligible il peut pas emprunter
                return;
        if (utilisateur.getLivresEmpruntes().size() == this.maxEmprunt) {//Si l'utilisateur a atteint le nombre maximum d'emprunt autorise par la bibliothèque il ne peut plus emprunter
            System.out.println("Désolé mais Vous ne pouvez plus emprunter de livre car vous avez atteint le maximum de livre que vous pouvez emprunter à la fois\nVeuillez en rendre certain pour pouvoir à nouveau emprunter");
            return;
        }
        //Quand on arrive a ce niveau c'est que toutes les conditions pour faire un emprunt son reunit
        for (Livre livre:this.listeLivres){//On parcourt la liste des livres 
            if (livre.equals(livreEmpruntee)) {//si on trouve le livre que l'utilsateur veut emprunter 
                livre.setQuantite(livre.getQuantite() - 1);//on diminue sa quantité de moins 1
                break;//on sort de la boucle parceque le livre existe en un seul exemplaire
            }
        }
        ArrayList<Livre> listeLivre = utilisateur.getLivresEmpruntes();//on recupere la liste des livres emprnte par l'utilisateur 
        listeLivre.add(livreEmpruntee);//on y ajoute le nouveau livre a emprunter
        utilisateur.setLivresEmpruntes(listeLivre);//on met a jour la liste des livres emprrunter par l'utilisateur 
        //On met a jour la liste des emprunts des utilisateur concernant l'utilisateur courant  
        empruntsUtilisateurs.put(utilisateur, utilisateur.getLivresEmpruntes());// la methode put de la classes hashmap cree un  nouvel entree cle(utilisateur) valeur (liste des livres) si la cle (utilisateur) n'existe pas si non il met juste a jour la valeur associe a la cle

        System.out.println("emprunt enregistré avec succès");
    }
    //Cette methode permet d'enregistrer le retour d'un livre par un utilisateur (On s'est assuree de l'appeler que si un l'utilsateur a des emprunt en cours)
    public void enregistrerRetourLivre(Utilisateur utilisateur, Livre livreRetourner)
    {
        ArrayList<Livre> listeLivre = utilisateur.getLivresEmpruntes();//On recupere les livres empruntee par l'utilisateur
        for (Livre livre:this.listeLivres){//on les parcours pour trouver le livre a retourner
            if (livre.equals(livreRetourner)) {
                livre.setQuantite(livre.getQuantite() + 1);
                break;
            }
        }
        listeLivre.remove(livreRetourner);//On supprime le livre retourner des livres emprunter par l'utilisateur
        utilisateur.setLivresEmpruntes(listeLivre);//on met a jour la liste des livres empruntee par lutilisateir avec la nouvelle liste
        empruntsUtilisateurs.put(utilisateur, utilisateur.getLivresEmpruntes());//on met a jour les emprunts de l'utilisateur

        System.out.println("retour enregistré avec succès");
    }
    //cette methode permet de modifier un ou plusieurs livres 
    public void modifierLivre()
    {
        if(this.listeLivres.isEmpty())//Si la liste des livres de la bibliotheques est vide on sort 
        {
            System.out.println("il n'y a pas de livre dans la bibliothèque");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int number;

        do {
            afficherLivres();//Cette methode permet d'afficher tous les livres de la bibliotheque
            System.out.print("veuillez saisir le numéro du livre à modifier: ");
            number = scanner.nextInt();//Selon les livres afficher on recupere le numero du livre a modifier 
            Main.nettoyerConsole();
        } while (number > this.listeLivres.size() || number <= 0);//On s'assure que l'utilisateur saisisse un nombre se trouvant dans le bon intervalle 
        //Ce Menu permet de designer quel informations modifier 
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
        switch (choix) {//On modifie les informaion du livre en passant par leur mutateurs directements(setters)
            case 1://on modife le titre
                System.out.print("saisir le nouveau titre:");
                this.listeLivres.get(number - 1).setTitre(scanner.nextLine());
                break;
            case 2://on modifie l'auteur
                System.out.print("saisir le nouveau auteur:");
                this.listeLivres.get(number - 1).setAuteur(scanner.nextLine());
                break;
            case 3://On modifie l'annee de publication
                System.out.print("saisir l'année de publication:");
                this.listeLivres.get(number - 1).setAnneePublication(scanner.nextInt());
                break;
            default:
                break;
        }
        System.out.println("\n\nmodification éffectué avec succès");
    }
    //Cette methode permet de verifier si l'utilisateur est a jour sur ces cotisations si non lui proposer de les payer
    public boolean verifierEligibilite(Utilisateur utilisateur){
        Scanner scanner = new Scanner(System.in);
        if (!utilisateur.isCotisationAJour()) {//Si l'utilisateur n'est pas a jour
            System.out.println("Désole Mais Vous n'etes pas à jour sur vos cotisations");
            
            String reponse;
            do {
                System.out.print("Voulez-vous payer??");
                reponse = scanner.next();
            } while (!reponse.equalsIgnoreCase("oui") && !reponse.equalsIgnoreCase("non"));//On controle la reponse
            if (reponse.equalsIgnoreCase("oui")) {//S'il veut payer 
                utilisateur.payerCotisation(scanner); //On appelle sa methode de payement pour lui permettre de payer (On y met a jour l'etat de sa cotisation) 
            }
            else{
                System.out.println("D'accord Mais vous n'allez pas pouvoir Emprunter de Livre");
            }
        }
        return utilisateur.isCotisationAJour();//On renvoit l'etat de sa cotisation
    }
    //Cette permet de parcourir et d'afficher tous les livres de la bibliotheque
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
        System.out.println("Le nombre total de livre est "+listeLivres.size());//Ceci est le nombre de livre distinct sans tenir compte du nombre d'exemplaire de chaque 
        int livreEmpruntee = 0;
        for (Utilisateur utilisateur : empruntsUtilisateurs.keySet()) {//on parcourt les emprunts des utilisateurs et on ajoute le nombre d'emprunt de chacun
            ArrayList<Livre> livresEmpruntes = empruntsUtilisateurs.get(utilisateur);
            livreEmpruntee += livresEmpruntes.size();
        }
        
        System.err.println("Le nombre de livre Emprunté est "+livreEmpruntee);//Ceci est le nombre de d'exemplaire de livre emprunte par tous les utilisateurs 
    }
    //Cette methode permet d'entregistrer un ou plusieurs utilisateurs 
    public void enregistrerUtilisateur(){

        Scanner scanner = new Scanner(System.in);
        String reponse;
        do {
            //On saisit les information de l'utilisateur
            System.out.println("*-------------------------------------------------------*");
            System.out.println("|        enregistrement d'un utilisateur                |");
            System.out.println("*-------------------------------------------------------*");
            System.out.println("Veuillez Saisir le nom de l'utilisateur");
            String nom = scanner.nextLine();
            System.out.println("Veuillez Saisir le numéro de l'utilisateur");
            int numero = scanner.nextInt();
            Utilisateur user = new Utilisateur(nom, numero);//On cree un nouvelle utilisateur 
            this.listeUtilisateurs.add(user);//On l'ajoute a la liste des utilisateurs de la bibliotheque 

            System.out.println("Voulez-vous effectuer le paiement des cotisations(oui/non)");
            reponse = scanner.next();
            if (reponse.equalsIgnoreCase("oui")) {//Si l'utilisateur est pret il paie directement ses cotisations pour pouvoir emprunter des livres plus tard 
                user.payerCotisation(scanner);
            }

            System.out.println("Voulez-vous enregistrer un autre utilisateur(oui/non)");
            reponse = scanner.next();//on propose d'ajouter un nouvelle utilisateur 
            scanner.nextLine();
            Main.nettoyerConsole();//on nettoie la console 
        } while (reponse.equalsIgnoreCase("oui"));

    }
    //Cette methode permet d'afficher les utilisateurs de la biblioque
    public void afficherUtilisateur(){
        if(this.listeUtilisateurs.isEmpty())//On s'assure que la liste des utilisateurs de la bibliotheque n'est pas vide 
        {
            System.out.println("il n'y a pas d'utilisateur enregistrer dans le système de gestion de la bibliotheque");
            return;
        }
        int numero = 1;
        System.out.println("*-------------------------------------------------------*");
        System.out.println("|        utilisateur de la bibliothèque                 |");
        System.out.println("*-------------------------------------------------------*");
        for(Utilisateur user : this.listeUtilisateurs){//on parcourt la liste des utilisateurs et on l'affiche
            System.out.println(numero+"-Nom: "+user.getNom()+" Numero: "+user.getNumeroIdentification());
            System.out.println("-------------------------------------------------------");
            numero++;
        }
    }
    //Cette methode permet de modifier le maximum qu'un utilisateur puisse emprunter a la fois dans une bibliotheque 
    public void modifierMaxEmprunt(int max){
        if (max <= 0) {//on s'assure que le maximum soit logique (superieur a 0)
            System.out.println("Erreur!!! " + max + " est trop petit pour etre maximum de livre qu'un utilisateur puisse emprunter");
            return;
        }
        this.maxEmprunt = max;
    }
    //Cette methode d'afficher les utilisateurs et leurs emprunt en cours
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
