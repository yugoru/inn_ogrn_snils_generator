import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

// Генератор ИНН физлица (Россия)
public class GeneratorINNFL {

    public static String generateInn() {
        LinkedList<Integer> nums = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            nums.add(randomNumbers()[i]);
        }
        int n2 = getInnControlSum(nums, "n2_12");
        nums.add(n2);
        int n1 = getInnControlSum(nums, "n1_12");
        nums.add(n1);
        return nums.stream()
                .map(Object::toString)
                .collect(joining(""));
    }

    public static Integer[] randomNumbers() {
        StringBuilder innGen = new StringBuilder();
        int region = (int) (10 + Math.random() * 75); // Регион регистрации
        int inspection = (int) (10 + Math.random() * 89); //Код налоговой инспекции
        int number = (int) (111111 + Math.random() * 777777); //Номер записи регистрации
        for (var i : new int[]{region, inspection, number}) {
            String s = String.valueOf(i);
            innGen.append(s);
        }
        Integer[] innGenToMassive = new Integer[innGen.length()];
        for (int j = 0; j < innGen.length(); j++) {
            innGenToMassive[j] = innGen.charAt(j) - '0';
        }
        return innGenToMassive;
    }

    public static Integer getInnControlSum(List<Integer> nums, String type) {
        Map<String, List<Integer>> innControlType = Map.of(
                "n2_12", List.of(7, 2, 4, 10, 3, 5, 9, 4, 6, 8),
                "n1_12", List.of(3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8)
        );
        int n = 0;
        List<Integer> l = innControlType.get(type);
        for (int i = 0; i < l.size(); i++) {
            n += nums.get(i) * l.get(i);
        }
        return n % 11 % 10;
    }
}