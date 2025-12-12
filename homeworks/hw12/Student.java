package homeworks.hw12;

public abstract class Student {
    protected int credits = 0;
    protected int requiredCredits;
    protected int money;
    protected boolean isExpelled = false;

    public Student(int requiredCredits, int money) {
        this.requiredCredits = requiredCredits;
        this.money = money;
    }

    public abstract void accept(Visitor visitor);

    public void addCredits(int c) {
        if (!isExpelled) this.credits += c;
    }

    public void obtain(int m) {
        if (!isExpelled) this.money += m;
    }

    public boolean pay(int m) {
        if (isExpelled) return false;

        if (this.money >= m) {
            this.money -= m;
            return true;
        } else {
            this.isExpelled = true;
            return false;
        }
    } 
}
