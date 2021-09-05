public class AccountMovement {

    public static Thread putMoney(Account account, int money) {
        Runnable action = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.put(money);
        };
        return new Thread(action);
    }

    public static Thread getMoney(Account account, int money) {
        Runnable action = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.get(money);
        };
        return new Thread(action);
    }

    public static void main(String[] args) throws InterruptedException {
        int numberThreads = 20;
        Account account1 = new Account("acc1", 100);
        Account account2 = new Account("acc2", 200);
        for (int i = 1; i <= numberThreads; i++) {
            Thread threadP1 = putMoney(account1,i*10);
            Thread threadG1 = getMoney(account1,i*10);

            Thread threadP2 = putMoney(account2,i*2);
            Thread threadG2 = getMoney(account2,i*2);

            threadP1.start();
            threadG1.start();
            threadP2.start();
            threadG2.start();
        }
        Thread.sleep(5000);
    }
}
