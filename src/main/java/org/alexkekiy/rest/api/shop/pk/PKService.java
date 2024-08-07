package org.alexkekiy.rest.api.shop.pk;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import org.alexkekiy.rest.api.shop.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PKService {
    PKRepository pkRepository;
    public long create(PKDto pkDto) {

        PKEntity pkEntity = new PKEntity();
        pkEntity.setCount(pkDto.getCount());
        pkEntity.setPrice(pkDto.getPrice());
        pkEntity.setMadeBy(pkDto.getMadeBy());
        pkEntity.setSerial(pkDto.getSerial());
        pkEntity.setFormFactor(pkDto.getFormFactor());
        pkRepository.save(pkEntity);
        return pkEntity.getId();
    }

    private static PKDto entityToDTO(PKEntity entity) {
        return PKDto.builder()
                .formFactor(entity.getFormFactor())
                .count(entity.getCount())
                .price(entity.getPrice())
                .serial(entity.getSerial())
                .madeBy(entity.getMadeBy())
                .build();
    }

    public void update(PKDto pkDto,Long id) {
        Optional<PKEntity> optionalPKEntity = pkRepository.findById(id);
        if (optionalPKEntity.isEmpty()) {
            throw new ResourceNotFoundException("PK with id " + id + " not found");
        }
        PKEntity entity = optionalPKEntity.get();
        entity.setCount(pkDto.getCount());
        entity.setPrice(pkDto.getPrice());
        entity.setMadeBy(pkDto.getMadeBy());
        entity.setSerial(pkDto.getSerial());
        entity.setFormFactor(pkDto.getFormFactor());
        pkRepository.save(entity);

    }

    public PKDto[] getAll() {
        ArrayList<PKDto> dtos = new ArrayList<>();
        pkRepository.findAll().forEach(entity -> dtos.add(entityToDTO(entity)));
        return dtos.toArray(new PKDto[0]);
    }


    public PKDto getById(Long id) {
        Optional<PKEntity> optionalPKEntity = pkRepository.findById(id);
        if (optionalPKEntity.isEmpty()) {
            throw new ResourceNotFoundException("PK with id " + id + " not found");
        }
        PKEntity entity = optionalPKEntity.get();
        return entityToDTO(entity);
    }
}
