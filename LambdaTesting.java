import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntPredicate;

public class LambdaTesting {

    /**
     * Composing Predicates
     * @param predicates - list of predicates
     * @return disjunct of all IntPredicates
     */
    public static IntPredicate disjunctAll(List<IntPredicate> predicates) {
        Iterator<IntPredicate> iterator = predicates.iterator();
        if (predicates.isEmpty()) return (x) -> false; //always return false, if list is empty
        IntPredicate result = iterator.next();
        while (iterator.hasNext()){
            result = result.or(iterator.next());//disjunct of IntPredicates
        }
        return result;
    };

    public static void main(String[] args) {
        //Function of comparing
        BiFunction<Integer, Integer, Integer> z = (x, y) -> x>y ? x : y;

        //Function accepts a long value and returns a next even number.
        Function<Long, Long> f = (x) -> ((x % 2 == 0) && (++x % 2 != 0)) ? ++x : ++x;

        //Production of all numbers in the range (inclusive)
        BiFunction<Long, Long, Long> f1 = (x, y) -> {
            long result = 1;
            for (long i = x; i <= y; i++) {
                result *= i;
            }
            return result;
        };

        //Deleting duplicates in the list
        Function<List<String>, List<String>> list = (x) -> {
            Set<String> setString = new HashSet<>(x);
            List<String> listString = new ArrayList<>(setString);
            return listString;
        };

        //Testing closure in lambda
        double a = 1;
        double b = 1;
        double c = 1;
        Function<Double, Double> f2 = (x) -> a*Math.pow(x, 2) + b*x + c;
    }
}