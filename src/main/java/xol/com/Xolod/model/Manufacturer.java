package xol.com.Xolod.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;




    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public List<Refrigerator> getRefrigerators() {
        return refrigerators;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRefrigerators(List<Refrigerator> refrigerators) {
        this.refrigerators = refrigerators;
    }

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("manufacturer") // игнорировать обратную ссылку при сериализации
    private List<Refrigerator> refrigerators;
}
