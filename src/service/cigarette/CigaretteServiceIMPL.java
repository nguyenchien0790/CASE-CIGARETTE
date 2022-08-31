package service.cigarette;

import config.Config;
import model.Cigarette;

import java.util.ArrayList;
import java.util.List;

public class CigaretteServiceIMPL implements ICigaretteService {
    static String PATH_CIGARETTE = "src/database/cigarette.txt";
    static Config<List<Cigarette>> config = new Config<>();
    static List<Cigarette> cigaretteList = config.read(PATH_CIGARETTE);

    static {
        if (cigaretteList == null) {
            cigaretteList = new ArrayList<>();
        }
    }

    @Override
    public List<Cigarette> findAll() {
        return cigaretteList;
    }

    @Override
    public void save(Cigarette cigarette) {
        cigaretteList.add(cigarette);
        updateData();
    }

    @Override
    public void remove(int id) {
        cigaretteList.remove(findById(id));
        updateData();
    }

    @Override
    public Cigarette findById(int id) {
        for (Cigarette cigarette : cigaretteList) {
            if (cigarette.getId() == id) {
                return cigarette;
            }
        }
        return null;
    }

    @Override
    public void updateData() {
        config.write(PATH_CIGARETTE, cigaretteList);
    }

    @Override
    public int getLastId() {
        return cigaretteList.isEmpty() ? 1 : cigaretteList.get(cigaretteList.size() - 1).getId() + 1;
    }

    @Override
    public void edit(Cigarette cigarette) {
        for (int i = 0; i < cigaretteList.size(); i++) {
            if (cigaretteList.get(i).getId() == cigarette.getId()) {
                cigaretteList.set(i, cigarette);
                break;
            }
        }
    }

    @Override
    public Cigarette findByName(String name) {
        for (Cigarette cigarette : cigaretteList) {
            if (cigarette.getName().equals(name)) {
                return cigarette;
            }
        }
        return null;
    }
}