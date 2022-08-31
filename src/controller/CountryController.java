package controller;

import model.Country;
import service.country.CountryServiceIMPL;
import service.country.ICountryService;

import java.util.List;

public class CountryController {

    ICountryService countryService = new CountryServiceIMPL();

    public List<Country> getCountryList() {
        return countryService.findAll();
    }

    public void createCountry(String name) {
        countryService.save(new Country(countryService.getLastId(), name));
    }

    public void deleteCountry(int id) {
        countryService.remove(id);
    }

    public void updateCountry(int id, String name) {
        countryService.edit(new Country(id, name));
    }

}
