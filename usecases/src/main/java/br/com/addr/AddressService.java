package br.com.addr;

import br.com.addr.adapters.AddressRepository;
import br.com.addr.exceptions.AddressException;
import br.com.addr.exceptions.AddressNotFoundException;
import br.com.addr.model.Address;
import br.com.addr.model.UpdateAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressService {

    private final AddressRepository addrRepository;

    private final CEPService cepService;

    @Autowired
    public AddressService(AddressRepository addrRepository, CEPService cepService) {
        this.addrRepository = addrRepository;
        this.cepService = cepService;
    }

    public Address findAddressByID(String id) {
        return addrRepository.findByID(id)
                .orElseThrow(() -> new AddressNotFoundException("Address not found for the given ID"));
    }

    public String createNewAddress(Address newAddress) {
        return addrRepository.createNewAddress(newAddress);
    }

    public void updateAddress(UpdateAddress updateAddress) {
        Address addr = updateAddress.getAddress();
        Address cepServiceAddress = cepService.findAddress(addr.getCep());

        if (isInvalid(addr, cepServiceAddress)) {
            throw new AddressException("Address update invalid, CEP and Address data doesn't match");
        }
        addrRepository.updateAddress(updateAddress);
    }

    public void deleteAddress(String id) {
        addrRepository.deleteAddress(id);
    }

    private boolean isInvalid(Address cepServiceAddress, Address addr) {
        return !cepServiceAddress.getStreet().equals(addr.getStreet())
                || !cepServiceAddress.getUf().equals(addr.getUf())
                || !cepServiceAddress.getCity().equals(addr.getCity());
    }
}
