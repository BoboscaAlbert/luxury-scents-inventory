import java.util.Scanner;

public class Main {
    private static final String USER_ADMIN = "admin";
    private static final String PASS_ADMIN = "scent123";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- ACCES SISTEM LUXURY SCENTS ---");
        System.out.print("Utilizator: "); String user = sc.nextLine();
        System.out.print("Parola: "); String pass = sc.nextLine();

        if (!user.equals(USER_ADMIN) || !pass.equals(PASS_ADMIN)) {
            System.out.println("Acces respins! Date incorecte.");
            return;
        }

        System.out.println("\nAcces permis! Bine ai venit, " + USER_ADMIN);
        Magazin shop = new Magazin();

        while (true) {
            System.out.println("\nMENU: 1.Catalog | 2.Adauga | 3.Vinde | 4.Sterge Epuizate | 5.Iesire");
            System.out.print("Comanda: ");
            String opt = sc.nextLine();

            if (opt.equals("5")) break;

            switch (opt) {
                case "1":
                    shop.afiseazaTot();
                    break;
                case "2":
                    try {
                        System.out.print("Brand: "); String b = sc.nextLine();
                        System.out.print("Nume: "); String n = sc.nextLine();
                        System.out.print("Pret: "); double p = Double.parseDouble(sc.nextLine());
                        System.out.print("Stoc: "); int s = Integer.parseInt(sc.nextLine());
                        System.out.print("Tip (EDP/EDT): "); String t = sc.nextLine();
                        System.out.print("Categorie (Nisa/Designer/Arabesc): "); String cat = sc.nextLine();
                        shop.adauga(new Parfum(b, n, p, s, t, cat));
                    } catch (Exception e) {
                        System.out.println("Date invalide! Reincearca.");
                    }
                    break;
                case "4":
                    shop.stergeEpuizate();
                    break;
                case "3":
                    System.out.print("Nume parfum de vandut: ");
                    shop.vindeParfum(sc.nextLine());
                    break;
                default:
                    System.out.println("Optiune invalida.");
            }
        }
        System.out.println("Sistem deconectat.");
        sc.close();
    }
}