package dao;

import model.Product;

public class ProductDao extends AbstractDao<Product> {
    protected ProductDao() {
        super(Product.class);
    }
}
