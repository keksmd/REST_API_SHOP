package org.alexkekiy.rest.api.shop.harddisk;

import org.alexkekiy.rest.api.shop.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class HarddiskService {
    HarddiskRepository harddiskRepository;

    private static HarddiskDto entityToDTO(HarddiskEntity entity) {
        return HarddiskDto.builder()
                .volume(entity.getVolume())
                .count(entity.getCount())
                .price(entity.getPrice())
                .serial(entity.getSerial())
                .madeBy(entity.getMadeBy())
                .build();
    }
    public long create(HarddiskDto harddiskDto) {
        HarddiskEntity harddiskEntity = new HarddiskEntity();
        harddiskEntity.setCount(harddiskDto.getCount());
        harddiskEntity.setPrice(harddiskDto.getPrice());
        harddiskEntity.setMadeBy(harddiskDto.getMadeBy());
        harddiskEntity.setSerial(harddiskDto.getSerial());
        harddiskEntity.setVolume(harddiskDto.getVolume());
        harddiskRepository.save(harddiskEntity);
        return harddiskEntity.getId();
    }

    public void update(HarddiskDto harddiskDto, Long id) {
        Optional<HarddiskEntity> optionalPKEntity = harddiskRepository.findById(id);
        if (optionalPKEntity.isEmpty()) {
            throw new ResourceNotFoundException("Monitor with id " + id + " not found");
        }
        HarddiskEntity entity = optionalPKEntity.get();
        entity.setCount(harddiskDto.getCount());
        entity.setPrice(harddiskDto.getPrice());
        entity.setMadeBy(harddiskDto.getMadeBy());
        entity.setSerial(harddiskDto.getSerial());
        entity.setVolume(harddiskDto.getVolume());
        harddiskRepository.save(entity);
    }

    public HarddiskDto[] getAll() {
        ArrayList<HarddiskDto> dtos = new ArrayList<>();
        harddiskRepository.findAll().forEach(entity -> dtos.add(entityToDTO(entity)));
        return dtos.toArray(new HarddiskDto[0]);
    }


    public HarddiskDto getById(Long id) {
        Optional<HarddiskEntity> optionalPKEntity = harddiskRepository.findById(id);
        if (optionalPKEntity.isEmpty()) {
            throw new ResourceNotFoundException("Monitor with id " + id + " not found");
        }
        HarddiskEntity entity = optionalPKEntity.get();
        return entityToDTO(entity);
    }
}
