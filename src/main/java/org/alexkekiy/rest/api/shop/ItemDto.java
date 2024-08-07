package org.alexkekiy.rest.api.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Data
public class ItemDto {
    private int price;
    private String madeBy;
    private long serial;
    private volatile int count;
    public ItemDto(int price, String madeBy, long serial, int count) {
        this.price = price;
        this.madeBy = madeBy;
        this.serial = serial;
        this.count = count;
    }
}
