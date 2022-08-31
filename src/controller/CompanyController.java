package controller;

import model.Company;
import service.company.CompanyServiceIMPL;
import service.company.ICompanyService;

import java.util.List;

public class CompanyController {

    ICompanyService companyService = new CompanyServiceIMPL();

    public List<Company> getCompanyList() {
        return companyService.findAll();
    }

    public void createCompany(String name) {
        companyService.save(new Company(companyService.getLastId(), name));
    }

    public void deleteCompany(int id) {
        companyService.remove(id);
    }

    public void updateCompany(int id, String name) {
        companyService.edit(new Company(id, name));
    }

}
