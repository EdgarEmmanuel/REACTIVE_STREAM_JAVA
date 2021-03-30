package dao;

import models.Animal;
import models.Dog;

import java.util.ArrayList;
import java.util.List;

public class Database {

    public List<Animal> getAllAnimal(){
        List<Animal>  animals = new ArrayList<>();
        animals.add(new Dog("dog1","fam1"));
        animals.add(new Dog("dog2","fam2"));
        return animals;
    }

}
