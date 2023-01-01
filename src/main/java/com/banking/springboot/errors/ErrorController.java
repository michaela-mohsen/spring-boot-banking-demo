package com.banking.springboot.errors;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@ControllerAdvice
@RequestMapping
public class ErrorController {

    @ExceptionHandler({ ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolation(@ModelAttribute("exception") ConstraintViolationException ex,
            WebRequest request,
            @ModelAttribute("status") HttpStatus status, Model model) {

        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        model.addAttribute("errors", errors);
        model.addAttribute("message", ex.getMessage());
        return "error";

    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentTypeMismatch(
            @ModelAttribute("exception") MethodArgumentTypeMismatchException ex, WebRequest request,
            @ModelAttribute("status") HttpStatus status, Model model) {

        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        model.addAttribute("error", error);
        model.addAttribute("exception", ex.toString());
        model.addAttribute("message", ex.getMessage());

        return "error";
    }

    @ExceptionHandler({ NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoHandlerFoundException(
            @ModelAttribute("exception") NoHandlerFoundException ex, @ModelAttribute("status") HttpStatus status,
            WebRequest request, Model model) {

        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        model.addAttribute("error", error);
        model.addAttribute("exception", ex.toString());
        model.addAttribute("message", ex.getMessage());

        return "error";
    }

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            @ModelAttribute("status") HttpStatus status,
            WebRequest request, Model model) {

        String error = ex.getMethod() + " method is not supported for this request.";

        model.addAttribute("error", error);
        model.addAttribute("exception", ex.toString());
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleAll(@ModelAttribute("exception") Exception ex, WebRequest request,
            @ModelAttribute("status") HttpStatus status, Model model) {

        String error = "Unknown error has occurred.";

        model.addAttribute("error", error);
        model.addAttribute("exception", ex.toString());
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("trace", ex.getStackTrace());
        return "error";

    }

    @ExceptionHandler({ NoSuchElementException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleNoSuchElementException(NoSuchElementException ex,
            WebRequest request,
            @ModelAttribute("status") HttpStatus status, Model model) {

        String error = "No data result founds.";

        model.addAttribute("error", error);
        model.addAttribute("exception", ex.toString());
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("trace", ex.getStackTrace());
        return "error";
    }

    @ExceptionHandler({ HttpClientErrorException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleForbiddenException(HttpClientErrorException ex, WebRequest request,
            @ModelAttribute("status") HttpStatus status, Model model) {
        String error = "Access forbidden.";

        model.addAttribute("error", error);
        model.addAttribute("exception", ex.toString());
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("trace", ex.getStackTrace());
        return "error";
    }

    @ExceptionHandler({ NullPointerException.class })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleNullPointerException(NullPointerException ex, WebRequest request,
            @ModelAttribute("status") HttpStatus status, Model model) {
        String error = "No object found.";

        model.addAttribute("error", error);
        model.addAttribute("exception", ex.toString());
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("trace", ex.getStackTrace());
        return "error";
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleDataIntegrityViolationException(@ModelAttribute("exception") DataIntegrityViolationException ex,
            WebRequest request,
            @ModelAttribute("status") HttpStatus status, Model model) {

        String error = "Customer already exists.";

        model.addAttribute("error", error);
        model.addAttribute("exception", ex.toString());
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("trace", ex.getStackTrace());
        return "error";

    }
}
