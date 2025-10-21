package com.eletronicosstore.service;

import com.eletronicosstore.dto.EnderecoDto;
import com.eletronicosstore.models.EnderecoCliente;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViaCep {

    public EnderecoDto buscarEnderecoPorCep(String cep) throws Exception {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        URL obj = new URL(url);
        HttpURLConnection conexao = (HttpURLConnection) obj.openConnection();
        conexao.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream(), "UTF-8"));
        StringBuilder resposta = new StringBuilder();
        String linha;
        while ((linha = in.readLine()) != null) {
            resposta.append(linha);
        }
        in.close();

        Gson gson = new Gson();
        return gson.fromJson(resposta.toString(), EnderecoDto.class);
    }
}
