package xol.com.Xolod.model;


import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ManufacturerTest {

    @Test
    void testGettersAndSetters() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1L);
        manufacturer.setName("LG");
        manufacturer.setCountry("South Korea");

        assertThat(manufacturer.getId()).isEqualTo(1L);
        assertThat(manufacturer.getName()).isEqualTo("LG");
        assertThat(manufacturer.getCountry()).isEqualTo("South Korea");
    }

    @Test
    void testRefrigeratorsAssociation() {
        Manufacturer manufacturer = new Manufacturer();
        Refrigerator refrigerator = new Refrigerator();
        refrigerator.setManufacturer(manufacturer);

        List<Refrigerator> refrigerators = new ArrayList<>();
        refrigerators.add(refrigerator);
        manufacturer.setRefrigerators(refrigerators);

        assertThat(manufacturer.getRefrigerators()).hasSize(1);
        assertThat(manufacturer.getRefrigerators().get(0).getManufacturer())
                .isEqualTo(manufacturer);
    }
}
