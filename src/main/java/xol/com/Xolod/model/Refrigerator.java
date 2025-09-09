package xol.com.Xolod.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Refrigerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private String type;
    private Double price;
    private String color;
    private Double length;
    private Integer quantity; // количество на складе

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    @JsonIgnoreProperties("refrigerators") // чтобы не зациклить JSON
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties({"refrigerators"}) // игнорируем обратную ссылку
    private Warehouse warehouse;
}
