package org.alexkekiy.rest.api.shop.pk;

import org.alexkekiy.rest.api.shop.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PKController.class)
class PKControllerIntegrationTest {
    static PKDto pkDto = new PKDto(100, "BrandA", 123456789L, 10, FormFactor.DESKTOP); //pkDto

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PKService pkService;

    @Test
    void testGetById_NotFound() throws Exception {
        Mockito.when(pkService.getById(anyLong())).thenThrow(new ResourceNotFoundException("PK with id 1 not found"));

        mockMvc.perform(get("/api/v1/pk/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.detail").value("PK with id 1 not found"));
    }
    @Test
    void testUpdate_Success() throws Exception {
        Mockito.doNothing().when(pkService).update(any(PKDto.class), anyLong());

        mockMvc.perform(post("/api/v1/pk/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1")
                        .content("{\"price\": 100, \"madeBy\": \"BrandA\", \"serial\": 123456789, \"count\": 10, \"formFactor\": \"DESKTOP\"}"))
                .andExpect(status().isOk());

        Mockito.verify(pkService, Mockito.times(1)).update(any(PKDto.class), anyLong());
    }
    @Test
    void testGetAll_Success() throws Exception {
        PKDto[] pkDtos = {pkDto, pkDto};
        Mockito.when(pkService.getAll()).thenReturn(pkDtos);

        mockMvc.perform(get("/api/v1/pk")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

        Mockito.verify(pkService, Mockito.times(1)).getAll();
    }
    @Test
    void testGetById_Success() throws Exception {
        Mockito.when(pkService.getById(anyLong())).thenReturn(pkDto);

        mockMvc.perform(get("/api/v1/pk/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.formFactor").value("DESKTOP"));

        Mockito.verify(pkService, Mockito.times(1)).getById(anyLong());
    }



}
