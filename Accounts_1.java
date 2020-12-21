import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Accounts_1 {
    String number;
    Long balance;
    boolean isLocked;

    public Accounts_1(String number, Long balance, boolean isLocked) {
        this.number = number;
        this.balance = balance;
        this.isLocked = isLocked;
    }

    public String getNumber() {
        return number;
    }

    public Long getBalance() {
        return balance;
    }

    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public String toString() {
        return getNumber() + " " + getBalance() + " " + isLocked();
    }

    //Method for filtering the List
    static <T> List<T> filter(List<T> elems, Predicate<T> predicate){
        elems.removeIf(t -> predicate.test(t));
        return elems;
    }

    public static void main(String[] args) {
        List<Accounts_1> accounts = new ArrayList<>();
        accounts.add(new Accounts_1("1",100_000_000l,true));
        accounts.add(new Accounts_1("2", 100l, false));

        //List of all non-empty accounts
        List<Accounts_1> nonEmptyAccounts = filter(accounts, account -> account.getBalance() > 0);

        //All non-locked accounts with too much money
        List<Accounts_1> filtered = filter(accounts, account -> account.getBalance() > 100_000_000l);

        filtered.forEach(System.out::println);
    }
}
