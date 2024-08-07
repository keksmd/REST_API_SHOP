package org.alexkekiy.rest.api.shop.laptop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.alexkekiy.rest.api.shop.ItemDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class LaptopDto extends ItemDto {

    private Size size;
    public LaptopDto(int price, String madeBy, long serial, int count, Size size) {
        super(price, madeBy, serial, count);
        this.size = size;
    }
}
