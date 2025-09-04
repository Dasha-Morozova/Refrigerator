package xol.com.Xolod.model;



import jakarta.persistence.*;
        import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Refrigerator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String type;
    private Double price;
    private String color;
    private Double length;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getLength() {
        return length;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
}
