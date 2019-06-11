package com.contract.data;

import java.util.List;

/**
 * @param <T>
 * @param <V>
 * @author Riccardo
 */
public interface IBaseDAO<T, V> {
    public List<T> getAll(V req);

    public T getElement(V req);
}
