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

        long minor = persons.stream()
                .filter(Person -> Person.getAge()<18)
                .count();

        System.out.println("Количество несовершеннолетних " + minor);

        List<String> recruit = persons.stream()
                .filter(Person -> Person.getSex() != Sex.WOMAN)
                .filter(Person -> Person.getAge() > 18 && Person.getAge() < 27)
                .map(Person -> Person.getFamily())
                .collect(Collectors.toList());


        System.out.println("Фамилии призывников " + recruit);

        List<Person> ableBodiedHighEd = persons.stream()
                .filter(Person -> Person.getEducation() == Education.HIGHER)
                .filter(Person -> Person.getSex() == Sex.WOMAN && 18 <= Person.getAge() && Person.getAge() <= 60
                        || Person.getSex() == Sex.MAN && 18 <= Person.getAge() && Person.getAge() <= 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Работоспособные люди с высшим образованием " + ableBodiedHighEd);






    }
}
