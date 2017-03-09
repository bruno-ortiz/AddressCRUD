package br.com.addr.spark.mappings;

import br.com.addr.AddressService;
import br.com.addr.CEPService;
import br.com.addr.exceptions.AddressException;
import br.com.addr.exceptions.AddressNotFoundException;
import br.com.addr.exceptions.InvalidCepException;
import br.com.addr.model.Address;
import br.com.addr.model.ApiError;
import br.com.addr.model.UpdateAddress;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static spark.Spark.*;

@Component
public class UrlMappings {

    private static final Gson gson = new Gson();

    private final CEPService cepService;
    private final AddressService addressService;

    @Autowired
    public UrlMappings(CEPService cepService, AddressService addressService) {
        this.cepService = cepService;
        this.addressService = addressService;
    }

    public void mapEndpoints() {

        get("/health", (request, response) -> "Health OK!");

        get("/address/:cep", (request, response) -> {
            String cep = request.params("cep");
            return cepService.findAddress(cep);
        }, gson::toJson);

        get("/address/id/:id", (request, response) -> {
            String id = request.params("id");
            return addressService.findAddressByID(id);
        }, gson::toJson);

        post("/address", (request, response) -> {
            UpdateAddress addr = gson.fromJson(request.body(), UpdateAddress.class);
            addressService.updateAddress(addr);
            return "";
        });

        put("/address", (request, response) -> {
            Address newAddress = gson.fromJson(request.body(), Address.class);
            String addressID = addressService.createNewAddress(newAddress);
            response.status(201);
            return Collections.singletonMap("addressID", addressID);
        }, gson::toJson);

        delete("/address/id/:id", (request, response) -> {
            String id = request.params("id");
            addressService.deleteAddress(id);
            return "";
        });

        exception(AddressNotFoundException.class, ((exception, request, response) -> {
            response.status(404);
            ApiError error = new ApiError(exception.getMessage());
            response.body(gson.toJson(error));
        }));

        exception(InvalidCepException.class, ((exception, request, response) -> {
            response.status(400);
            ApiError error = new ApiError(exception.getMessage());
            response.body(gson.toJson(error));
        }));

        exception(AddressException.class, ((exception, request, response) -> {
            response.status(422);
            ApiError error = new ApiError(exception.getMessage());
            response.body(gson.toJson(error));
        }));

        after((request, response) -> response.type("application/json"));

    }

}
