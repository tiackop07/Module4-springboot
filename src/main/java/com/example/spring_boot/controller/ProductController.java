package com.example.spring_boot.controller;

import com.example.spring_boot.model.Product;
import com.example.spring_boot.repository.ICategoryRepository;
import com.example.spring_boot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.domain.PageRequest.*;

@Controller
@RequestMapping("/")
@Service
public class ProductController {
    @Autowired
    private IProductService iProductService;


    @Autowired
    private ICategoryRepository iCategoryRepository;

    @GetMapping
    public String showList(Model model,
                           @RequestParam(name = "page", required = false,defaultValue = "0") int page){
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(page, 3, sort);
        Page<Product> products = iProductService.findAll(pageable);
        System.out.println(products.getTotalPages());
        model.addAttribute("list", products);
        return "home";
    }


    @GetMapping("save")
    public void save() {
//        Category c = new Category();
//        c.setName("CAR");
//        Product p1 = new Product("ex", 1000L, c);
//        Product p2 = new Product("ab", 1000L, c);
//        c.setProducts(new ArrayList<>(List.of(p1,p2)));
        iCategoryRepository.deleteById(5L);

    }
}
