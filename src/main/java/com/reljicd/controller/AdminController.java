package com.reljicd.controller;

import com.reljicd.model.Product;
import com.reljicd.model.User;
import com.reljicd.repository.RoleRepository;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.ProductService;
import com.reljicd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(ProductService productService, UserService userService,
                          UserRepository userRepository, RoleRepository roleRepository) {
        this.productService = productService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/dashboard")
    public ModelAndView adminDashboard(@RequestParam(value = "search", required = false) String search) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products;

        if (search != null && !search.trim().isEmpty()) {
            products = productService.findAllProducts().stream()
                    .filter(p -> p.getName().toLowerCase().contains(search.toLowerCase()) ||
                               p.getDescription().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            products = productService.findAllProducts();
        }

        modelAndView.addObject("products", products);
        modelAndView.addObject("users", userService.findAllUsers());
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("totalProducts", productService.findAllProducts().size());
        modelAndView.addObject("totalUsers", userService.findAllUsers().size());
        modelAndView.addObject("searchTerm", search);
        modelAndView.setViewName("/admin/dashboard");
        return modelAndView;
    }

    @PostMapping("/products/add")
    public ModelAndView addProduct(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("products", productService.findAllProducts());
            modelAndView.setViewName("/admin/dashboard");
        } else {
            productService.saveProduct(product);
            redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");
            modelAndView.setViewName("redirect:/admin/dashboard");
        }

        return modelAndView;
    }

    @GetMapping("/products/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully!");
        return new ModelAndView("redirect:/admin/dashboard");
    }

    @GetMapping("/products/edit/{id}")
    public ModelAndView editProduct(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = productService.findById(id).orElse(new Product());
        modelAndView.addObject("product", product);
        modelAndView.addObject("editMode", true);
        modelAndView.setViewName("/admin/dashboard");
        return modelAndView;
    }

    @PostMapping("/products/update")
    public ModelAndView updateProduct(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("editMode", true);
            modelAndView.setViewName("/admin/dashboard");
        } else {
            productService.saveProduct(product);
            redirectAttributes.addFlashAttribute("successMessage", "Product updated successfully!");
            modelAndView.setViewName("redirect:/admin/dashboard");
        }

        return modelAndView;
    }

    @GetMapping("/users/change-role/{id}/{role}")
    public ModelAndView changeUserRole(@PathVariable("id") Long id, @PathVariable("role") String role, RedirectAttributes redirectAttributes) {
        User user = userService.findAllUsers().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst().orElse(null);

        if (user != null) {
            // Update user role - this is a simplified approach
            // In a real app, you'd want more sophisticated role management
            user.setRoles(Collections.singletonList(roleRepository.findByRole("ROLE_" + role.toUpperCase())));
            userRepository.save(user);
            redirectAttributes.addFlashAttribute("successMessage", "User role updated successfully!");
        }

        return new ModelAndView("redirect:/admin/dashboard");
    }

    @GetMapping("/users/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        // Note: In a real application, you might want to soft delete users instead
        User user = userService.findAllUsers().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst().orElse(null);

        if (user != null) {
            userRepository.delete(user);
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
        }

        return new ModelAndView("redirect:/admin/dashboard");
    }
}