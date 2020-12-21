import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Accounts_2 {

    enum State {
        CANCELED,
        FINISHED,
        PROCESSING
    }

    static class Transaction {
        String uuid;
        State state;
        Long sum;
        Date created;

        Transaction (String uuid, State state, Long sum, Date created) {
            this.uuid = uuid;
            this.state = state;
            this.sum = sum;
            this.created = created;
        }

        String getUuid() {
            return uuid;
        }

        State getState() {
            return state;
        }

        Long getSum() {
            return sum;
        }

        Date getCreated() {
            return created;
        }
    }

    static class Account {
        String number;
        Long balance;
        List<Transaction> transactions;

        Account (String number, Long balance, List<Transaction> transactions) {
            this.number = number;
            this.balance = balance;
            this.transactions = transactions;
        }

        String getNumber() {
            return number;
        }

        Long getBalance() {
            return balance;
        }

        List<Transaction> getTransactions() {
            return transactions;
        }
    }

    /**
     *  Method for calculating total sum of Canceled transactions on non-empty accounts
     * @param accounts
     * @return
     */
    public static long calcSumOfCanceledTransOnNonEmptyAccounts(List<Account> accounts) {
        return accounts.stream()
                .filter(x -> x.getBalance() > 0) //get all non-empty accounts
                .flatMap(x -> x.getTransactions().stream()) //replace elements with transactions
                .filter(x -> x.getState() == State.CANCELED) //get all Canceled transactions
                .flatMap(x -> Stream.of(x.getSum())) //replace all elements with transaction's sum
                .reduce(0L, Long::sum); //calculate total sum of transactions
    }

    public static void main(String[] args) {
        Transaction first = new Transaction("1", State.CANCELED, 1000L, new Date());
        Transaction second = new Transaction("2", State.FINISHED, 8000L, new Date());
        Transaction third = new Transaction("3", State.CANCELED, 10_000L, new Date());
        Account account1 = new Account("1001", 0L,
                Stream.of(first).collect(Collectors.toList()));
        Account account2 = new Account("1002", 8000L,
                Stream.of(second, third).collect(Collectors.toList()));
        List<Account> accounts = Stream.of(account1, account2).collect(Collectors.toList());
        System.out.println(calcSumOfCanceledTransOnNonEmptyAccounts(accounts));
    }
}
