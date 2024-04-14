class Livre{
    
    private String titre,auteur,ISBN;
    private int anneePublication, quantite;
    
    Livre(String titre,String auteur,String ISBN,int anneePublication, int quantite){
        this.titre = titre;
        this.auteur = auteur;
        this.ISBN = ISBN;
        this.anneePublication = anneePublication;
        this.quantite = quantite;
    }
    
    Livre(Livre l){
        this(l.titre,l.auteur,l.ISBN,l.anneePublication,l.quantite);
    }

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

    public String toString() {
        return ("Livre{" +
                "titre='" + this.titre + '\'' +
                ", auteur='" + this.auteur + '\'' +
                ", ISBN='" + this.ISBN + '\'' +
                ", Quantite='" + this.quantite + '\'' +
                ", anneePublication=" + this.anneePublication +
                '}');
    }

    public boolean equals(Livre l){
        return (this.titre.equalsIgnoreCase(l.titre) && this.auteur.equalsIgnoreCase(l.auteur));
    }
}