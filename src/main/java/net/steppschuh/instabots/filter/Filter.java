package net.steppschuh.instabots.filter;

import java.util.List;

public interface Filter<T> {

    boolean matches(T item);

    List<T> applyTo(List<T> items);

}
