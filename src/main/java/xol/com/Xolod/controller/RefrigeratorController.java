package xol.com.Xolod.controller;



import xol.com.Xolod.model.Refrigerator;
import xol.com.Xolod.repository.RefrigeratorRepository;
import xol.com.Xolod.repository.ManufacturerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refrigerators")
public class RefrigeratorController {

    private final RefrigeratorRepository refrigeratorRepository;
    private final ManufacturerRepository manufacturerRepository;

    public RefrigeratorController(RefrigeratorRepository refrigeratorRepository, ManufacturerRepository manufacturerRepository) {
        this.refrigeratorRepository = refrigeratorRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @GetMapping
    public List<Refrigerator> getAll() {
        return refrigeratorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Refrigerator getById(@PathVariable Long id) {
        return refrigeratorRepository.findById(id).orElseThrow();
    }

    @PostMapping("/{manufacturerId}")
    public Refrigerator create(@PathVariable Long manufacturerId, @RequestBody Refrigerator refrigerator) {
        var manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow();
        refrigerator.setManufacturer(manufacturer);
        return refrigeratorRepository.save(refrigerator);
    }

    @PutMapping("/{id}")
    public Refrigerator update(@PathVariable Long id, @RequestBody Refrigerator updated) {
        Refrigerator refrigerator = refrigeratorRepository.findById(id).orElseThrow();
        refrigerator.setModel(updated.getModel());
        refrigerator.setType(updated.getType());
        refrigerator.setPrice(updated.getPrice());
        refrigerator.setColor(updated.getColor());
        refrigerator.setLength(updated.getLength());
        return refrigeratorRepository.save(refrigerator);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        refrigeratorRepository.deleteById(id);
    }
}
