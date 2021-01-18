package qyh.leetcode.midium;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCodeIndex721UFDS {
    private static Map<Account, Account> parent = new HashMap<>();
    private static Map<Account, Integer> rank = new HashMap<>();
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        List<String> acc1 = Arrays.asList("David","David0@m.co","David1@m.co");
        accounts.add(acc1);
        List<String> acc2 = Arrays.asList("David","David3@m.co","David4@m.co");
        accounts.add(acc2);
        List<String> acc3 = Arrays.asList("David","David4@m.co","David5@m.co");
        accounts.add(acc3);
        List<String> acc4 = Arrays.asList("David","David2@m.co","David3@m.co");
        accounts.add(acc4);
        List<String> acc5 = Arrays.asList("David","David1@m.co","David2@m.co");
        accounts.add(acc5);
        System.out.println(accountsMerge(accounts));
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<Account> allCounts = new ArrayList<>();
        for (List<String> account : accounts) {
            String name = account.get(0);
            Account account1 = new Account(name, new HashSet<>());
            for (int i = 1; i < account.size(); i++) {
                account1.emails.add(account.get(i));
            }
            // 初始化
            parent.put(account1, account1);
            rank.put(account1, 1);
            for (Account preserved : allCounts) {
                union(preserved, account1);
            }
            allCounts.add(account1);
        }
        Map<Account, Set<String>> map = new HashMap<>();
        for (Account preserved : allCounts) {
            Account root = find(preserved);
            if (!map.containsKey(root)) {
                Set<String> emails = new HashSet<>();
                emails.add(root.name);
                emails.addAll(preserved.emails);
                map.put(root, emails);
            } else {
                map.get(root).addAll(preserved.emails);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Account, Set<String>> entry : map.entrySet()) {
            result.add(entry.getValue().stream().sorted().collect(Collectors.toList()));
        }
        return result;
    }

    public static Account find(Account account) {
        if (parent.get(account).equals(account)) {
            return account;
        } else {
            Account root = find(parent.get(account));
            parent.put(account, root);
            return root;
        }
    }

    public static void union(Account account1, Account account2) {
        Account root1 = find(account1); // [3, 4]
        Account root2 = find(account2); // [2, 3]
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
