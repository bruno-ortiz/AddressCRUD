package br.com.addr.adapters;


import br.com.addr.model.Address;
import br.com.addr.model.UpdateAddress;

import java.util.Optional;

public interface AddressRepository {

    Optional<Address> findByCep(String cep);

    Optional<Address> findByID(String id);

    String createNewAddress(Address newAddress);

    void updateAddress(UpdateAddress updateAddress);

    void deleteAddress(String id);
}
