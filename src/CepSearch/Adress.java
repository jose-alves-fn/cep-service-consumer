package CepSearch;

public class Adress {

    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
//    private String ibge;
//    private String gia;
//    private String ddd;
//    private String siafi;

    public Adress(AddressFields campos) {
        this.cep = campos.cep();
        this.rua = campos.logradouro();
        this.complemento = campos.complemento();
        this.bairro = campos.bairro();
        this.cidade = campos.localidade();
        this.estado = campos.uf();
    }

    // Getters and setters

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    public String getIbge() {
//        return ibge;
//    }
//
//    public void setIbge(String ibge) {
//        this.ibge = ibge;
//    }
//
//    public String getGia() {
//        return gia;
//    }
//
//    public void setGia(String gia) {
//        this.gia = gia;
//    }
//
//    public String getDdd() {
//        return ddd;
//    }
//
//    public void setDdd(String ddd) {
//        this.ddd = ddd;
//    }
//
//    public String getSiafi() {
//        return siafi;
//    }
//
//    public void setSiafi(String siafi) {
//        this.siafi = siafi;
//    }

    @Override
    public String toString() {
        return "Endereco retornado:" +
                "\n cep: " + cep +
                "\n rua: " + rua +
                "\n complemento: " + complemento +
                "\n bairro: " + bairro +
                "\n cidade: " + cidade +
                "\n estado: " + estado;
//                "\n ibge: " + ibge +
//                "\n gia: " + gia +
//                "\n ddd: " + ddd +
//                "\n siafi: " + siafi;
    }
}