package br.com.addr.model;

public class Address {

    private final String street;
    private final Integer number;
    private final String cep;
    private final String neighborhood;
    private final String city;
    private final String uf;
    private final String complement;

    public Address(String street, Integer number, String cep, String neighborhood, String city, String uf, String complement) {
        this.street = street;
        this.number = number;
        this.cep = cep;
        this.neighborhood = neighborhood;
        this.city = city;
        this.uf = uf;
        this.complement = complement;
    }

    public Address(String street, String neighborhood, String city, String uf) {
        this(street, null, null, neighborhood, city, uf, null);
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

    public String getComplement() {
        return complement;
    }
}
