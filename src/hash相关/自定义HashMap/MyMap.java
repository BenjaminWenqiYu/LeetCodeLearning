package hash相关.自定义HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2020-11-13
 * Time: 13:32
 * Description:
 */
public interface MyMap<K, V> {
    public V put(K k, V v);
    public V get(K k);

    interface Entry<K, V> {
        public K getKey();
        public V getValue();
    }
}
