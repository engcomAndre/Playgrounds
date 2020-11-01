package com.parse.starter.util;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.HashMap;

public class ParseErrors {

    private HashMap<Integer,String> errors;

    public ParseErrors() {
        this.errors = new HashMap<>();
        this.errors.put(201,"A senha não foi preenchida");
        this.errors.put(202,"Usuário já existe,escolha um outro nome de usuário");
    }

    public String getErrors(Integer codError){
        if(codError != null){
            return this.errors.get(codError);
        }
        return "Erro ao cadastrar usuário";
    }
}
