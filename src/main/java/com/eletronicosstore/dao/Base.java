package com.eletronicosstore.dao;

public interface Base<T> {

    public T cadastrar(T input);

    public T alterar(T input);

}
