package org.alexkekiy.rest.api.shop.harddisk;

import org.alexkekiy.rest.api.shop.pk.PKDto;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/harddisk")
public class HarddiskController {
    private final HarddiskService harddiskService;

    public HarddiskController(HarddiskService harddiskService) {
        this.harddiskService = harddiskService;
    }

    @PostMapping("/{id}")
    public void update(@RequestBody HarddiskDto harddiskDto, @PathVariable(name = "id") Long id) {
        harddiskService.update(harddiskDto,id);
    }
    @PostMapping
    public long create(@RequestBody HarddiskDto harddiskDto) {
        return harddiskService.create(harddiskDto);
    }

    @GetMapping
    public HarddiskDto[] getAll() {
        return harddiskService.getAll();
    }

    @GetMapping("/{id}")
    public HarddiskDto getById(@PathVariable(name = "id") Long id) {
        try{
            return harddiskService.getById(id);
        }catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

}
