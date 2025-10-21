package com.eletronicosstore.dao;

import java.util.List;

public interface Base<T> {
    T cadastrar(T input);

    T alterar(T input);

    T buscarPorId(int id);

    List<T> listarTodos(String filtro);

    T alterarStatus(T input);
}
