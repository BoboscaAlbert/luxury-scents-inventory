public class Parfum {
    private String brand;
    private String nume;
    private double pret;
    private int stoc;
    private String concentratie;
    private String categorie; // Nisa, Designer, Arabesc

    public Parfum(String brand, String nume, double pret, int stoc, String concentratie, String categorie) {
        this.brand = brand;
        this.nume = nume;
        this.pret = pret;
        this.stoc = stoc;
        this.concentratie = concentratie;
        this.categorie = categorie;
    }

    public String getBrand() { return brand; }
    public String getNume() { return nume; }
    public double getPret() { return pret; }
    public int getStoc() { return stoc; }
    public String getCategorie() { return categorie; }
    public void setStoc(int stoc) { this.stoc = stoc; }

    @Override
    public String toString() {
        String status = (stoc > 0) ? String.valueOf(stoc) : "OUT OF STOCK";
        return String.format("| %-12s | %-18s | %-10s | %-6s | %8.2f RON | Stoc: %-12s |",
                brand, nume, categorie, concentratie, pret, status);
    }

    public String toFileFormat() {
        return brand + "," + nume + "," + pret + "," + stoc + "," + concentratie + "," + categorie;
    }
}