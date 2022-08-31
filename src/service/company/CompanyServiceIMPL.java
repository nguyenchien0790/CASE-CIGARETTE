package service.company;

import config.Config;
import model.Company;
import model.Country;

import java.util.ArrayList;
import java.util.List;

public class CompanyServiceIMPL implements ICompanyService {

    static String PATH_COMPANY = "src/database/company.txt";
    static Config<List<Company>> config = new Config<>();
    static List<Company> companyList = config.read(PATH_COMPANY);

    static {
        if (companyList == null) {
            companyList = new ArrayList<>();
        }
    }

    @Override
    public List<Company> findAll() {
        return companyList;
    }

    @Override
    public void save(Company company) {
        companyList.add(company);
        updateData();
    }

    @Override
    public void remove(int id) {
        companyList.remove(findById(id));
        updateData();
    }

    @Override
    public Company findById(int id) {
        for (Company company : companyList) {
            if (company.getId() == id) {
                return company;
            }
        }
        return null;
    }

    @Override
    public void updateData() {
        config.write(PATH_COMPANY, companyList);

    }

    @Override
    public int getLastId() {
        return companyList.isEmpty() ? 1 : companyList.get(companyList.size() - 1).getId() + 1;

    }

    @Override
    public void edit(Company company) {
        for (int i = 0; i < companyList.size(); i++) {
            if (companyList.get(i).getId() == company.getId()) {
                companyList.set(i, company);
                break;
            }
        }
    }

    @Override
    public Company findByName(String name) {
        for (Company company : companyList) {
            if (company.getName().equals(name)) {
                return company;
            }
        }
        return null;
    }
}
