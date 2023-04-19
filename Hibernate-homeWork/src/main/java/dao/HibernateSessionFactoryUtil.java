package dao;

import model.City;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.util.function.Consumer;


public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private static HibernateSessionFactoryUtil hibernateSessionFactoryUtil;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(City.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
    private void hibernateSessionFactoryUtil() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Employee.class).addAnnotatedClass(City.class);
        this.sessionFactory = configuration.buildSessionFactory();
    }
    public void withEntityManager(Consumer<EntityManager> function) {
        try (Session session = this.sessionFactory.openSession()) {
            session.beginTransaction();
            function.accept(session);
            session.getTransaction().commit();
        }
    }
    public static HibernateSessionFactoryUtil getInstance() {
        if (hibernateSessionFactoryUtil == null) {
            hibernateSessionFactoryUtil = new HibernateSessionFactoryUtil();
        }
        return hibernateSessionFactoryUtil;
    }
}
