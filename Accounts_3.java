import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Accounts_3 {

    static class Account {
        String number;
        Long balance;

        Account (String number, Long balance) {
            this.number = number;
            this.balance = balance;
        }

        String getNumber() {
            return number;
        }

        Long getBalance() {
            return balance;
        }
    }

    static class Transaction {
        String uuid;
        Long sum;
        Account account;

        Transaction (String uuid, Long sum, Account account) {
            this.uuid = uuid;
            this.sum = sum;
            this.account = account;
        }

        String getUuid() {
            return uuid;
        }

        Long getSum() {
            return sum;
        }

        Account getAccount() {
            return account;
        }
    }

    public static void main(String[] args) {
        Account account1 = new Account("111", 200_000L);
        Account account2 = new Account("222", 100_000L);
        Transaction transaction1 = new Transaction("1", 1000L, account1);
        Transaction transaction2 = new Transaction("2", 3000L, account2);
        Transaction transaction3 = new Transaction("3", 3000L, account2);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);

        //Group the list by account numbers, and calculate sum of transaction
        Map<String, Long> totalSum = transactions.stream()
                .collect(Collectors.groupingBy(x -> x.getAccount().getNumber(), Collectors.summingLong(Transaction::getSum)));

        for (Map.Entry<String, Long> entry : totalSum.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
