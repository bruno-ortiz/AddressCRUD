package br.com.addr.model;

public class UpdateAddress {
    private final String addressID;
    private final Address address;

    public UpdateAddress(String addressID, Address address) {
        this.addressID = addressID;
        this.address = address;
    }

    public String getAddressID() {
        return addressID;
    }

    public Address getAddress() {
        return address;
    }
}
