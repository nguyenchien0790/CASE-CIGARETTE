package controller;

import model.Cigarette;
import model.Company;
import service.cigarette.CigaretteServiceIMPL;
import service.cigarette.ICigaretteService;
import service.company.CompanyServiceIMPL;
import service.company.ICompanyService;
import service.country.CountryServiceIMPL;
import service.country.ICountryService;

import java.util.List;

public class CigaretteController {
    ICigaretteService cigaretteService = new CigaretteServiceIMPL();
    ICountryService countryService = new CountryServiceIMPL();
    ICompanyService companyService = new CompanyServiceIMPL();

    public void updateCigarette(int id, String name, String country, String company, String price) {
        cigaretteService.edit(new Cigarette(id, name, countryService.findByName(country), companyService.findByName(company), Double.parseDouble(price)));
    }

    public void deleteCigarette(int id) {
        cigaretteService.remove(id);
    }

    public void createCigarette(String name, String country, String company, String price) {
        cigaretteService.save(new Cigarette(cigaretteService.getLastId(), name, countryService.findByName(country), companyService.findByName(company), Double.parseDouble(price)));
    }

    public List<Cigarette> getCigaretteList() {
        return cigaretteService.findAll();
    }
}
