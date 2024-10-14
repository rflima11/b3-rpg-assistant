package tech.ada.rflima.rpgassistant.unit.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import tech.ada.rflima.rpgassistant.service.ObterUmConselhoService;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ObterUmConselhoServiceTest {

    @InjectMocks
    ObterUmConselhoService obterUmConselhoService;

    @Mock
    RestTemplate restTemplate;

    @Test
    void deveRetornarQuandoOStatusCodeFor200() {
        //Cenário
        ResponseEntity<String> retorno = new ResponseEntity<>(
                "{\"slip\": { \"id\": 148, \"advice\": \"Some people would be better off if they took their own advice.\"}}", HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(String.class)))
                .thenReturn(retorno);

        //Execução
        String executar = obterUmConselhoService.executar();

        //Verificação
        Assertions.assertNotNull(executar);
        Assertions.assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void deveLancarExcecaoQuandoObterUmErro() {
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> obterUmConselhoService.executar());

        Assertions.assertNotNull(runtimeException);
    }

    @Test
    void deveLancarExcecaoQuandoObterUmErro500() {
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(String.class)))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> obterUmConselhoService.executar());

        Assertions.assertNotNull(runtimeException);
        Assertions.assertNotNull(runtimeException.getMessage());
        Assertions.assertEquals("Erro Interno de servidor", runtimeException.getMessage());
    }
}