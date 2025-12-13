package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cats")
public class CatController {
    private final CatRepository catRepository;

    public CatController(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @GetMapping
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cat getCatById(@PathVariable Long id) {
        return catRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cat not found with id: " + id));
    }

    @PostMapping
    public Cat createCat(@RequestBody Cat cat) {
        return catRepository.save(cat);
    }

    @PutMapping("/{id}")
    public Cat updateCat(@PathVariable Long id, @RequestBody Cat updatedCat) {
        return catRepository.findById(id)
                .map(cat -> {
                    cat.setName(updatedCat.getName());
                    return catRepository.save(cat);
                })
                .orElseThrow(() -> new RuntimeException("Cat not found with id: " + id));
    }
    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable Long id) {
        catRepository.deleteById(id);
    }
}



