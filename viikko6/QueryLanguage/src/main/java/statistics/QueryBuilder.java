package statistics;

import java.util.ArrayList;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

class QueryBuilder {
    private ArrayList<Matcher> matchers = new ArrayList<>();

    QueryBuilder playsIn(String team) {
        matchers.add(new PlaysIn(team));
        return this;
    }

    QueryBuilder hasAtLeast(int i, String goals) {
        matchers.add(new HasAtLeast(i, goals));
        return this;
    }

    QueryBuilder hasFewerThan(int i, String goals) {
        matchers.add(new HasFewerThan(i, goals));
        return this;
    }

    Matcher build() {
        Matcher and = new And(matchers.toArray(new Matcher[matchers.size()]));
        this.matchers.clear();
        return and;
    }

    QueryBuilder oneOf(Matcher... matchers) {
        this.matchers.add(new Or(matchers));
        return this;
    }
}
