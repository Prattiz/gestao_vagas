package br.com.thiagopratti.gestao_vagas.exceptions;

public class UserFoundExceptions extends RuntimeException{
    public UserFoundExceptions(){
        super("User already exists");
    }
}
