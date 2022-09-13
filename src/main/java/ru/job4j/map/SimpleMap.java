package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int i = key != null ? indexFor(hash(key.hashCode())) : 0;
        boolean rsl = table[i] == null;
        if (rsl) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode != 0 ? hashCode ^ (hashCode >>> 16) : 0;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        int oldCap = capacity;
        MapEntry<K, V>[] oldTable = table;
        capacity *= 2;
        table = (MapEntry<K, V>[]) new MapEntry[capacity];
        for (int i = 0; i < oldCap; i++) {
            if (oldTable[i] != null) {
                if (oldTable[i].key == null) {
                    table[i] = oldTable[i];
                } else {
                    int index = indexFor(hash(oldTable[i].key.hashCode()));
                    table[index] = oldTable[i];
                }
            }
        }
    }

    @Override
    public V get(K key) {
        int i = key != null ? indexFor(hash(key.hashCode())) : 0;
        V rsl = null;
        if (table[i] != null) {
            rsl = Objects.equals(table[i].key, key) ? table[i].value : null;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        int i = key != null ? indexFor(hash(key.hashCode())) : 0;
        boolean rsl = table[i] != null;
        if (key == null) {
            if (rsl) {
                table[i] = null;
                count--;
                modCount--;
            }
        } else {
            rsl = table[i] != null && table[i].key.equals(key);
        }
        if (rsl) {
            table[i] = null;
            count--;
            modCount--;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index;
            private int expectedMC = modCount;
            @Override
            public boolean hasNext() {
                if (expectedMC != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}