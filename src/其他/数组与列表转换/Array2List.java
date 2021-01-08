package 其他.数组与列表转换;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ywq
 * Date: 2021-01-08
 * Time: 15:03
 * Description:
 * 数组与集合的链表相互转换
 */
public class Array2List {

    public static void main(String[] args) {
        Array2List.list2Arr();
    }

    // 基本类型不能作为 Arrays.asList方法的参数，否则会被当做一个参数
    public static void arr2List() {
        int[] array = {1, 2, 3};
        List list = Arrays.asList(array);
        System.out.println(list.size());
        //
        // 运行结果为 1
        // reason: 基本类型不能作为 Arrays.asList方法的参数，否则会被当做一个参数
        //

    }

    // Arrays.asList 返回的 List 不支持增删操作
    public static void arr2List2() {
        String[] arr = {"1", "2", "3"};
        List list = Arrays.asList(arr);
        list.add("5");
        System.out.println(list.size());
        //
        // Exception in thread "main" java.lang.UnsupportedOperationException
        //	at java.util.AbstractList.add(AbstractList.java:148)
        //	at java.util.AbstractList.add(AbstractList.java:108)
        //	at 其他.数组与列表转换.Array2List.arr2List2(Array2List.java:34)
        //	at 其他.数组与列表转换.Array2List.main(Array2List.java:17)
        // reason: Arrays.asList 返回的 List 并不是我们期望的 java.util.ArrayList，
        //         而是 Arrays 的内部类 ArrayList。内部类的ArrayList没有实现add方法
        //
    }

    // 使用Arrays.asList的时候，对原始数组的修改会影响到我们获得的那个List
    public static void arr2List3() {
        String[] arr = {"1", "2", "3"};
        List list = Arrays.asList(arr);
        arr[1] = "4";
        System.out.println("原始数组"+Arrays.toString(arr));
        System.out.println("list数组" + list);
        //
        // 原始数组[1, 4, 3]
        // list数组[1, 4, 3]
        //
        List<String> li = new ArrayList<String>(Arrays.asList(arr));
        li.add("5");
        System.out.println("list新数组" + li);
    }

    // ArrayList.toArray() 强转的坑
    public static void list2Arr() {
        List<String> list = new ArrayList<String>(1);
        list.add("公众号：捡田螺的小男孩");
        // String[] array21 = (String[])list.toArray();//类型转换异常

        //
        // 因为返回的是Object类型，Object类型数组强转String数组，会发生ClassCastException。
        // 解决方案是，使用toArray()重载方法toArray(T[] a)
        //
        String[] arr = list.toArray(new String[0]);
        System.out.println(Arrays.toString(arr));
    }
}
