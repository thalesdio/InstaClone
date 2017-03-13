package com.parse.starter.util;

import java.util.HashMap;

//Tratar erros do Parse

public class ParseErros {
    private HashMap<Integer, String> erros;

    public ParseErros() {
        this.erros = new HashMap<>();
        this.erros.put(201, "Senha não preenchida"); //Pegar codigo do erro, e implementar a mensagem
        this.erros.put(202, "Usuario ja existe, escolha outro login");
    }

    //Passar o codigo e recuperar a mensagem
    public String getErro(int codErro){
        return this.erros.get(codErro);
    }
}
