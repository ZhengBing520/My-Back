package com.zb.sys.utils.function;

/**
 * Created by bzheng on 2019/9/6.
 */
@FunctionalInterface
public interface MultiFunction<T, U, V, R>  {

    /**
     *  增强版function
     * @param t
     * @param u
     * @param v
     * @return
     */
    R apply(T t, U u, V v);

}
