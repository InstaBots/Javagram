package net.steppschuh.instabots.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A filter that consists of multiple other filters.
 */
public class CompositeFilter<T> extends AbstractFilter<T> {

    public enum Mode {
        ALL, ANY
    }

    private Mode mode;
    private List<Filter<T>> filters = new ArrayList<>();

    public CompositeFilter(Mode mode) {
        this.mode = mode;
    }

    public CompositeFilter(Mode mode, List<Filter<T>> filters) {
        this(mode);
        this.filters = filters;
    }

    public CompositeFilter(Mode mode, Filter<T>... filters) {
        this(mode, Arrays.asList(filters));
    }

    @Override
    public boolean matches(T item) {
        switch (mode) {
            case ALL: return matchesAll(item);
            case ANY: return matchesAny(item);
        }
        throw new RuntimeException("Unable to evaluate matches using mode: " + mode);
    }

    private boolean matchesAll(T item) {
        for (Filter<T> filter : filters) {
            if (!filter.matches(item)) {
                return false;
            }
        }
        return true;
    }

    private boolean matchesAny(T item) {
        for (Filter<T> filter : filters) {
            if (filter.matches(item)) {
                return true;
            }
        }
        return false;
    }

    public void add(Filter<T> filter) {
        filters.add(filter);
    }

    /*
        Getter & Setter
     */

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public List<Filter<T>> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter<T>> filters) {
        this.filters = filters;
    }

}
