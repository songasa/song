package likou;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

public class a1669mergeInBetween {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(0,
                new ListNode(1,
                        new ListNode(2,
                                new ListNode(3,
                                        new ListNode(4,
                                                new ListNode(5,
                                                        new ListNode(6))))
                        ))
                );
        ListNode list2 = new ListNode(1000000,
                new ListNode(1000001,
                        new ListNode(1000002,
                                new ListNode(1000003,
                                        new ListNode(1000004))
                        ))
                );

        System.out.println(mergeInBetween(list1, 2, 4, list2));


    }

    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode preA = list1;
        for (int i = 0; i < a - 1; i++) {
            preA = preA.next;
        }
        ListNode preB = preA;
        for (int i = 0; i < b - a + 2; i++) {
            preB = preB.next;
        }
        preA.next = list2;
        while (list2.next != null) {
            list2 = list2.next;
        }
        list2.next = preB;
        return list1;
    }

    /**
      Definition for singly-linked list.*/
      public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
