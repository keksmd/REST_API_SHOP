package org.alexkekiy.rest.api.shop.laptop;

import org.alexkekiy.rest.api.shop.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LaptopService {
    LaptopRepository laptopRepository;

    private static LaptopDto entityToDTO(LaptopEntity entity) {
        return LaptopDto.builder()
                .size(entity.getSize())
                .count(entity.getCount())
                .price(entity.getPrice())
                .serial(entity.getSerial())
                .madeBy(entity.getMadeBy())
                .build();
    }

    public void update(LaptopDto laptopDto, Long id) {
        Optional<LaptopEntity> optionalPKEntity = laptopRepository.findById(id);
        if (optionalPKEntity.isEmpty()) {
            throw new ResourceNotFoundException("PK with id " + id + " not found");
        }
        LaptopEntity entity = optionalPKEntity.get();
        entity.setCount(laptopDto.getCount());
        entity.setPrice(laptopDto.getPrice());
        entity.setMadeBy(laptopDto.getMadeBy());
        entity.setSerial(laptopDto.getSerial());
        entity.setSize(laptopDto.getSize());
        laptopRepository.save(entity);

    }

    public LaptopDto[] getAll() {
        ArrayList<LaptopDto> dtos = new ArrayList<>();
        laptopRepository.findAll().forEach(entity -> dtos.add(entityToDTO(entity)));
        return dtos.toArray(new LaptopDto[0]);
    }


    public LaptopDto getById(Long id) {
        Optional<LaptopEntity> optionalPKEntity = laptopRepository.findById(id);
        if (optionalPKEntity.isEmpty()) {
            throw new ResourceNotFoundException("PK with id " + id + " not found");
        }
        LaptopEntity entity = optionalPKEntity.get();
        return entityToDTO(entity);
    }
}
