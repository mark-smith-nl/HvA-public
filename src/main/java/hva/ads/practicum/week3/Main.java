package hva.ads.practicum.week3;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public class Main {

    public static void main(String[] args) {
        SmallList<String> list = new SmallArrayList<>(1);
        System.out.println(list);
        list.add("Mark");
        list.add("Pieter");
        list.add("Jan", "Kees", "Karel");

        System.out.println(list.size());
        for (String item : list) {
            System.out.println(item);
        }
        System.out.println(list.find("Pieter"));
        System.out.println(list.find("Karel"));
        System.out.println(list.find("Oscar"));
        System.out.println(list.find("Mark"));

        System.out.println(list.get(1));
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        System.out.println(list.size());
        list.remove(0);
        System.out.println(list);
        System.out.println(list.size());
        list.remove(0);
        System.out.println(list);
        System.out.println(list.size());
        list.remove(list.size() - 1);
        System.out.println(list);
        System.out.println(list.size());
        list.remove(list.size() - 1);
        System.out.println(list);
        System.out.println(list.size());
    }
}
