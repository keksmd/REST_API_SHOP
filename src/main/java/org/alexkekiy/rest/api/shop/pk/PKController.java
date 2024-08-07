package org.alexkekiy.rest.api.shop.pk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/pk")
public class PKController {
    private final PKService pkService;

    public PKController(PKService pkService) {
        this.pkService = pkService;
    }

    @PostMapping("/{id}")
    public void update(@RequestBody PKDto pkDto,@PathVariable(name = "id") Long id) {
        pkService.update(pkDto,id);
    }

    @GetMapping
    public PKDto[] getAll() {
        return pkService.getAll();
    }

    @GetMapping("/{id}")
    public PKDto getById(@PathVariable(name = "id") Long id) {
        try{
            return pkService.getById(id);
        }catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

}
