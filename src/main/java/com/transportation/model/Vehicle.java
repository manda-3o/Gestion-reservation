public class Vehicle {
    private int idvoit;
    private String design;
    private String type; // ENUM: simple, premium, VIP
    private int nbrplace;
    private double frais;

    public Vehicle() {
    }

    public Vehicle(int idvoit, String design, String type, int nbrplace, double frais) {
        this.idvoit = idvoit;
        this.design = design;
        this.type = type;
        this.nbrplace = nbrplace;
        this.frais = frais;
    }

    public int getIdvoit() {
        return idvoit;
    }

    public void setIdvoit(int idvoit) {
        this.idvoit = idvoit;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public double getFrais() {
        return frais;
    }

    public void setFrais(double frais) {
        this.frais = frais;
    }
}