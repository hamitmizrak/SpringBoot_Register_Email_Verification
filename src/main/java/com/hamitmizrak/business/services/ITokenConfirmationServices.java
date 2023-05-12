package com.hamitmizrak.business.services;

// Generics
public interface ITokenConfirmationServices<T> {

    // CREATE
    public String createToken(T t);

    // DELETE
    public void deleteToken(Long id);
}
