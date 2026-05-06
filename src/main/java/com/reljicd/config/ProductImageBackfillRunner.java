package com.reljicd.config;

import com.reljicd.model.Product;
import com.reljicd.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductImageBackfillRunner implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final Map<String, String> defaultImageByKeyword = new HashMap<>();

    @Autowired
    public ProductImageBackfillRunner(ProductRepository productRepository) {
        this.productRepository = productRepository;
        defaultImageByKeyword.put("soap", "https://images.pexels.com/photos/4465124/pexels-photo-4465124.jpeg?auto=compress&cs=tinysrgb&w=900");
        defaultImageByKeyword.put("tooth", "https://images.pexels.com/photos/298611/pexels-photo-298611.jpeg?auto=compress&cs=tinysrgb&w=900");
        defaultImageByKeyword.put("shirt", "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?auto=format&fit=crop&w=900&q=80");
        defaultImageByKeyword.put("bag", "https://images.unsplash.com/photo-1548036328-c9fa89d128fa?auto=format&fit=crop&w=900&q=80");
        defaultImageByKeyword.put("bottle", "https://images.unsplash.com/photo-1523362628745-0c100150b504?auto=format&fit=crop&w=900&q=80");
        defaultImageByKeyword.put("watch", "https://images.unsplash.com/photo-1522312346375-d1a52e2b99b3?auto=format&fit=crop&w=900&q=80");
        defaultImageByKeyword.put("mobile", "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?auto=format&fit=crop&w=900&q=80");
        defaultImageByKeyword.put("shampoo", "https://images.unsplash.com/photo-1556228578-8c89e6adf883?auto=format&fit=crop&w=900&q=80");
        defaultImageByKeyword.put("wallet", "https://images.unsplash.com/photo-1627123424574-724758594e93?auto=format&fit=crop&w=900&q=80");
        defaultImageByKeyword.put("camera", "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?auto=format&fit=crop&w=900&q=80");
    }

    @Override
    public void run(String... args) {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            String fallbackImage = imageForProductName(product.getName());
            boolean missingImage = product.getImageUrl() == null || product.getImageUrl().trim().isEmpty();
            boolean forceRefreshForKnownIssues = isForceRefreshProduct(product.getName());
            if (missingImage || forceRefreshForKnownIssues) {
                product.setImageUrl(fallbackImage);
                productRepository.save(product);
            }
        }
    }

    private boolean isForceRefreshProduct(String productName) {
        if (productName == null) {
            return false;
        }
        String loweredName = productName.toLowerCase();
        return loweredName.contains("soap") || loweredName.contains("tooth");
    }

    private String imageForProductName(String productName) {
        if (productName == null) {
            return "https://via.placeholder.com/400x260?text=Product";
        }
        String loweredName = productName.toLowerCase();
        for (Map.Entry<String, String> entry : defaultImageByKeyword.entrySet()) {
            if (loweredName.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "https://via.placeholder.com/400x260?text=Product";
    }
}
