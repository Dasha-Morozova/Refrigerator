package xol.com.Xolod.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Data
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnoreProperties("warehouse") // игнорировать обратную ссылку при сериализации
    private List<Refrigerator> refrigerators;
}
