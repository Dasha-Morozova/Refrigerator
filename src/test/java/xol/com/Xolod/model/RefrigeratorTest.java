package xol.com.Xolod.model;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RefrigeratorTest {

    @Test
    void testGettersAndSetters() {
        Refrigerator refrigerator = new Refrigerator();

        refrigerator.setId(1L);
        refrigerator.setModel("RT-500");
        refrigerator.setType("Two-door");
        refrigerator.setPrice(999.99);
        refrigerator.setColor("White");
        refrigerator.setLength(185.0);

        assertThat(refrigerator.getId()).isEqualTo(1L);
        assertThat(refrigerator.getModel()).isEqualTo("RT-500");
        assertThat(refrigerator.getType()).isEqualTo("Two-door");
        assertThat(refrigerator.getPrice()).isEqualTo(999.99);
        assertThat(refrigerator.getColor()).isEqualTo("White");
        assertThat(refrigerator.getLength()).isEqualTo(185.0);
    }



    @Test
    void testManufacturerAssociation() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(5L);
        manufacturer.setName("Samsung");
        manufacturer.setCountry("South Korea");

        Refrigerator refrigerator = new Refrigerator();
        refrigerator.setManufacturer(manufacturer);

        assertThat(refrigerator.getManufacturer()).isEqualTo(manufacturer);
        assertThat(refrigerator.getManufacturer().getName()).isEqualTo("Samsung");
    }
}
