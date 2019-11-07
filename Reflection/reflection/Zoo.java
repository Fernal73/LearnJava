package reflection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("PMD.ShortClassName")
public class Zoo {
  String city;
  String name;

  Animal[] animals;

  public Zoo(String city, String name) {
    this.city = city;
    this.name = name;
    this.animals = new Animal[] {};
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Animal[] getAnimals() {
    return animals.clone();
  }

  public void setAnimals(List<Animal> animals) {
    this.animals = animals.toArray(this.animals);
  }

  public void add(Animal al) {
    List<Animal> beasts = new ArrayList<>(Arrays.asList(this.animals));
    beasts.add(al);
    this.animals = beasts.toArray(this.animals);
  }
}