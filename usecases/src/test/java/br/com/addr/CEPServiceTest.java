package br.com.addr;

import br.com.addr.adapters.AddressRepository;
import br.com.addr.exceptions.AddressNotFoundException;
import br.com.addr.exceptions.InvalidCepException;
import br.com.addr.model.Address;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CEPServiceTest {

    private CEPService service;
    private AddressRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = Mockito.mock(AddressRepository.class);
        service = new CEPService(repository);

    }

    @Test
    public void testCanFindCep() throws Exception {
        //Given
        String cep = "02729111";
        Address address = new Address("Rua da balsa 257", "Freguesia do Ó", "São Paulo", "SP");
        when(repository.findByCep(any(String.class))).thenReturn(Optional.of(address));
        //When
        Address addrFound = service.findAddress(cep);
        //Then
        assertNotNull(addrFound);
        Mockito.verify(repository).findByCep(cep);
        assertEquals(address, addrFound);
    }

    @Test(expected = AddressNotFoundException.class)
    public void testCannnotFindCep() throws Exception {
        //Given
        String cep = "02729111";
        when(repository.findByCep(any(String.class))).thenReturn(Optional.empty());
        //When
        service.findAddress(cep);
    }


    @Test
    public void testCanFindCepOnThirdInteraction() throws Exception {
        //Given
        String cep = "02729111";
        Address address = new Address("Rua da balsa 257", "Freguesia do Ó", "São Paulo", "SP");

        when(repository.findByCep(any(String.class))).thenReturn(Optional.empty(), Optional.empty(), Optional.of(address));
        //When
        Address addrFound = service.findAddress(cep);
        //Then
        assertNotNull(addrFound);
        Mockito.verify(repository).findByCep(cep);
        Mockito.verify(repository).findByCep("02729110");
        Mockito.verify(repository).findByCep("02729100");
        assertEquals(address, addrFound);
    }

    @Test(expected = InvalidCepException.class)
    public void testInvalidCep() throws Exception {
        //Given
        String cep = "027291111";
        //When
        service.findAddress(cep);
    }

}
