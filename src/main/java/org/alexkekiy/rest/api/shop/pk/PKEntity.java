package org.alexkekiy.rest.api.shop.pk;

import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PKEntity {

    private FormFactor formFactor;
    private int price;
    private String madeBy;
    private long serial;
    private int count;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;


}
