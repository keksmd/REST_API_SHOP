package org.alexkekiy.rest.api.shop.monitor;

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
public class MonitorDto extends ItemDto {
    private int diagonal;

    public MonitorDto(int price, String madeBy, long serial, int count, int diagonal) {
        super(price, madeBy, serial, count);
        this.diagonal = diagonal;
    }
}
