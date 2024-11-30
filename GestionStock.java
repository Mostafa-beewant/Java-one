import java.util.Scanner;

public class GestionStock {
    private static final int MAX_PRODUCTS = 100;
    private static Produit[] produits = new Produit[MAX_PRODUCTS];
    private static int produitCount = 0;

    // Print menu
    public static void printMenu() {
        System.out.println("\n--- Gestion de Stock ---");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher la liste des produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("7. Quitter");
    }

    // Add product
    public static void ajouterProduit(Produit produit) {
        if (produitCount >= MAX_PRODUCTS) {
            System.out.println("Erreur : Le stock est plein.");
            return;
        }
        for (int i = 0; i < produitCount; i++) {
            if (produits[i].getCode() == produit.getCode()) {
                System.out.println("Erreur : Le code produit doit être unique.");
                return;
            }
        }
        produits[produitCount++] = produit;
        System.out.println("Produit ajouté avec succès.");
    }

    // Modify product
    public static void modifierProduit(int code, String nouveauNom, int nouvelleQuantite, double nouveauPrix) {
        for (int i = 0; i < produitCount; i++) {
            if (produits[i].getCode() == code) {
                produits[i].setNom(nouveauNom);
                produits[i].setQuantite(nouvelleQuantite);
                produits[i].setPrix(nouveauPrix);
                System.out.println("Produit modifié avec succès.");
                return;
            }
        }
        System.out.println("Erreur : Produit non trouvé.");
    }

    // Delete product
    public static void supprimerProduit(int code) {
        for (int i = 0; i < produitCount; i++) {
            if (produits[i].getCode() == code) {
                produits[i] = produits[produitCount - 1];
                produits[--produitCount] = null;
                System.out.println("Produit supprimé avec succès.");
                return;
            }
        }
        System.out.println("Erreur : Produit non trouvé.");
    }

    // Display all products
    public static void afficherProduits() {
        if (produitCount == 0) {
            System.out.println("Aucun produit en stock.");
            return;
        }
        for (int i = 0; i < produitCount; i++) {
            System.out.println(produits[i]);
        }
    }

    // Search product by name
    public static void rechercherProduit(String nom) {
        for (int i = 0; i < produitCount; i++) {
            if (produits[i].getNom().equalsIgnoreCase(nom)) {
                System.out.println(produits[i]);
                return;
            }
        }
        System.out.println("Erreur : Produit non trouvé.");
    }

    // Calculate total stock value
    public static void calculerValeurStock() {
        double total = 0;
        for (int i = 0; i < produitCount; i++) {
            total += produits[i].calculerValeurTotale();
        }
        System.out.println("Valeur totale du stock : " + total);
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    System.out.print("Code : ");
                    int code = scanner.nextInt();
                    scanner.nextLine(); // Clear the buffer
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Quantité : ");
                    int quantite = scanner.nextInt();
                    System.out.print("Prix : ");
                    double prix = scanner.nextDouble();
                    ajouterProduit(new Produit(code, nom, quantite, prix));
                    break;
                case 2:
                    System.out.print("Code du produit à modifier : ");
                    int modCode = scanner.nextInt();
                    scanner.nextLine(); // Clear the buffer
                    System.out.print("Nouveau nom : ");
                    String nouveauNom = scanner.nextLine();
                    System.out.print("Nouvelle quantité : ");
                    int nouvelleQuantite = scanner.nextInt();
                    System.out.print("Nouveau prix : ");
                    double nouveauPrix = scanner.nextDouble();
                    modifierProduit(modCode, nouveauNom, nouvelleQuantite, nouveauPrix);
                    break;
                case 3:
                    System.out.print("Code du produit à supprimer : ");
                    int delCode = scanner.nextInt();
                    supprimerProduit(delCode);
                    break;
                case 4:
                    afficherProduits();
                    break;
                case 5:
                    System.out.print("Nom du produit à rechercher : ");
                    scanner.nextLine(); // Clear the buffer
                    String searchNom = scanner.nextLine();
                    rechercherProduit(searchNom);
                    break;
                case 6:
                    calculerValeurStock();
                    break;
                case 7:
                    System.out.println("Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide.");
            }
        }
    }
}
