package view;

import config.Config;
import controller.CigaretteController;
import controller.CompanyController;
import controller.CountryController;
import model.Cigarette;
import model.Company;

public class ViewCigarette {

    CigaretteController cigaretteController = new CigaretteController();

    public void menu() {

        System.out.println("*****MENU CIGARETTE*****");
        System.out.println("1. Show list cigarette");
        System.out.println("2. Add cigarette");
        System.out.println("3. Delete cigarette");
        System.out.println("4. Update cigarette");
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
        System.out.println("Enter cigarette id want to update");
        int id = Config.getValidInteger();
        System.out.println("Enter new cigarette name:");
        String name = Config.scanner().nextLine();
        System.out.println("Enter country");
        System.out.println(new CountryController().getCountryList());
        String country = Config.scanner().nextLine();
        System.out.println("Enter company");
        System.out.println(new CompanyController().getCompanyList());
        String company = Config.scanner().nextLine();
        System.out.println("Enter price");
        String price = Config.scanner().nextLine();
        cigaretteController.updateCigarette(id, name, country, company, price);
    }

    private void formDelete() {
        formShowList();
        System.out.println("Enter cigarette id to delete");
        int id = Config.getValidInteger();
        cigaretteController.deleteCigarette(id);
    }

    private void formAdd() {
        System.out.println("Enter cigarette name to add:");
        String name = Config.scanner().nextLine();
        System.out.println("Enter country");
        System.out.println(new CountryController().getCountryList());
        String country = Config.scanner().nextLine();
        System.out.println("Enter company");
        System.out.println(new CompanyController().getCompanyList());
        String company = Config.scanner().nextLine();
        System.out.println("Enter price");
        String price = Config.scanner().nextLine();
        cigaretteController.createCigarette(name, country, company, price);
    }

    private void formShowList() {
        for (Cigarette cigarette : cigaretteController.getCigaretteList()) {
            System.out.println(cigarette);
        }
    }

}
