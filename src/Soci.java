import java.io.Serializable;
import java.time.LocalDate;


public class Soci implements Serializable, Comparable<Soci> {
    private String alias;
    private String nom;
    private LocalDate dataIngres;

    public Soci(String alias, String nom, LocalDate dataIngres) throws Exception {
        if (alias == null || nom == null || dataIngres == null) {
            throw new Exception("Els camps no poden ser nuls.");
        }

        this.alias = alias;
        this.nom = nom;
        this.dataIngres = dataIngres;
    }

    public String getAlias() {
        return alias;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDataIngres() {
        return dataIngres;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDataIngres(LocalDate  dataIngres) throws Exception {
        this.dataIngres = dataIngres;
    }

    @Override
    public int compareTo(Soci other) {
        return this.dataIngres.compareTo(other.dataIngres);
    }
}
