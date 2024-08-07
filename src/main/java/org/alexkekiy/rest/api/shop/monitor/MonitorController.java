package org.alexkekiy.rest.api.shop.monitor;

import org.alexkekiy.rest.api.shop.pk.PKDto;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/monitor")
public class MonitorController {
    private final MonitorService monitorService;
    @PostMapping
    public long create(@RequestBody MonitorDto monitorDto) {
        return monitorService.create(monitorDto);
     }
    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @PostMapping("/{id}")
    public void update(@RequestBody MonitorDto monitorDto,@PathVariable(name = "id") Long id) {
        monitorService.update(monitorDto,id);
    }

    @GetMapping
    public MonitorDto[] getAll() {
        return monitorService.getAll();
    }

    @GetMapping("/{id}")
    public MonitorDto getById(@PathVariable(name = "id") Long id) {
        try{
            return monitorService.getById(id);
        }catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

}
