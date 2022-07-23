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
            System.out.println(l);
        }

        System.out.println(l.contains(0));
        l.set(1, 10000);
        System.out.println(l);
        l.addLast(999);
        System.out.println(l);

        l.add(2, 999);
        System.out.println(l);

        l.removeFirst();
        System.out.println(l);

        l.removeLast();
        System.out.println(l);

        l.removeAt(2);
        System.out.println(l);
        l.removeAt(0);
        System.out.println(l);




        l.remove(999);
        System.out.println(l);
        l.removeFirst();
        System.out.println(l);


    }


}

