package com.pureland.common.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: qinpr
 * Date: 14-6-8
 * Time: 上午9:48
 * To change this template use File | Settings | File Templates.
 */
public class GuavaFunctions {
    public interface GroupFunction<E> {
        public boolean sameGroup(final E element1, final E element2);
    }

    public static <E> List<List<E>> group(final List<E> list, final GroupFunction<E> groupFunction) {
        List<List<E>> result = Lists.newArrayList();
        for (final E element : list) {

            boolean groupFound = false;
            for (final List<E> group : result) {
                if (groupFunction.sameGroup(element, group.get(0))) {
                    group.add(element);
                    groupFound = true;
                    break;
                }
            }
            if (! groupFound) {

                List<E> newGroup = Lists.newArrayList();
                newGroup.add(element);
                result.add(newGroup);
            }
        }
        return result;
    }
}
