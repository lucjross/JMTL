package main.java.org.lucjross.jmtl.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by lucas on 11/16/2014.
 */
public class CircularArrayList<E> extends ArrayList<E>
{
    public CircularArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public E get(int index)
    {
        return super.get(realIndex(index));
    }

    @Override
    public E set(int index, E element)
    {
        return super.set(realIndex(index), element);
    }

    @Override
    public void add(int index, E element)
    {
        super.add(realIndex(index), element);
    }

    @Override
    public E remove(int index)
    {
        return super.remove(realIndex(index));
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
    {
        return super.addAll(realIndex(index), c);
    }

    @Override
    public ListIterator<E> listIterator(int index)
    {
        return super.listIterator(realIndex(index));
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex)
    {
        if (fromIndex >= toIndex)
        {
            // will either fail or do nothing
            return super.subList(fromIndex, toIndex);
        }

        fromIndex = realIndex(fromIndex);
        toIndex = realIndex(toIndex);
        if (fromIndex > toIndex)
        {
            List<E> list1 = super.subList(0, toIndex);
            List<E> list2 = super.subList(fromIndex, size() - 1);
            CircularArrayList<E> list = new CircularArrayList<>(fromIndex - toIndex + 1);
            list.addAll(list1);
            list.addAll(list2);
            return list;
        }
        return super.subList(fromIndex, toIndex);
    }

    private int realIndex(int index)
    {
        final int size = size();
        while (index < 0)
        {
            index += size;
        }
        while (index >= size)
        {
            index -= size;
        }
        return index;
    }
}
