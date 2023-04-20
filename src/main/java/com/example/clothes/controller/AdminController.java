package com.example.clothes.controller;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItemStream.ItemSkippedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.clothes.model.DistributionCentre;
import com.example.clothes.model.Item;
import com.example.clothes.model.searchDto;
import com.example.clothes.model.Item.Brand;
import com.example.clothes.repository.ItemRepository;

@Controller
@RequestMapping("/admin")
@CrossOrigin("http://localhost:8082")
public class AdminController {
    private RestTemplate restTemplate;
    @Autowired
    private ItemRepository itemRepository;

    public DistributionCentre[] distributionCentres;

    public AdminController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String AdminPage(){
        return "admin";
    }
    @ModelAttribute
    public void items(Model model) {
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
    }
    @ModelAttribute("distributioncentres")
    public List<DistributionCentre> getDistrubDistributionCentres(){
        distributionCentres =  restTemplate.getForObject("http://localhost:8082/distribution-centre", DistributionCentre[].class);
        return Arrays.asList(distributionCentres);
    }

    @ModelAttribute
    public void brands(Model model){
        var brands = EnumSet.allOf(Brand.class);
        model.addAttribute("brands", brands);
    }
    @ModelAttribute
    public Item item() {
        return Item.builder()
                .build();
    }

    @PostMapping 
    public String searchByBrandAndName(@RequestBody searchDto searchParams){
        var brand = searchParams.getBrand();
        var name = searchParams.getName();
        var searchResults = restTemplate.getForObject("http://localhost:8082/distribution-centre/items/" + brand + "/" + name, Item[].class);

        List<Item> items = Arrays.asList(searchResults);
        itemRepository.saveAll(items);
        return "admin";
    }
}
