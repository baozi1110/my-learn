package cn.qp.myleetcode.code;

import cn.qp.myleetcode.model.ListNode;


/**
 * 019
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 */
public class RemoveNthFromEnd {
    /**
     * 两次遍历算法
     * 首先我们将添加一个哑结点作为辅助，该结点位于列表头部。
     * 哑结点用来简化某些极端情况，例如列表中只含有一个结点，或需要删除列表的头部。
     * <p>
     * 在第一次遍历中，我们找出列表的长度 L。
     * 然后设置一个指向哑结点的指针，并移动它遍历列表，直至它到达第 (L - n)个结点那里。
     * 我们把第 (L - n) 个结点的 next 指针重新链接至第 (L - n + 2) 个结点，完成这个算法。
     * <p>
     * 时间复杂度：O(L)，该算法对列表进行了两次遍历，
     * 首先计算了列表的长度 LL 其次找到第 (L - n) 个结点。
     * 操作执行了 2L-n 步，时间复杂度为 O(L)。
     * <p>
     * 空间复杂度：O(1)，我们只用了常量级的额外空间。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //添加一个假节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        //第一遍遍历获取长度
        while (first != null) {
            length++;
            first = first.next;
        }
        //获取要移动的长度
        length = length - n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        //将倒数第n+1个节点指向倒数第n-1个节点
        first.next = first.next.next;
        return dummy.next;
    }

    /**
     * 一次遍历
     * 使用两个指针，第一个指针从列表开头向前移动n+1步，第二个指针从列表的开头出发
     * 现在两个指针被隔开n个节点，保持这个间距同时移动两个指针
     * 当最后一个指针到达结尾时，此时第二个指针将指向从最后一个结点数起的第 n 个结点。
     * 重新链接第二个指针所引用的结点的 next 指针指向该结点的下下个结点。
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        //第二个节点移动n+1步
        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}