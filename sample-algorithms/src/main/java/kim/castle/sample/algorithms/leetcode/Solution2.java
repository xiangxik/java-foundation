package kim.castle.sample.algorithms.leetcode;

public class Solution2 {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = new ListNode(0);
		ListNode r = result;
		int v = 0;
		while (l1 != null || l2 != null) {
			int val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + v;
			v = val / 10;
			ListNode next = new ListNode(val % 10);
			r.next = next;
			r = next;

			if (l1 != null) {
				l1 = l1.next;
			}
			if (l2 != null) {
				l2 = l2.next;
			}
		}

		if (v > 0) {
			r.next = new ListNode(v);
		}

		return result.next;
	}

	public static void main(String[] args) {
		// System.out.println(9+9999999991);
	}
}
