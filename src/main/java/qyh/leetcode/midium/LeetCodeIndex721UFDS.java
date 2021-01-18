package qyh.leetcode.midium;

import java.util.*;

public class LeetCodeIndex721UFDS {
    private static Map<Account, Account> parent = new HashMap<>();
    private static Map<Account, Integer> rank = new HashMap<>();
    public static void main(String[] args) {

    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        for (List<String> account : accounts) {
            String name = account.get(0);
            Account account1 = new Account(name, new HashSet<>());
            for (int i = 1; i < account.size(); i++) {
                account1.emails.add(account.get(i));
            }
            // 初始化
            parent.put(account1, account1);
            rank.put(account1, 1);
        }
        return null;
    }

    public static Account find(Account account) {
        if (parent.get(account).equals(account)) {
            return account;
        } else {
            Account root = find(account);
            parent.put(account, root);
            return root;
        }
    }

    public static void union(Account account1, Account account2) {
        Account root1 = find(account1);
        Account root2 = find(account2);
        if (!account1.hasSameEmail(account2)) {
            return;
        }
        if (rank.get(root1) <= rank.get(root2)) {
            parent.put(root1, root2);
        } else {
            parent.put(root2, root1);
        }
        if (rank.get(root1).equals(rank.get(root2)) && !root1.equals(root2)) {
            rank.put(root2, rank.get(root2) + 1);
        }
    }

    private static class Account {
        String name;
        Set<String> emails;

        public Account(String name, Set<String> emails) {
            this.name = name;
            this.emails = emails;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Account) {
                Account bAccount = (Account) obj;
                return bAccount.emails.equals(this.emails);
            }
            return false;
        }

        public boolean hasSameEmail(Account account) {
            for (String email : account.emails) {
                if (this.emails.contains(email)) {
                    return true;
                }
            }
            return false;
        }
    }
}
