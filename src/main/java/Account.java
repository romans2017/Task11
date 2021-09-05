public class Account {

    private int balance;
    private String name;

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public synchronized String getBalance() {
        return name + " " + balance;
    }

    public synchronized void put(int money) {
        this.balance += money;
        System.out.println("put to " + name + ":" + money);
        System.out.println(getBalance());
    }

    public synchronized void get(int money) {
        this.balance -= money;
        System.out.println("get from " + name + ":" + money);
        System.out.println(getBalance());
    }
}
