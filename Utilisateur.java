import java.util.ArrayList;
import java.util.Scanner;
public class Utilisateur {
// la declaration des attributs  
    private String nom;
    private int numeroIdentification;
    private ArrayList<Livre> livresEmpruntes;
    private boolean cotisationAJour;
// c'est un constructeur qui permet d'initialiser les attributs
    public Utilisateur(String nom, int numeroIdentification) {
        this.nom = nom;
        this.numeroIdentification = numeroIdentification;
        this.livresEmpruntes = new ArrayList<>();
        this.cotisationAJour = false;
    }
//  // l'initialisation de Getther et Setter pour accéder et modifier les attributs
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumeroIdentification() {
        return numeroIdentification;
    }

    public void setNumeroIdentification(int numeroIdentification) {
        this.numeroIdentification = numeroIdentification;
    }


    public ArrayList<Livre> getLivresEmpruntes() {
        return livresEmpruntes;
    }

    public void setLivresEmpruntes(ArrayList<Livre> livresEmpruntes) {
        this.livresEmpruntes = livresEmpruntes;
    }
    public boolean isCotisationAJour() {
        return cotisationAJour;
    }

    public void setCotisationAJour(boolean cotisationAJour) {
        this.cotisationAJour = cotisationAJour;
    }
    
// cette méthode nous permet d'emprunter un livre
    public void emprunterLivre(Bibliotheque bibliotheque) {
        Livre livre = bibliotheque.rechercherLivre(); // cette méthode permet de rechercher un livre disponible dans la bibliotheque et elle retourne un livre, si un livre disponible est trouvé, sinon elle retourne null 
        if (livre != null) {
            livre = new Livre(livre);
            livre.setQuantite(1);
            bibliotheque.enregistrerEmpruntLivre(this, livre); // cette methode permet d'enregistrer l'emprunt du livre 
        }
       
    }
    
// cette méthode nous permet de retourner un livre qui a été emprunté 
    public void retournerLivre(Bibliotheque bibliotheque) {
        Scanner scanner = new Scanner(System.in);
        int numero;
        if (this.livresEmpruntes.isEmpty()) { // permet de verifier si l'utilisateur si l'utilisateur a emprunté des livres, si la liste est vide elle affiche un message a l'utilisateur "n'a aucun emprunt en cours" 
            System.out.println(this.nom+" n'a aucun emprunt en cours");
            return;
        }
        System.out.println("Veuillez indiquer le livre a retourner"); 
        this.afficherLivreEmpruntee();// cette methode permet d'afficcher les livres empruntés par l'utilisateur
        do {
            System.out.print("Numero du Livre: ");
            numero = scanner.nextInt();
        } while (numero > this.livresEmpruntes.size() || numero <=0);
        bibliotheque.enregistrerRetourLivre(this, this.livresEmpruntes.get(numero - 1));
    }
// cette méthode nous permet d'afficher les livres empruntés 
    public void afficherLivreEmpruntee(){
        if (this.livresEmpruntes.isEmpty()) {
            System.out.println(this.nom+" n'a aucun emprunt en cours");
            return;
        }
        int numero = 1;
        System.out.println("Livre Empruntee par "+this.nom);
        for(Livre livre : this.livresEmpruntes){
            System.out.printf("\t\t%d- %s\n",numero,livre);
            numero++;
        }
    }
// cette méthode nous permet de payer les cotisations (le montant à payer devrait être supperieur ou égale à 5000)
    public void payerCotisation(Scanner sc){
        int montant;
        do {
            System.out.println("Veuillez Saisir le montant que vous souhaitez (Plus de 5000)");
            montant = sc.nextInt();
        } while (montant < 5000);
        this.cotisationAJour = true;
    }
}
