package cn.qp.myleetcode.code;

import cn.qp.myleetcode.model.ListNode;

/**
 * 021
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class MergeTwoLists {
    /**
     * 递归
     * 特殊的，如果 l1 或者 l2 一开始就是 null ，那么没有任何操作需要合并，
     * 所以我们只需要返回非空链表。否则，我们要判断 l1 和 l2 哪一个的头元素更小，
     * 然后递归地决定下一个添加到结果里的值。
     * 如果两个链表都是空的，那么过程终止，所以递归过程最终一定会终止。
     * 时间复杂度O(m+n)，空间复杂度O(m+n)
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 迭代
     * 假设 l1 元素严格比 l2元素少，我们可以将 l2 中的元素逐一插入 l1 中正确的位置。
     *  首先，我们设定一个哨兵节点 "prehead" ，这可以在最后让我们比较容易地返回合并后的链表。
     *  我们维护一个 prev 指针，我们需要做的是调整它的 next 指针。
     *  然后，我们重复以下过程，直到 l1 或者 l2 指向了 null ：
     *  如果 l1 当前位置的值小于等于 l2 ，我们就把 l1 的值接在 prev 节点的后面同时将 l1 指针往后移一个。
     *  否则，我们对 l2 做同样的操作。不管我们将哪一个元素接在了后面，我们都把 prev 向后移一个元素。
     *
     *  时间复杂度O(m+n)，空间复杂度O(1)
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        //在返回节点之前保持对节点的不变引用。
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val){
                prev.next = l1;
                l1 = l1.next;
            }else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        //此时l1和l2中只有一个非空，所以连接非空列表到合并列表的末尾。
        prev.next = l1==null?l2:l1;
        return prehead.next;
   }
}
