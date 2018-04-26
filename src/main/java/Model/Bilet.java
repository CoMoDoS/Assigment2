package Model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
@Table(name = "bilet")
@NamedQueries({
        @NamedQuery(name = "Bilet.showAll", query = " FROM Bilet as cas"),

})
public class Bilet {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private int id;

    @Column(name = "rand")
    private int rand;

    @Column(name = "numar")
    private int numar;

    @Column(name = "spectacol_id")
    private int spectacol_id;

    public Bilet() {}

    public Bilet(int rand, int numar, int spectacol_id) {

        this.rand = rand;
        this.numar = numar;
        this.spectacol_id = spectacol_id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = rand;
    }

    public int getNumar() {
        return numar;
    }

    public void setNumar(int numar) {
        this.numar = numar;
    }

    public int getSpectacol_id() {
        return spectacol_id;
    }

    public void setSpectacol_id(int spectacol_id) {
        this.spectacol_id = spectacol_id;
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "id=" + id +
                ", rand=" + rand +
                ", numar=" + numar +
                ", spectacol_id=" + spectacol_id +
                '}';
    }
}
