package reflection;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("PMD.DataClass")
public class Menagerie {
  String city;
  String name;

  List<Animal> animals;

  public Menagerie(String city, String name) {
    this.city = city;
    this.name = name;
    this.animals = new ArrayList<>();
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

  public List<Animal> getAnimals() {
    return animals;
  }

  public void setAnimals(List<Animal> animals) {
    this.animals = animals;
  }

  public void add(Animal al) {
    animals.add(al);
  }
}
