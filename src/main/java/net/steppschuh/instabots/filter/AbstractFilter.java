package net.steppschuh.instabots.filter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFilter<T> implements Filter<T> {

    @Override
    public List<T> applyTo(List<T> items) {
        List<T> matches = new ArrayList<>();
        for (T item : items) {
            if (matches(item)) {
                matches.add(item);
            }
        }
        return matches;
    }

}
