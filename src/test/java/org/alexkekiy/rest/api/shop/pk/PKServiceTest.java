package org.alexkekiy.rest.api.shop.pk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.alexkekiy.rest.api.shop.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PKServiceTest {

    @Mock
    private PKRepository pkRepository;

    @InjectMocks
    private PKService pkService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdate_Success() {
        PKDto pkDto = new PKDto(100, "BrandA", 123456789L, 10, FormFactor.DESKTOP);
        PKEntity pkEntity = new PKEntity();
        pkEntity.setId(1L);

        when(pkRepository.findById(1L)).thenReturn(Optional.of(pkEntity));

        pkService.update(pkDto, 1L);

        verify(pkRepository, times(1)).findById(1L);
        verify(pkRepository, times(1)).save(any(PKEntity.class));
    }

    @Test
    void testUpdate_NotFound() {
        PKDto pkDto = new PKDto(100, "BrandA", 123456789L, 10, FormFactor.DESKTOP);

        when(pkRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> pkService.update(pkDto, 1L));

        assertEquals("PK with id 1 not found", exception.getMessage());
        verify(pkRepository, times(1)).findById(1L);
        verify(pkRepository, never()).save(any(PKEntity.class));
    }

    @Test
    void testGetAll() {
        PKEntity pkEntity1 = new PKEntity();
        pkEntity1.setId(1L);
        pkEntity1.setFormFactor(FormFactor.DESKTOP);

        PKEntity pkEntity2 = new PKEntity();
        pkEntity2.setId(2L);
        pkEntity2.setFormFactor(FormFactor.NETTOP);

        when(pkRepository.findAll()).thenReturn(List.of(pkEntity1, pkEntity2));

        PKDto[] result = pkService.getAll();

        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals(FormFactor.DESKTOP, result[0].getFormFactor());
        assertEquals(FormFactor.NETTOP, result[1].getFormFactor());
        verify(pkRepository, times(1)).findAll();
    }

    @Test
    void testGetById_Success() {
        PKEntity pkEntity = new PKEntity();
        pkEntity.setId(1L);
        pkEntity.setFormFactor(FormFactor.MONOBLOCK);

        when(pkRepository.findById(1L)).thenReturn(Optional.of(pkEntity));

        PKDto result = pkService.getById(1L);

        assertNotNull(result);
        assertEquals(FormFactor.MONOBLOCK, result.getFormFactor());
        verify(pkRepository, times(1)).findById(1L);
    }

    @Test
    void testGetById_NotFound() {
        when(pkRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> pkService.getById(1L));

        assertEquals("PK with id 1 not found", exception.getMessage());
        verify(pkRepository, times(1)).findById(1L);
    }
}
