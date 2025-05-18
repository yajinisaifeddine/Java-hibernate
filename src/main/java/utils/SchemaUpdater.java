package utils;

import model.Product;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SchemaUpdater {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class) // Register CartItem entity
                .addAnnotatedClass(Product.class)  // Your Hibernate config
                .setProperty("hibernate.hbm2ddl.auto", "update"); // or "create", "create-drop"

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        try {
            SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
            // Database schema will be updated automatically
            System.out.println("migration completed");
            sessionFactory.close();
        } catch (Exception e) {
            System.err.println("migration failed");
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }
    }
}