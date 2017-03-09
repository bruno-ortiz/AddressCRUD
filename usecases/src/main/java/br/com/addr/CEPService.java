package br.com.addr;

import br.com.addr.adapters.AddressRepository;
import br.com.addr.exceptions.AddressNotFoundException;
import br.com.addr.exceptions.InvalidCepException;
import br.com.addr.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CEPService {

    private final AddressRepository addrRepository;

    @Autowired
    public CEPService(AddressRepository addrRepository) {
        this.addrRepository = addrRepository;
    }

    public Address findAddress(String cep) {
        if (cep.length() != 8) {
            throw new InvalidCepException("CEP inválido");
        }
        int zeroIdx = cep.length() - 1;
        String suffix = "";
        Optional<Address> address;
        do {
            address = this.addrRepository.findByCep(cep);
            suffix += "0";
            cep = cep.substring(0, zeroIdx--) + suffix;
        } while (!address.isPresent() && zeroIdx >= 0);

        return address.orElseThrow(() -> new AddressNotFoundException("Cep não encontrado!"));
    }

}
