package com.eletronicosstore.models;

public class EnderecoCliente {
    private int id;
    private String cep;
    private String logradouro;
    private String complemento;
    private String numero;
    private String bairro;
    private String localidade;
    private String uf;
    private String regiao;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
    private String tipoEndereco;
    private int status;
    private int principal;
    private int idCliente;

    public EnderecoCliente() {
    }

    public EnderecoCliente(String cep, String logradouro, String complemento, String bairro,
                           String localidade, String regiao, String tipoEndereco, int idCliente) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.regiao = regiao;
        this.tipoEndereco = tipoEndereco;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getNumero() {return numero;}
    public void setNumero(String numero) {this.numero = numero;}

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getLocalidade() { return localidade; }
    public void setLocalidade(String localidade) { this.localidade = localidade; }

    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }

    public String getRegiao() { return regiao; }

    public void setRegiao(String regiao) { this.regiao = regiao; }

    public String getIbge() { return ibge; }
    public void setIbge(String ibge) { this.ibge = ibge; }

    public String getGia() { return gia; }
    public void setGia(String gia) { this.gia = gia; }

    public String getDdd() { return ddd; }
    public void setDdd(String ddd) { this.ddd = ddd; }

    public String getSiafi() { return siafi; }
    public void setSiafi(String siafi) { this.siafi = siafi; }

    public String getTipoEndereco() { return tipoEndereco; }
    public void setTipoEndereco(String tipoEndereco) { this.tipoEndereco = tipoEndereco; }

    public int getIdCliente() { return idCliente;}
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrincipal() {
        return principal;
    }

    public void setPrincipal(int principal) {
        this.principal = principal;
    }
}
