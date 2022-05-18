package ru.ulstu.is.sbapp.carstoowner.model;

public class DopCar {
    String model;
    String owner_first_name;
    String owner_last_name;
    String sto_name;

    public DopCar(String model, String owner_first_name, String owner_last_name,
                  String sto_name) {
        this.model = model;
        this.owner_first_name = owner_first_name;
        this.owner_last_name = owner_last_name;
        this.sto_name = sto_name;
    }

    public String getModel() {
        return model;
    }

    public String getOwner_first_name() {
        return owner_first_name;
    }

    public String getOwner_last_name() {
        return owner_last_name;
    }

    public String getSto_name() {
        return sto_name;
    }
}
