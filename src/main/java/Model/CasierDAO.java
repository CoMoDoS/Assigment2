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

public class CasierDAO {

    public static EntityManager entityManager;
    public static EntityManagerFactory entityManagerFactory;


    public static void insert(Casier casier)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(casier);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }

    public static Casier findById(int id)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        Casier c1 = entityManager.find(Casier.class, id);
        entityManager.close();
        entityManagerFactory.close();
        return c1;
    }

    public static Casier findByEmail(String email)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Casier.findByEmail");
        query.setParameter("email",email);
        Casier casier = (Casier) query.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();
        return casier;
    }

    public static void delete(int id)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        Casier a1 = entityManager.find(Casier.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(a1);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void update(int id, Casier admin)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        Casier a1 = entityManager.find(Casier.class, id);
        entityManager.getTransaction().begin();
        a1.setId(admin.getId());
        a1.setParola(admin.getParola());
        a1.setName(admin.getName());
        a1.setEmail(admin.getEmail());
        a1.setStatus(admin.getStatus());
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public static ArrayList<Casier> selectAll()
    {

        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        entityManager = entityManagerFactory.createEntityManager();
        String[] dateTabel = new String[40];
        ArrayList<Casier> elem = new ArrayList<Casier>();
        List<Casier> casiers = entityManager.createNamedQuery("Casier.showAll").getResultList();

        for ( Casier c1 : casiers )
        {
            Casier aux = new Casier(c1.getName(), c1.getEmail(), c1.getParola(), c1.getStatus());
            aux.setId(c1.getId());
            elem.add(aux);
        }
        entityManager.close();
        entityManagerFactory.close();

        return elem;

    }



}
