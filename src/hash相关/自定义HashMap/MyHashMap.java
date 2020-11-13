package hash相关.自定义HashMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2020-11-13
 * Time: 13:34
 * Description:
 */
public class MyHashMap<K, V> implements MyMap<K, V> {
    // 数组的默认初始化长度
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    // 阈值比例
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int defaultInitSize;
    private float defaultLoadFactor;

    // Map当中entry的数量
    private int entryUseSize;

    // 数组
    private Entry<K, V>[] table = null;

    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int defaultInitialCapacity, float defaultLoadFactor) {

        if (defaultInitialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + defaultInitialCapacity);

        if (defaultLoadFactor <= 0 || Float.isNaN(defaultLoadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + defaultLoadFactor);

        this.defaultInitSize = defaultInitialCapacity;
        this.defaultLoadFactor = defaultLoadFactor;

        table = new Entry[this.defaultInitSize];
    }


    /**
     * 1. 要考虑是否扩容？
     *      HashMap中的条目的数量（副本以及单链表中的所有条目）是否达到阈值
     * 2. 如果扩容，意味着新生成一个Entry[]， 不仅如此还得重新散列
     * 3. 要根据Key计算出在Entry[] 中的位置，定位后，如果Entry[] 中的元素为空，
     *      那么可以加入其中，如果不为空，那么得遍历单链表，只能更新值，
     *      要么形成一个新的条目“挤压”单链表！
     * @param k
     * @param v
     * @return
     */
    @Override
    public V put(K k, V v) {
        V oldValue = null;
        // 是否需要扩容？
        // 扩容完毕 肯定需要重新散列
        if (entryUseSize >= defaultInitSize * defaultLoadFactor) {
            resize(2 * defaultInitSize);
        }

        // 得到HASH值，计算出数组中的位置
        int index = hash(k) & (defaultInitSize - 1);
        if (table[index] == null) {
            table[index] = new Entry<>(k, v, null);
            ++entryUseSize;
        } else {
            // 需要遍历单链表
            Entry<K, V> entry = table[index];
            Entry<K, V> e = entry;
            while (e != null) {
                if (k == e.getKey() || k.equals(e.getKey())) {
                    oldValue = e.value;
                    e.value = v;
                    return oldValue;
                }
                e = e.next;
            }
            table[index] = new Entry<>(k, v, entry);
            ++entryUseSize;
        }
        return oldValue;
    }

    @Override
    public V get(K k) {
        int index = hash(k) & (defaultInitSize - 1);
        if (table[index] == null) {
            return null;
        } else {
            Entry<K, V> entry = table[index];
            do {
                if (k == entry.getKey() || k.equals(entry.getKey())) {
                    return entry.value;
                }
                entry = entry.next;
            } while (entry != null);
        }
        return null;
    }

    // 哈希函数
    private int hash(K k) {
        int hashCode = k.hashCode();
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    private void resize(int i) {
        Entry[] newTable = new Entry[i];
        // 改变了数组的大小
        defaultInitSize = i;
        entryUseSize = 0;
        rehash(newTable);
    }

    /**
     * 对于HashMap而言，如果重复进行resize/rehash操作，是会影响性能的
     * resize/rehash的过程，就是层叠变大，原来复制中的entry元素一个个的
     * 放到新数组的过程，需要注意的是一些状态变量的改变
     * @param newTable
     */
    private void rehash(Entry<K, V>[] newTable) {
        // 得到原来老的Entry集合 注意遍历单链表
        List<Entry<K, V>> entryList = new ArrayList<>();
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                do {
                    entryList.add(entry);
                    entry = entry.next;
                } while(entry != null);
            }
        }

        // 覆盖旧的引用
        if (newTable.length > 0) {
            table = newTable;
        }

        for (Entry<K, V> entry : entryList) {
            put(entry.getKey(), entry.getValue());
        }
    }

    class Entry<K, V> implements MyMap.Entry<K, V> {

        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry() {
        }

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }
}


