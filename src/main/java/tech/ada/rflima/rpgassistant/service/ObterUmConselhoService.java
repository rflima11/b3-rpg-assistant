package tech.ada.rflima.rpgassistant.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ObterUmConselhoService {

    private final RestTemplate restTemplate;

    public ObterUmConselhoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String executar() {
        try {
            String url = "https://api.adviceslip.com/advice";
            ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
            if (Objects.nonNull(entity.getBody())) {
                return entity.getBody();
            }
            return null;
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Você fez algo errado!", e);
        } catch (HttpServerErrorException e) {
            throw new RuntimeException("Erro Interno de servidor", e);
        }
    }
}
