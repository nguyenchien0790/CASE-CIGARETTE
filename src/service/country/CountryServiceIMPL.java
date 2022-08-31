package service.country;

import config.Config;
import model.Company;
import model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryServiceIMPL implements ICountryService {

    static String PATH_COUNTRY = "src/database/country.txt";
    static Config<List<Country>> config = new Config<>();
    static List<Country> countryList = config.read(PATH_COUNTRY);

    static {
        if (countryList == null) {
            countryList = new ArrayList<>();
        }
    }

    @Override
    public List<Country> findAll() {
        return countryList;
    }

    @Override
    public void save(Country country) {
        countryList.add(country);
        updateData();
    }

    @Override
    public void remove(int id) {
        countryList.remove(findById(id));
        updateData();
    }

    @Override
    public Country findById(int id) {
        for (Country country : countryList) {
            if (country.getId() == id) {
                return country;
            }
        }
        return null;
    }

    @Override
    public void updateData() {
        config.write(PATH_COUNTRY, countryList);
    }

    @Override
    public int getLastId() {
        return countryList.isEmpty() ? 1 : countryList.get(countryList.size() - 1).getId() + 1;
    }

    @Override
    public void edit(Country country) {
        for (int i = 0; i < countryList.size(); i++) {
            if (countryList.get(i).getId() == country.getId()) {
                countryList.set(i, country);
                break;
            }
        }
    }

    @Override
    public Country findByName(String name) {
        for (Country country : countryList) {
            if (country.getName().equals(name)) {
                return country;
            }
        }
        return null;
    }
}
