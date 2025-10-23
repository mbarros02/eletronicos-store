package com.eletronicosstore.service;

import com.eletronicosstore.models.EnderecoCliente;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViaCep {

    public EnderecoCliente buscarEnderecoPorCep(String cep) throws Exception {
        cep = cep.replaceAll("\\D", ""); // remove hífens e espaços

        if (!cep.matches("\\d{8}")) {
            throw new IllegalArgumentException("Formato de CEP inválido!");
        }

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

        System.out.println("JSON recebido do ViaCep: " + resposta);

        if (resposta.toString().contains("\"erro\": true")) {
            throw new IllegalArgumentException("CEP inválido ou não encontrado!");
        }

        Gson gson = new Gson();
        return gson.fromJson(resposta.toString(), EnderecoCliente.class);
    }
}
	