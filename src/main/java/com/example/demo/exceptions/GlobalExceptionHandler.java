package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterErrors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.demo.DTOs.APIResponse;


@RestControllerAdvice 
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ListaVaciaException.class)
    public ResponseEntity<APIResponse<Void>> listaVacia(ListaVaciaException exception) {
        APIResponse<Void> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DescuentoInvalidoException.class)
    public ResponseEntity<APIResponse<Void>> descuentoInvalido(DescuentoInvalidoException exception){
        APIResponse<Void> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null);

        return ResponseEntity.badRequest().body(response);      
    }

    @ExceptionHandler (DatosInvalidosExceptions.class)
    public ResponseEntity<APIResponse<Void>> datosInvalidos(DatosInvalidosExceptions exception){
        APIResponse<Void> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler (EmailExistenteException.class)
    public ResponseEntity<APIResponse<Void>> emailExistente(EmailExistenteException exception){
        APIResponse<Void> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler (ServicioExternoException.class)
    public ResponseEntity<APIResponse<Void>> servicioException(ServicioExternoException exception){
        APIResponse<Void> response = new APIResponse<>(HttpStatus.BAD_GATEWAY.value(), exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }

    

    @ExceptionHandler(ProductoNoEncontrado.class)
    public ResponseEntity<APIResponse<Void>> idInvalido(ProductoNoEncontrado exception) {
    
        return ResponseEntity.notFound().build();      
    }

    @ExceptionHandler(StockInvalido.class)
    public ResponseEntity<APIResponse<Void>> stockInvalido(StockInvalido exception){
        APIResponse<Void> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), null);

        return ResponseEntity.badRequest().body(response);      
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<APIResponse<Void>> validacionMetodoEnListas(HandlerMethodValidationException exception) {
        StringBuilder mensajeConcatenar = new StringBuilder();

        for (ParameterErrors paramErrors : exception.getBeanResults()) {
            int indice = paramErrors.getContainerIndex();
            String posicion;
            if(indice > -1){
                posicion = String.valueOf(indice);
            } else {
                posicion = "desconocida";
            }
           

            for (FieldError fieldError : paramErrors.getFieldErrors()) {
                if (mensajeConcatenar.length() > 0) {
                    mensajeConcatenar.append("; ");
                }
                mensajeConcatenar.append("Posicion[")
                        .append(posicion)
                        .append("]: el campo '")
                        .append(fieldError.getField())
                        .append("' es invalido (")
                        .append(fieldError.getDefaultMessage())
                        .append(")");
            }
        }

        String mensaje = mensajeConcatenar.toString();

        APIResponse<Void> respuesta = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), mensaje, null);
        return ResponseEntity.badRequest().body(respuesta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Void>> validacionMetodo(MethodArgumentNotValidException exception) {

        StringBuilder mensaje = new StringBuilder();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {

            if (mensaje.length() > 0) {
                mensaje.append("; ");
            }

            mensaje.append("El campo '")
                    .append(fieldError.getField())
                    .append("' es inválido (")
                    .append(fieldError.getDefaultMessage())
                    .append(")");
        }

        APIResponse<Void> respuesta = new APIResponse<>(
            HttpStatus.BAD_REQUEST.value(),
            mensaje.toString(),
            null
        );

        return ResponseEntity.badRequest().body(respuesta);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<APIResponse<Void>> handleTipoInvalido(MethodArgumentTypeMismatchException exception) {
        APIResponse<Void> respuesta = new APIResponse<>(
            HttpStatus.BAD_REQUEST.value(),
            "El parámetro '" + exception.getName() + "' tiene un valor inválido",
            null
        );
        return ResponseEntity.badRequest().body(respuesta);
    }

    @ExceptionHandler(Exception.class)
        public ResponseEntity<APIResponse<Void>> errorGeneral(Exception exception) {
            exception.printStackTrace(); 
            APIResponse<Void> respuesta = new APIResponse<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error: " + exception.getClass().getName() + " - " + exception.getMessage(),
                    null
            );
        return ResponseEntity.internalServerError().body(respuesta);
    }
}
