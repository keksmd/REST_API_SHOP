package org.alexkekiy.rest.api.shop.monitor;

import org.alexkekiy.rest.api.shop.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MonitorService {
    MonitorRepository monitorRepository;

    private static MonitorDto entityToDTO(MonitorEntity entity) {
        return MonitorDto.builder()
                .diagonal(entity.getDiagonal())
                .count(entity.getCount())
                .price(entity.getPrice())
                .serial(entity.getSerial())
                .madeBy(entity.getMadeBy())
                .build();
    }

    public void update(MonitorDto monitorDto,Long id) {
        Optional<MonitorEntity> optionalPKEntity = monitorRepository.findById(id);
        if (optionalPKEntity.isEmpty()) {
            throw new ResourceNotFoundException("Monitor with id " + id + " not found");
        }
        MonitorEntity entity = optionalPKEntity.get();
        entity.setCount(monitorDto.getCount());
        entity.setPrice(monitorDto.getPrice());
        entity.setMadeBy(monitorDto.getMadeBy());
        entity.setSerial(monitorDto.getSerial());
        entity.setDiagonal(monitorDto.getDiagonal());
        monitorRepository.save(entity);
    }

    public MonitorDto[] getAll() {
        ArrayList<MonitorDto> dtos = new ArrayList<>();
        monitorRepository.findAll().forEach(entity -> dtos.add(entityToDTO(entity)));
        return dtos.toArray(new MonitorDto[0]);
    }


    public MonitorDto getById(Long id) {
        Optional<MonitorEntity> optionalPKEntity = monitorRepository.findById(id);
        if (optionalPKEntity.isEmpty()) {
            throw new ResourceNotFoundException("Monitor with id " + id + " not found");
        }
        MonitorEntity entity = optionalPKEntity.get();
        return entityToDTO(entity);
    }
}
