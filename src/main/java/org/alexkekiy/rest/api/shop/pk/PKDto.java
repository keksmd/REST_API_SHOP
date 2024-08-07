package org.alexkekiy.rest.api.shop.pk;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.alexkekiy.rest.api.shop.ItemDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PKDto extends ItemDto {

    private FormFactor formFactor;

    public PKDto(int price, String madeBy, long serial, int count, FormFactor formFactor) {
        super(price, madeBy, serial, count);
        this.formFactor = formFactor;
    }
}
