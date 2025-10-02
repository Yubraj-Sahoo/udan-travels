package com.raj.travels.udan.connection_service.mock;

import com.raj.travels.commons.converters.JsonConverter;
import com.raj.travels.commons.enums.Pseudocode;
import com.raj.travels.udan.connection_service.db.entities.ConnectionCredentials;
import com.raj.travels.udan.connection_service.db.repositories.ConnectionCredentialsRepository;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ConnectionCredentialsRepositoryTest {
    @MockitoBean
    private ConnectionCredentialsRepository connectionCredentialsRepository;

    @PreDestroy
    public void resetMock() {
        Mockito.reset(connectionCredentialsRepository);
    }

    @Test
    void testFindByPseudocode() {
        ConnectionCredentials mockResult = JsonConverter.fromJsonFile(
                "src/test/resources/json/connection-credentials.json", ConnectionCredentials.class);
        Pseudocode pseudocode = Pseudocode.UDAN;

        when(connectionCredentialsRepository.findByPseudocode(any())).thenReturn(mockResult);

        ConnectionCredentials returnedResult = connectionCredentialsRepository.findByPseudocode(pseudocode);

        verify(connectionCredentialsRepository, times(1)).findByPseudocode(any());

        assertNotNull(returnedResult);
        assertEquals(mockResult.getId(), returnedResult.getId());
        assertEquals(mockResult.getPseudocode(), returnedResult.getPseudocode());
        assertEquals(mockResult.getApiKey(), returnedResult.getApiKey());
        assertEquals(mockResult.getApiSecret(), returnedResult.getApiSecret());
    }

    @Test
    void testFindByPseudocode_NotFound() {
        Pseudocode pseudocode = Pseudocode.UDAN;

        when(connectionCredentialsRepository.findByPseudocode(any())).thenReturn(null);

        ConnectionCredentials returnedResult = connectionCredentialsRepository.findByPseudocode(pseudocode);

        verify(connectionCredentialsRepository, times(1)).findByPseudocode(any());

        assertNull(returnedResult);
    }
}
