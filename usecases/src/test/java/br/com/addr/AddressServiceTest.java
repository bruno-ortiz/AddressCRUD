package br.com.addr;

import br.com.addr.adapters.AddressRepository;
import br.com.addr.exceptions.AddressNotFoundException;
import br.com.addr.model.Address;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class AddressServiceTest {


    private CEPService service;
    private AddressRepository repository;
    private AddressService addressService;

    @Before
    public void setUp() throws Exception {
        repository = Mockito.mock(AddressRepository.class);
        service = new CEPService(repository);
        addressService = new AddressService(repository, service);
    }

    @Test
    public void testCanFindByID() throws Exception {
        //Given
        String id = "um-id-de-exemplo";
        Address address = new Address("Rua da balsa", 145, "024534000", "Freguesia do Ó", "São Paulo", "SP", "apto 12");
        when(repository.findByID(id)).thenReturn(Optional.of(address));
        //When
        Address addrFound = addressService.findAddressByID(id);
        //Then
        assertNotNull(addrFound);
        Mockito.verify(repository).findByID(id);
        assertEquals(address, addrFound);
    }

    @Test(expected = AddressNotFoundException.class)
    public void testCannotFindByID() throws Exception {
        //Given
        String id = "um-id-de-exemplo";
        when(repository.findByID(id)).thenReturn(Optional.empty());
        //When
        addressService.findAddressByID(id);
    }

    @Test
    public void testCanCreateAddress() throws Exception {
        //Given
        String id = "um-id-de-exemplo";
        Address address = new Address("Rua da balsa", 145, "024534000", "Freguesia do Ó", "São Paulo", "SP", "apto 12");
        when(repository.createNewAddress(address)).thenReturn(id);
        //When
        String newID = addressService.createNewAddress(address);
        //Then
        assertNotNull(newID);
        Mockito.verify(repository).createNewAddress(address);
        assertEquals(id, newID);
    }


    @Test
    public void testDeleteAddress() throws Exception {
        //Given
        String id = "um-id-de-exemplo";
        //When
        addressService.deleteAddress(id);
        //Then
        Mockito.verify(repository).deleteAddress(id);
    }
}
