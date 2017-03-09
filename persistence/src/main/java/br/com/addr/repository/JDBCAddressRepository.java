package br.com.addr.repository;

import br.com.addr.adapters.AddressRepository;
import br.com.addr.exceptions.AddressException;
import br.com.addr.model.Address;
import br.com.addr.model.UpdateAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.UUID;

@Component
public class JDBCAddressRepository implements AddressRepository {

    private final DataSource dataSource;

    @Autowired
    public JDBCAddressRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Address> findByCep(String cep) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        try {
            Address address = template.queryForObject(
                    "SELECT STREET street, NEIGHBORHOOD neighborhood, CITY city, UF uf FROM PUBLIC.ADDRESS WHERE CEP = ? LIMIT 1",
                    new Object[]{cep},
                    (rs, n) -> {
                        String street = rs.getString("street");
                        String neighborhood = rs.getString("neighborhood");
                        String city = rs.getString("city");
                        String uf = rs.getString("uf");
                        return new Address(street, neighborhood, city, uf);
                    }
            );
            return Optional.of(address);
        } catch (DataRetrievalFailureException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Address> findByID(String id) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        try {
            Address address = template.queryForObject(
                    "SELECT STREET street," +
                            " NUMBER number, " +
                            "CEP cep, " +
                            "NEIGHBORHOOD neighborhood," +
                            " CITY city," +
                            " UF uf," +
                            " COMPLEMENT complement " +
                            "FROM PUBLIC.ADDRESS WHERE ID = ?",
                    new Object[]{id},
                    (rs, n) -> {
                        String street = rs.getString("street");
                        Integer number = rs.getInt("number");
                        String cep = rs.getString("cep");
                        String neighborhood = rs.getString("neighborhood");
                        String city = rs.getString("city");
                        String uf = rs.getString("uf");
                        String complement = rs.getString("complement");
                        return new Address(street, number, cep, neighborhood, city, uf, complement);
                    }
            );
            return Optional.of(address);
        } catch (DataRetrievalFailureException e) {
            return Optional.empty();
        }
    }

    @Override
    public String createNewAddress(Address newAddress) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        try {
            UUID newUUID = UUID.randomUUID();
            template.update("INSERT INTO ADDRESS(ID, STREET, NUMBER, CEP, CITY, UF, NEIGHBORHOOD, COMPLEMENT) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    newUUID.toString(),
                    newAddress.getStreet(),
                    newAddress.getNumber(),
                    newAddress.getCep(),
                    newAddress.getCity(),
                    newAddress.getUf(),
                    newAddress.getNeighborhood(),
                    newAddress.getComplement());
            return newUUID.toString();
        } catch (DataAccessException e) {
            throw new AddressException("Could not insert Address", e);
        }
    }

    @Override
    public void updateAddress(UpdateAddress updateAddress) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        Address addr = updateAddress.getAddress();
        try {
            template.update("UPDATE ADDRESS SET STREET = ?, NUMBER= ?, CEP =?, CITY=?, UF=?, NEIGHBORHOOD=?, COMPLEMENT=?" +
                            "WHERE ID=?",
                    addr.getStreet(),
                    addr.getNumber(),
                    addr.getCep(),
                    addr.getCity(),
                    addr.getUf(),
                    addr.getNeighborhood(),
                    addr.getComplement(),
                    updateAddress.getAddressID());
        } catch (DataAccessException e) {
            throw new AddressException("Could not update Address", e);
        }
    }

    @Override
    public void deleteAddress(String id) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        try {
            template.update("DELETE FROM ADDRESS WHERE ID=?", id);
        } catch (DataAccessException e) {
            throw new AddressException("Could not delete Address", e);
        }
    }
}
