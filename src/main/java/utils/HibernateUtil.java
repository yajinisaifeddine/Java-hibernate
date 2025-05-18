package utils;

import model.User;
import model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private HibernateUtil() {}

    private static final SessionFactory sessionFactory;

    static {
        try {
            Class.forName("org.postgresql.Driver");

            Configuration config = new Configuration();

            config.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            config.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/TPJEE_V2");
            config.setProperty("hibernate.connection.username", "postgres");
            config.setProperty("hibernate.connection.password", "Djerba25");
            config.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            config.setProperty("hibernate.hbm2ddl.auto", "update");
            config.setProperty("hibernate.show_sql", "true");

            // Register model classes
            config.addAnnotatedClass(User.class);
            config.addAnnotatedClass(Product.class);

            sessionFactory = config.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed: " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
