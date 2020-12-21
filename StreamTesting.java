import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTesting {

    static class Employee {
        String name;
        Long salary;

        Employee (String name, Long salary) {
            this.name = name;
            this.salary = salary;
        }

        String getName() {
            return name;
        }

        Long getSalary() {
            return salary;
        }
    }

    static class Department {
        String name;
        String code;
        List<Employee> employees;

        Department (String name, String code, List<Employee> employees) {
            this.name = name;
            this.code = code;
            this.employees = employees;
        }

        String getDepName() {
            return name;
        }

        String getCode() {
            return code;
        }

        List<Employee> getEmployees() {
            return employees;
        }
    }

    /**
     * Check if number is prime
     * @param number
     * @return
     */
    public static boolean isPrime (final long number) {
        return LongStream.range(2, number)
                .noneMatch(x -> number % x == 0);
    }

    /**
     * Stream that will detect bad words in a text according to a bad words list
     * @param text
     * @param badWords
     * @return
     */
    public static Stream<String> createBadWordsDetectingStream(String text, List<String> badWords) {
        String[] words = text.split(" ");
        List<String> result = Arrays.stream(words).filter(badWords::contains)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        return result.stream();
    }

    /**
     * Create the third stream that contains numbers from both streams which is divisible by 3 and 5
     * @param evenStream
     * @param oddStream
     * @return
     */
    public static IntStream createFilteringStream(IntStream evenStream, IntStream oddStream) {
        return IntStream.concat(evenStream, oddStream)
                .filter(x -> x % 3 == 0 & x % 5 == 0)
                .sorted()
                .skip(2);
    }

    /**
     * Calculating factorial with streams
     * @param n
     * @return
     */
    public static long factorial(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (acc, elem) -> acc *= elem);
    }

    /**
     * Calculating the sum of odd numbers in a range
     * @param start
     * @param end
     * @return
     */
    public static long sumOfOddNumbersInRange(long start, long end) {
        return LongStream.rangeClosed(start, end)
                .filter(x -> x % 2 != 0)
                .reduce(0, Long::sum);
    }

    /**
     * Calculating the number of employees from department "111" with salary greater then "threshold"
     * @param departments
     * @param threshold
     * @return
     */
    public static long calcNumberOfEmployees(List<Department> departments, long threshold) {
         return departments.stream()
                .filter(x -> x.getCode().startsWith("111-"))
                .flatMap(x -> x.getEmployees().stream())
                .filter(x -> x.getSalary() >= threshold)
                .count();
    }

    public static void main(String[] args) {
        // list
        final List<Integer> numberList = Arrays.asList(1, 2, 3, 4);

        // from List<Integer> to IntStream
        final IntStream stream = numberList.stream().mapToInt(x -> x);

        // from IntStream to List<Integer>
        final List<Integer> anotherNumbers = stream.boxed().collect(Collectors.toList());

        List<String> words = new ArrayList<>();
        words.add("today");
        words.add("cat");
        words.add("book");
        words.add("tomorrow");
        words.add("level");
        String concat = words.stream()
                .filter(x -> x.length() > 4)
                .distinct()
                .map(String::toUpperCase)
                .reduce("",String::concat);

        List<String> badWords = new ArrayList<>();
        badWords.add("pen");
        badWords.add("pencil");
        badWords.add("dog");
        createBadWordsDetectingStream("cat dog book pen", badWords);

        IntStream even = Arrays.stream(new int[] {30, 60, 90});
        IntStream odd = Arrays.stream(new int[] {3, 5, 75});
        createFilteringStream(even, odd).forEach(System.out::println);

        //Production of squares of numbers
        long val = numberList.stream().collect(Collectors.reducing(1, (acc, elem) -> acc *= elem * elem));

        //Searching for palindrome
        Map<Boolean, List<String>> map = words.stream()
                .collect(Collectors.partitioningBy((x) -> x.equalsIgnoreCase(new StringBuilder(x).reverse().toString())));
        for (Map.Entry<Boolean, List<String>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
