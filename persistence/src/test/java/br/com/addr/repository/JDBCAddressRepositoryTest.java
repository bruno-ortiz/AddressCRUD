package br.com.addr.repository;

import br.com.addr.AbstractDBTestCase;
import br.com.addr.exceptions.AddressException;
import br.com.addr.model.Address;
import br.com.addr.model.UpdateAddress;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.*;

public class JDBCAddressRepositoryTest extends AbstractDBTestCase {

    @Autowired
    private JDBCAddressRepository repository;


    @Test
    public void testCanFindCEP() throws Exception {
        //Given minimal DB loaded with bootstrap.sql
        //When
        Optional<Address> optional = repository.findByCep("01234456");
        //Then
        assertTrue(optional.isPresent());

        Address addr = optional.get();

        assertEquals(addr.getStreet(), "Av. Paulista");
    }


    @Test
    public void testCannotFindCEP() throws Exception {
        //Given minimal DB loaded with bootstrap.sql
        //When
        Optional<Address> optional = repository.findByCep("invalid cep");
        //Then
        assertFalse(optional.isPresent());
    }

    @Test
    public void testCanFindAddressByID() throws Exception {
        //Given minimal DB loaded with bootstrap.sql
        //When
        Optional<Address> optional = repository.findByID("bb79a9f2-0ebc-4537-a0df-e39591039008");
        //Then
        assertTrue(optional.isPresent());

        Address addr = optional.get();

        assertEquals(addr.getStreet(), "Av. Paulista");
    }

    @Test
    public void testCannotFindAddressByID() throws Exception {
        //Given minimal DB loaded with bootstrap.sql
        //When
        Optional<Address> optional = repository.findByID("invalid-ID");
        //Then
        assertFalse(optional.isPresent());
    }

    @Test
    public void testCanInsertNewAddress() throws Exception {
        //Given minimal DB loaded with bootstrap.sql
        Address newAddress = new Address("Rua teste", 123, "01345656", null, "Sampa", "SP", null);
        //When
        String id = repository.createNewAddress(newAddress);
        //Then
        assertNotNull(id);

        Optional<Address> addr = repository.findByID(id);

        assertTrue(addr.isPresent());
    }

    @Test(expected = AddressException.class)
    public void testCannotInsertDuplicateCEP() throws Exception {
        //Given minimal DB loaded with bootstrap.sql
        Address newAddress1 = new Address("Rua teste", 123, "01345656", null, "Sampa", "SP", null);
        Address newAddress2 = new Address("Rua teste", 123, "01345656", null, "Sampa", "SP", null);
        //When
        repository.createNewAddress(newAddress1);
        repository.createNewAddress(newAddress2);
    }

    @Test
    public void testCanUpdateAddress() throws Exception {
        //Given minimal DB loaded with bootstrap.sql
        Address newAddress = new Address("Rua teste", 123, "01345656", null, "Sampa", "SP", null);
        Address updatedAddr = new Address("Rua teste", 159, "01345656", null, "Sampa", "SP", null);

        //When
        String id = repository.createNewAddress(newAddress);
        //Then
        assertNotNull(id);
        Optional<Address> addr = repository.findByID(id);
        assertTrue(addr.isPresent());
        assertEquals(123, addr.get().getNumber().intValue());

        //When
        repository.updateAddress(new UpdateAddress(id, updatedAddr));
        //Then
        Optional<Address> updated = repository.findByID(id);
        assertTrue(updated.isPresent());
        assertEquals(159, updated.get().getNumber().intValue());
    }


    @Test
    public void testCanDeleteAddress() throws Exception {
        //Given minimal DB loaded with bootstrap.sql
        Address newAddress = new Address("Rua teste", 123, "01345656", null, "Sampa", "SP", null);
        //When
        String id = repository.createNewAddress(newAddress);
        //Then
        assertNotNull(id);

        Optional<Address> addr = repository.findByID(id);

        assertTrue(addr.isPresent());

        //When
        repository.deleteAddress(id);
        addr = repository.findByID(id);
        assertFalse(addr.isPresent());
    }

}
