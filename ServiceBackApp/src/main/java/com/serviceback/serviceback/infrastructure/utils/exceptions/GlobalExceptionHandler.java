package com.serviceback.serviceback.infrastructure.utils.exceptions;

import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.serviceback.serviceback.domain.dtos.ErrorCustom;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ! CUANDO NO ENCUENTRA LA URL
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorCustom> notFoundEx(NoHandlerFoundException e) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Dirección inválida 😢...");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value()); // + 404

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // ! PARAMETROS NULOS O CORRUPTOS
    @ExceptionHandler({ NullPointerException.class, HttpMessageNotWritableException.class })
    public ResponseEntity<ErrorCustom> internalErrorEx(Exception ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Ups.. Algo ha salido mal");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); // + 500

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // ! EXCEPCION PERSONALIZADA
    @ExceptionHandler()
    public ResponseEntity<ErrorCustom> globalException(GlobalExceptions ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Ups.. Algo ha salido mal");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); // + 500

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // ! CUANDO LOS DATOS SEAN INVALIDOS
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ErrorCustom> invalidDataEx(InvalidDataAccessApiUsageException ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("El valor no puede ser nulo");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); // + 500

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // ! CUANDO INGRESEN TIPOS DE DATOS NO VALIDOS
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class, NumberFormatException.class,
            HttpMessageNotReadableException.class })
    public ResponseEntity<ErrorCustom> badRequestEx(Exception e) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("El valor proporcionado no es válido.");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value()); // + 400

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ! CUANDO EL METODO DE LA PETICION ES INCORRECTO
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorCustom> methodNotAllowedEx(HttpRequestMethodNotSupportedException ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Método HTTP no permitido.");
        error.setMessage("Método '" + ex.getMethod() + "' no es soportado para esta ruta.");
        error.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value()); // + 405

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    // ! CUANDO LA CONEXION A LA DB FALLE
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorCustom> databaseConnectionEx(DataAccessException ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Error de conexión a la base de datos");
        error.setMessage("No se pudo conectar a la base de datos. Verifica que el servicio esté en funcionamiento.");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); // + 500

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // ! CUANDO EL VALOR YA EXISTE EN LA DB
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorCustom> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Violación de integridad de datos");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value()); // + 409

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // ! CUANDO EL PARAMETRO NO SE ESTA RECIBIENDO BIEN
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorCustom> handleIllegalStateException(IllegalStateException ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Error de estado ilegal");
        error.setMessage("El parametro no se esta recibiendo correctamente.");
        error.setStatus(HttpStatus.BAD_REQUEST.value()); // + 400

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ! CUANDO EL ACCESO ES DENEGADO AL SERVIDOR
    @ExceptionHandler({
            AccessDeniedException.class,
            InsufficientAuthenticationException.class,
            AuthenticationException.class
    })
    public ResponseEntity<ErrorCustom> accessDenied(Exception ex) {
        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Acceso al servidor denegado");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.FORBIDDEN.value()); // + 403

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}