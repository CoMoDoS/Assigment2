package Model;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class BiletDAO {

    public static EntityManager entityManager;
    public static EntityManagerFactory entityManagerFactory;


    public static void insert(Bilet bilet)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(bilet);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }

    public static Bilet findById(int id)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        Bilet c1 = entityManager.find(Bilet.class, id);
        entityManager.close();
        entityManagerFactory.close();
        return c1;
    }

    public static ArrayList<Bilet> selectAll(int id)
    {

        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();

        ArrayList<Bilet> elem = new ArrayList<Bilet>();
        List<Bilet> bilets = entityManager.createNamedQuery("Bilet.showAll").getResultList();

        for ( Bilet c1 : bilets )
        {
            Bilet aux = new Bilet(c1.getRand(),c1.getNumar(),c1.getSpectacol_id());
            aux.setId(c1.getId());
            if ( aux.getSpectacol_id() == id )
                elem.add(aux);
        }
        entityManager.close();
        entityManagerFactory.close();

        return elem;

    }

    public static void export(Exporter exporter,int id)
    {
        exporter.export(id);
    }



}
