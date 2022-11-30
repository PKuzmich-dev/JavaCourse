package Unit9.Task2;


public class AccountThread implements Runnable {

    private final Account accountFrom;
    private final Account accountTo;
    private final int money;

    private boolean featureFlag = false;
    // надо будет потом удалить
    Thread t;
    public AccountThread(Account accountFrom, Account accountTo, int money) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.money = money;

        // надо будет потом удалить
        t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        for (int i = 0; i < 4000; i++) {
            // implement
            if (featureFlag) {
                synchronized (accountFrom) {
                    if (accountFrom.takeOffMoney(money))
                        synchronized (accountTo) {
                            accountTo.addMoney(money);
                        }
                }
            }
            else{
                Account lock1 = accountFrom;
                Account lock2 = accountTo;
                if (accountFrom.hashCode() > accountTo.hashCode()){
                    lock1 = accountTo;
                    lock2 = accountFrom;
                }
                synchronized (lock1){
                    synchronized (lock2){
                        if (accountFrom.takeOffMoney(money)) {
                            accountTo.addMoney(money);
                        }
                    }
                }
            }
        }
    }
}
