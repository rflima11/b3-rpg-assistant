package tech.ada.rflima.rpgassistant.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.ada.rflima.rpgassistant.exception.ErroPadrao;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErroPadrao> genericHandler(Exception e) {
        ErroPadrao erroPadrao = new ErroPadrao();
        erroPadrao.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        erroPadrao.setMessage(e.getMessage());
        return new ResponseEntity<>(erroPadrao, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
