/**
 * @description:
 * @Date on 2022/6/20
 * @author: suche
 **/

public class Application {
    public static void main(String[] args) {
        LinkedList1<Integer> l = new LinkedList1<>();
        for (int i = 0; i < 5; i++) {
            l.addFirst(i);
        }
        System.out.println(l.contains(0));
        System.out.println(l);

    }
}

