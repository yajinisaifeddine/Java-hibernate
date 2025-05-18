package dao;

public class DaoFactory {
    private static UserDao userDao;
    private static ProductDao productDao;

    public static UserDao getUserDao() {
        if (userDao == null) userDao = new UserDao();
        return userDao;
    }
    public static ProductDao getProductDao(){
        if(productDao == null) productDao = new ProductDao();
        return productDao;
    }

}
