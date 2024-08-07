package org.alexkekiy.rest.api.shop.laptop;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/laptop")
public class LaptopController {
    private final LaptopService laptopService;

    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @PostMapping("/{id}")
    public void update(@RequestBody LaptopDto laptopDto, @PathVariable(name = "id") Long id) {
        laptopService.update(laptopDto,id);
    }

    @GetMapping
    public LaptopDto[] getAll() {
        return laptopService.getAll();
    }

    @GetMapping("/{id}")
    public LaptopDto getById(@PathVariable(name = "id") Long id) {
        try{
            return laptopService.getById(id);
        }catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

}
