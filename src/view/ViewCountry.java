package view;

import config.Config;
import controller.CountryController;
import model.Company;
import model.Country;

public class ViewCountry {

    CountryController countryController = new CountryController();

    public void menu() {
        System.out.println("*****MENU COUNTRY*****");
        System.out.println("1. Show list country");
        System.out.println("2. Add country");
        System.out.println("3. Delete country");
        System.out.println("4. Update country");
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
        System.out.print("Enter country id want to update : ");
        int id = Config.getValidInteger();
        System.out.print("Enter new name : ");
        String name = Config.scanner().nextLine();
        countryController.updateCountry(id, name);
    }

    private void formDelete() {
        formShowList();
        System.out.print("Enter country id to delete : ");
        int id = Config.getValidInteger();
        countryController.deleteCountry(id);
    }

    private void formAdd() {
        System.out.print("Enter country name to add : ");
        String name = Config.scanner().nextLine();
        countryController.createCountry(name);
    }

    private void formShowList() {
        for (Country country : countryController.getCountryList()) {
            System.out.println(country);
        }
    }

}
