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

public class SpectacolDAO {

    public static EntityManager entityManager;
    public static EntityManagerFactory entityManagerFactory;


    public static void insert(Spectacol spectacol)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(spectacol);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }

    public static Spectacol findById(int id)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        Spectacol c1 = entityManager.find(Spectacol.class, id);
        entityManager.close();
        entityManagerFactory.close();
        return c1;
    }
    public static void delete(int id)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        Spectacol a1 = entityManager.find(Spectacol.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(a1);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void update(int id, Spectacol admin)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        Spectacol a1 = entityManager.find(Spectacol.class, id);
        entityManager.getTransaction().begin();
        a1.setId(admin.getId());
        a1.setData(admin.getData());
        a1.setDistributie(admin.getDistributie());
        a1.setGen(admin.getGen());
        a1.setRegie(admin.getRegie());
        a1.setTitlu(admin.getTitlu());
        a1.setNrBilete(admin.getNrBilete());
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }



    public static ArrayList<Spectacol> selectAll()
    {

        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();

        ArrayList<Spectacol> elem = new ArrayList<Spectacol>();
        List<Spectacol> spectacols = entityManager.createNamedQuery("Spectacol.showAll").getResultList();

        for ( Spectacol c1 : spectacols )
        {
            Spectacol aux = new Spectacol(c1.getGen(), c1.getTitlu(), c1.getRegie(), c1.getDistributie(), c1.getData(), c1.getNrBilete());
            aux.setId(c1.getId());
            elem.add(aux);
        }
        entityManager.close();
        entityManagerFactory.close();

        return elem;

    }



}
