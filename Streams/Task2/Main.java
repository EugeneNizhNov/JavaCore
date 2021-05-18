package JavaCore.Streams.Task2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long countAge = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(countAge);
        List<String> surname = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 27)
                .filter(x -> x.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(surname);
        List<Person> eduction = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> (x.getAge() <= 60 && x.getSex() == Sex.WOMEN) || (x.getAge() <= 65 && x.getSex() == Sex.MAN))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(eduction);
    }
}
