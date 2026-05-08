package com.reljicd.config;

import com.reljicd.model.Product;
import com.reljicd.model.Role;
import com.reljicd.model.User;
import com.reljicd.repository.ProductRepository;
import com.reljicd.repository.RoleRepository;
import com.reljicd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PersistentDataBootstrapRunner implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Autowired
    public PersistentDataBootstrapRunner(RoleRepository roleRepository, ProductRepository productRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        ensureDefaultUser();
        ensureRole("ROLE_ADMIN");
        ensureRole("ROLE_USER");
        productRepository.deleteByName("Tooth Brush");

        if (productRepository.count() == 0) {
            createProduct("Soap", "Pears baby soap for Kids", 1, "35.75",
                    "https://images.pexels.com/photos/4465124/pexels-photo-4465124.jpeg?auto=compress&cs=tinysrgb&w=900");
            createProduct("Shirt", "Casual Shirt imported from France", 3, "1500.00",
                    "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?auto=format&fit=crop&w=900&q=80");
            createProduct("Office Bag", "Leather bag imported from USA", 40, "1000.00",
                    "https://images.unsplash.com/photo-1548036328-c9fa89d128fa?auto=format&fit=crop&w=900&q=80");
            createProduct("Bottle", "Hot Water Bottles", 80, "450.45",
                    "https://images.unsplash.com/photo-1523362628745-0c100150b504?auto=format&fit=crop&w=900&q=80");
            createProduct("Wrist Watch", "Imported wrist watches from swiss", 800, "2500.00",
                    "https://images.unsplash.com/photo-1522312346375-d1a52e2b99b3?auto=format&fit=crop&w=900&q=80");
            createProduct("Mobile Phone", "3G/4G capability", 700, "45000.00",
                    "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?auto=format&fit=crop&w=900&q=80");
            createProduct("Shampoo", "Head and Shoulders Shampoo", 500, "300.00",
                    "https://images.unsplash.com/photo-1556228578-8c89e6adf883?auto=format&fit=crop&w=900&q=80");
            createProduct("Leather Wallets", "Imported Leather Wallets from AUS", 1000, "500.00",
                    "https://images.unsplash.com/photo-1627123424574-724758594e93?auto=format&fit=crop&w=900&q=80");
            createProduct("Camera", "Imported Canon camera from USA", 10, "85000.00",
                    "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?auto=format&fit=crop&w=900&q=80");
        }
    }

    private void ensureRole(String roleName) {
        if (roleRepository.findByRole(roleName) == null) {
            Role role = new Role();
            role.setRole(roleName);
            roleRepository.save(role);
        }
    }

    private void createProduct(String name, String description, int quantity, String price, String imageUrl) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setPrice(new BigDecimal(price));
        product.setImageUrl(imageUrl);
        productRepository.save(product);
    }

    private void ensureDefaultUser() {
        if (userService.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            user.setPassword("user12345");
            user.setEmail("user@example.com");
            user.setName("Default");
            user.setLastName("User");
            userService.saveUser(user);
        }
    }
}
