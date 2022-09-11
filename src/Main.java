import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        final int POPULATION = 10_000_000;
        final int ADULT_AGE = 18;
        final int AFTER_ARMY_AGE = 27;
        final int RETIREMENT_MEN_AGE = 65;
        final int RETIREMENT_WOMEN_AGE = 60;

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < POPULATION; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Long underage = persons.stream()
                .filter(age -> age.getAge() < ADULT_AGE)
                .count();
        System.out.println(underage);

        List<String> conscripts = persons.stream()
                .filter(sex -> sex.getSex() == Sex.MAN)
                .filter(age -> age.getAge() >= ADULT_AGE)
                .filter(age -> age.getAge() < AFTER_ARMY_AGE)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println(conscripts);
        List<Person> jobable = persons.stream()
                .filter(education -> education.getEducation() == Education.HIGHER)
                .filter(age -> age.getAge() >= ADULT_AGE)
                .filter(person -> (person.getAge() < RETIREMENT_WOMEN_AGE && person.getSex() == Sex.WOMAN) ||
                        (person.getAge() < RETIREMENT_MEN_AGE && person.getSex() == Sex.MAN))
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());
//        for (Person p : jobable) {
//            System.out.println(p);
//        }
    }

}