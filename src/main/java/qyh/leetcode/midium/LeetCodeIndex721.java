package qyh.leetcode.midium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 账户合并
 * 给定一个列表 accounts，每个元素 accounts[i]是一个字符串列表，
 * 其中第一个元素 accounts[i][0]是名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，
 * 则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，
 * 因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/accounts-merge
 */
public class LeetCodeIndex721 {
    private static boolean merge = false;
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
        merge = false;
        Map<Account, String> accountMap = new HashMap<>();
        for (List<String> account : accounts) {
            Account acc = new Account(account.get(0));
            for (int i = 1; i < account.size(); i++) {
                acc.emails.add(account.get(i));
            }
            accountMap.put(acc, account.get(0));
        }
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Account, String> entry : accountMap.entrySet()) {
            List<String> account = new ArrayList<>();
            account.add(entry.getValue());
            account.addAll(entry.getKey().emails);
            result.add(account.stream().distinct().sorted().collect(Collectors.toList()));
        }
        if (merge) {
            return accountsMerge(result);
        }
        return result;
    }


    private static class Account {
        final String name;
        List<String> emails = new ArrayList<>();
        public Account(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Account) {
                Account account = (Account) obj;
                for (String email : account.emails) {
                    if (this.emails.contains(email)) {
                        merge = true;
                        account.addEmails(this.emails);
                        return true;
                    }
                }
            }
            return false;
        }

        public void addEmails(List<String> emailCollection) {
            this.emails.addAll(emailCollection);
            this.emails = this.emails.stream().distinct().collect(Collectors.toList());
        }
    }


}
