package tech.ada.rflima.rpgassistant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import tech.ada.rflima.rpgassistant.dto.CampanhaDTO;

public class TestUtils {

    public static String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
