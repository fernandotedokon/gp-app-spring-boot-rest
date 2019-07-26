package br.com.tedokon.curso.exception;

public class NaoExisteDaoException extends RuntimeException {

    public NaoExisteDaoException(String message) {
        super(message);
    }
}
