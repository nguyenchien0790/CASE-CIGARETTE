package view;

import config.Config;
import controller.CompanyController;
import model.Company;

public class ViewCompany {

    CompanyController companyController = new CompanyController();

    public void menu() {
        System.out.println("*****MENU COMPANY*****");
        System.out.println("1. Show list company");
        System.out.println("2. Add company");
        System.out.println("3. Delete company");
        System.out.println("4. Update company");
        System.out.println("5. Back");

        int choice = Config.getValidInteger();

        switch (choice) {
            case 1:
                formShowList();
                break;
            case 2:
                formAdd();
                break;
            case 3:
                formDelete();
                break;
            case 4:
                formUpdate();
                break;
            case 5:
                return;
            default:
                System.out.print("Invalid choice : ");
        }
        menu();
    }

    private void formUpdate() {
        formShowList();
        System.out.println("Enter company id want to update");
        int id = Config.getValidInteger();
        System.out.print("Enter new name : ");
        String name = Config.scanner().nextLine();
        companyController.updateCompany(id, name);
    }

    private void formDelete() {
        formShowList();
        System.out.print("Enter company id to delete :");
        int id = Config.getValidInteger();
        companyController.deleteCompany(id);
    }

    private void formAdd() {
        System.out.print("Enter company name to add : ");
        String name = Config.scanner().nextLine();
        companyController.createCompany(name);
    }

    private void formShowList() {
        for (Company company : companyController.getCompanyList()) {
            System.out.println(company);
        }
    }

}
