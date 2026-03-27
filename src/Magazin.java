import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Magazin {
    private ArrayList<Parfum> inventar = new ArrayList<>();
    private final String FISIER = "inventar.txt";

    public Magazin() {
        incarcaDinFisier();
    }

    public void adauga(Parfum p) {
        inventar.add(p);
        salveazaInFisier();
    }

    public void afiseazaTot() {
        if (inventar.isEmpty()) {
            System.out.println("Magazinul este gol.");
            return;
        }
        imprimaHeader();
        for (Parfum p : inventar) System.out.println(p);
    }

    public void vindeParfum(String nume) {
        for (Parfum p : inventar) {
            if (p.getNume().equalsIgnoreCase(nume)) {
                if (p.getStoc() > 0) {
                    p.setStoc(p.getStoc() - 1);
                    System.out.println("Vanzare reusita pentru " + p.getNume() + "!");
                    salveazaInFisier();
                } else {
                    System.out.println("ALERTA: Produsul " + p.getNume() + " este OUT OF STOCK!");
                }
                return;
            }
        }
        System.out.println("Parfumul nu a fost gasit.");
    }

    public void stergeEpuizate() {
        int initialSize = inventar.size();
        inventar.removeIf(p -> p.getStoc() <= 0);
        if (inventar.size() < initialSize) {
            System.out.println("Produsele fara stoc au fost eliminate din catalog.");
            salveazaInFisier();
        } else {
            System.out.println("Nu exista produse out of stock de eliminat.");
        }
    }

    private void imprimaHeader() {
        System.out.println("\n" + "=".repeat(90));
        System.out.format("| %-12s | %-18s | %-10s | %-6s | %12s | %-12s |\n", "BRAND", "NUME", "CATEGORIE", "TIP", "PRET", "STOC");
        System.out.println("-".repeat(90));
    }

    private void salveazaInFisier() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FISIER))) {
            for (Parfum p : inventar) pw.println(p.toFileFormat());
        } catch (IOException e) {
            System.out.println("Eroare scriere fisier.");
        }
    }

    private void incarcaDinFisier() {
        File f = new File(FISIER);
        if (!f.exists()) return;
        try (Scanner fs = new Scanner(f)) {
            while (fs.hasNextLine()) {
                String linie = fs.nextLine();
                if (linie.trim().isEmpty()) continue;
                String[] d = linie.split(",");
                if (d.length == 6) {
                    inventar.add(new Parfum(d[0], d[1], Double.parseDouble(d[2]), Integer.parseInt(d[3]), d[4], d[5]));
                }
            }
        } catch (Exception e) {
            System.out.println("Eroare la incarcarea datelor.");
        }
    }
}