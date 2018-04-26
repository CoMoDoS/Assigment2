package Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "spectacol")
@NamedQueries({
        @NamedQuery(name = "Spectacol.showAll", query = " FROM Spectacol as cas"),
})
public class Spectacol {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id;

    @Column(name = "gen")
    private String gen;
    @Column(name = "titlu")
    private String titlu;
    @Column(name = "regie")
    private String regie;
    @Column(name = "distributie")
    private String distributie;
    @Column(name = "data")
    private Date data;
    @Column(name = "nr_bilete")
    private int nrBilete;

    public Spectacol() {}

    public Spectacol(String gen, String titlu, String regie, String distributie, Date data, int nrBilete) {
        this.gen = gen;
        this.titlu = titlu;
        this.regie = regie;
        this.distributie = distributie;
        this.data = data;
        this.nrBilete = nrBilete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getRegie() {
        return regie;
    }

    public void setRegie(String regie) {
        this.regie = regie;
    }

    public String getDistributie() {
        return distributie;
    }

    public void setDistributie(String distributie) {
        this.distributie = distributie;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getNrBilete() {
        return nrBilete;
    }

    public void setNrBilete(int nrBilete) {
        this.nrBilete = nrBilete;
    }

    @Override
    public String toString() {
        return "Spectacol{" +
                "id=" + id +
                ", gen='" + gen + '\'' +
                ", titlu='" + titlu + '\'' +
                ", regie='" + regie + '\'' +
                ", distributie='" + distributie + '\'' +
                ", data=" + data +
                ", nrBilete=" + nrBilete +
                '}';
    }
}
