package org.alexkekiy.rest.api.shop.harddisk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.alexkekiy.rest.api.shop.ItemDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class HarddiskDto extends ItemDto {
    private int volume;

    public HarddiskDto(int price, String madeBy, long serial, int count, int volume) {
        super(price, madeBy, serial, count);
        this.volume = volume;
    }
}
