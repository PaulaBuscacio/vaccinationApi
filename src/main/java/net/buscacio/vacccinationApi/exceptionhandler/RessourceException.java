package net.buscacio.vacccinationApi.exceptionhandler;

public class RessourceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RessourceException(String message) {
        super(message);
    }
}
