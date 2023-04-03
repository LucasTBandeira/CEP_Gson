package com.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cep {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    @JsonProperty("cep")
    public String getCep() { return cep; }
    @JsonProperty("cep")
    public void setCep(String value) { this.cep = value; }

    @JsonProperty("logradouro")
    public String getLogradouro() { return logradouro; }
    @JsonProperty("logradouro")
    public void setLogradouro(String value) { this.logradouro = value; }

    @JsonProperty("complemento")
    public String getComplemento() { return complemento; }
    @JsonProperty("complemento")
    public void setComplemento(String value) { this.complemento = value; }

    @JsonProperty("bairro")
    public String getBairro() { return bairro; }
    @JsonProperty("bairro")
    public void setBairro(String value) { this.bairro = value; }

    @JsonProperty("localidade")
    public String getLocalidade() { return localidade; }
    @JsonProperty("localidade")
    public void setLocalidade(String value) { this.localidade = value; }

    @JsonProperty("uf")
    public String getUf() { return uf; }
    @JsonProperty("uf")
    public void setUf(String value) { this.uf = value; }

    @JsonProperty("ibge")
    public String getIbge() { return ibge; }
    @JsonProperty("ibge")
    public void setIbge(String value) { this.ibge = value; }

    @JsonProperty("gia")
    public String getGia() { return gia; }
    @JsonProperty("gia")
    public void setGia(String value) { this.gia = value; }

    @JsonProperty("ddd")
    public String getDdd() { return ddd; }
    @JsonProperty("ddd")
    public void setDdd(String value) { this.ddd = value; }

    @JsonProperty("siafi")
    public String getSiafi() { return siafi; }
    @JsonProperty("siafi")
    public void setSiafi(String value) { this.siafi = value; }

    @Override
    public String toString() {
        return "Cep [cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento + ", bairro=" + bairro
                + ", localidade=" + localidade + ", uf=" + uf + ", ibge=" + ibge + ", gia=" + gia + ", ddd=" + ddd
                + ", siafi=" + siafi + "]";
    }

    
}
