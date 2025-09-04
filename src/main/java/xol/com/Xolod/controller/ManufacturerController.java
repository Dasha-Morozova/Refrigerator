package xol.com.Xolod.controller;


import xol.com.Xolod.model.Manufacturer;
import xol.com.Xolod.repository.ManufacturerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {

    private final ManufacturerRepository repository;

    public ManufacturerController(ManufacturerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Manufacturer> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Manufacturer getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Manufacturer create(@RequestBody Manufacturer manufacturer) {
        return repository.save(manufacturer);
    }

    @PutMapping("/{id}")
    public Manufacturer update(@PathVariable Long id, @RequestBody Manufacturer updated) {
        Manufacturer manufacturer = repository.findById(id).orElseThrow();
        manufacturer.setName(updated.getName());
        manufacturer.setCountry(updated.getCountry());
        return repository.save(manufacturer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
