package xol.com.Xolod.controller;
import java.util.List;


import xol.com.Xolod.model.Refrigerator;
import xol.com.Xolod.repository.RefrigeratorRepository;
import xol.com.Xolod.repository.ManufacturerRepository;
import org.springframework.web.bind.annotation.*;
import xol.com.Xolod.repository.WarehouseRepository;
import xol.com.Xolod.model.Manufacturer;
import  xol.com.Xolod.model.Warehouse;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/refrigerators")
public class RefrigeratorController {

    private final RefrigeratorRepository refrigeratorRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final WarehouseRepository warehouseRepository;

    public RefrigeratorController(RefrigeratorRepository refrigeratorRepository,
                                  ManufacturerRepository manufacturerRepository,
                                  WarehouseRepository warehouseRepository) {
        this.refrigeratorRepository = refrigeratorRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.warehouseRepository = warehouseRepository;
    }

    // ====== READ ALL ======
    @GetMapping
    public List<Refrigerator> getAll() {
        return refrigeratorRepository.findAll();
    }

    // ====== READ ONE ======
    @GetMapping("/{id}")
    public ResponseEntity<Refrigerator> getById(@PathVariable Long id) {
        return refrigeratorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ====== CREATE ======
    // здесь указываем производителя и склад
    @PostMapping("/{manufacturerId}/{warehouseId}")
    public ResponseEntity<Refrigerator> create(
            @PathVariable Long manufacturerId,
            @PathVariable Long warehouseId,
            @RequestBody Refrigerator refrigerator) {

        return manufacturerRepository.findById(manufacturerId)
                .flatMap(m -> warehouseRepository.findById(warehouseId).map(w -> {
                    refrigerator.setManufacturer(m);
                    refrigerator.setWarehouse(w);
                    Refrigerator saved = refrigeratorRepository.save(refrigerator);

                    Refrigerator withRelations = refrigeratorRepository.findById(saved.getId())
                            .orElseThrow();
                    return ResponseEntity.ok(withRelations);
                }))
                .orElse(ResponseEntity.notFound().build());
    }

    // ====== UPDATE ======
    @PutMapping("/{id}/{manufacturerId}/{warehouseId}")
    public ResponseEntity<Refrigerator> update(
            @PathVariable Long id,
            @PathVariable Long manufacturerId,
            @PathVariable Long warehouseId,
            @RequestBody Refrigerator updated) {

        return refrigeratorRepository.findById(id).flatMap(existing ->
                manufacturerRepository.findById(manufacturerId).flatMap(m ->
                        warehouseRepository.findById(warehouseId).map(w -> {
                            existing.setModel(updated.getModel());
                            existing.setType(updated.getType());
                            existing.setPrice(updated.getPrice());
                            existing.setColor(updated.getColor());
                            existing.setLength(updated.getLength());
                            existing.setQuantity(updated.getQuantity());
                            existing.setManufacturer(m);
                            existing.setWarehouse(w);
                            Refrigerator saved = refrigeratorRepository.save(existing);
                            return ResponseEntity.ok(saved);
                        })
                )
        ).orElse(ResponseEntity.notFound().build());
    }

    // ====== DELETE ======
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return refrigeratorRepository.findById(id)
                .map(existing -> {
                    refrigeratorRepository.delete(existing);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }



}

