package workingwithstreams5.util;

import workingwithstreams5.domain.Trader;
import workingwithstreams5.domain.Transaction;

import java.util.Arrays;
import java.util.List;

public class TransactionUtil {

    private Trader raoul = new Trader("Raoul", "Cambridge");
    private Trader mario = new Trader("Mario", "Milan");
    private Trader alan = new Trader("Alan", "Cambridge");
    private Trader brian = new Trader("Brian", "Cambridge");

    public List<Trader> getTraders() {
        return Arrays.asList(raoul, mario, alan, brian);
    }

    public List<Transaction> getTransactions() {
        return Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }
}
