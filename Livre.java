class Livre{
    // declaration des attributs
    private String titre,auteur,ISBN;
    private int anneePublication, quantite;
    // c'est un constructeur qui permet d'initialiser les attributs
    Livre(String titre,String auteur,String ISBN,int anneePublication, int quantite){
        this.titre = titre;
        this.auteur = auteur;
        this.ISBN = ISBN;
        this.anneePublication = anneePublication;
        this.quantite = quantite;
    }
    // 
    Livre(Livre l){
        this(l.titre,l.auteur,l.ISBN,l.anneePublication,l.quantite);
    }
     // l'initialisation de Getther et Setter pour acceder et modifier les attributs
    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    public void setAuteur(String auteur)
    {
        this.auteur = auteur;
    }

    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }

    public void setAnneePublication(int anneePublication)
    {
        this.anneePublication = anneePublication;
        
    }
    public void setQuantite(int quantite)
    {
        this.quantite = quantite;
    }

    public String getTitre()
    {
        return this.titre;
    }

    public String getAuteur()
    {
        return this.auteur;
    }

    public String getISBN()
    {
        return this.ISBN;
    }

    public int getAnneePublication()
    {
        return this.anneePublication;
    }
    public int getQuantite()
    {
        return this.quantite;
    }

 // la methode toString qui s'occupe de l'affichage d'un livre avec tous les details
    public String toString() {
        return ("Livre{" +
                "titre='" + this.titre + '\'' +
                ", auteur='" + this.auteur + '\'' +
                ", ISBN='" + this.ISBN + '\'' +
                ", Quantite='" + this.quantite + '\'' +
                ", anneePublication=" + this.anneePublication +
                '}');
    }
    // c'est une methode qui permet de comparer si deux livres sont identiques ou pas (ccriteur de comparaison titre et auteur)
    public boolean equals(Livre l){
        return (this.titre.equalsIgnoreCase(l.titre) && this.auteur.equalsIgnoreCase(l.auteur));
    }
}
