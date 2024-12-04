package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService) {
        this.productRepository = productRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    public Product handleSaveProduct(Product product) {
        Product newProduct = this.productRepository.save(product);
        return newProduct;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductByID(long id) {
        return this.productRepository.findById(id);
    }

    public void deleteProductByID(long id) {
        this.productRepository.deleteById(id);
    }

    public void handleAddProductToCart(String email, long productID) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            // check user đã có cart hay chưa
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(1);
                cart = this.cartRepository.save(newCart);
            }

            // save cart_detail
            // tìm product by id
            Product productOptional = this.productRepository.findById(productID);
            if (productOptional != null) {
                CartDetail newCartDetail = new CartDetail();
                newCartDetail.setCart(cart);
                newCartDetail.setProduct(productOptional);
                newCartDetail.setPrice(productOptional.getPrice());
                newCartDetail.setQuantity(1);
                this.cartDetailRepository.save(newCartDetail);
            }
        }
    }
}
