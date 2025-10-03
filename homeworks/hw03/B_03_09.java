package java_course.homeworks.hw03;
import java.util.Arrays;

public class B_03_09 {
    public static void main(String[] args) {
        Customer[] array = {
            new Customer("Alice", "Smith", "123 Maple St", 12345678, 100001),
            new Customer("Frank", "Miller", "987 Spruce St", 54323435, 100007),
            new Customer("David", "Brown", "321 Birch St", 12334478, 100005),
            new Customer("Bob", "Johnson", "456 Oak St", 23456789, 100002),
            new Customer("Grace", "Wilson", "159 Elm St", 12354334, 100006),
            new Customer("Eva", "Davis", "654 Cedar St", 56784321, 100004),
            new Customer("Carol", "Williams", "789 Pine St", 32498767, 100003)
        };
        // System.out.println(Arrays.toString(array));
        // sort(array);
        // System.out.println(Arrays.toString(array));
        Customer[] within_interval = interval(0, 12400000, array);
        System.out.println(Arrays.toString(within_interval));
    }


    /**
     * Returns an array of customers whose credit card numbers
     * fall within the specified interval [a, b].
     */
    public static Customer[] interval(int a, int b, Customer[] base) {
        int i = 0;
        for (Customer c: base) {
            if (c.card >= a && c.card <= b) i++;
        }
        Customer[] res = new Customer[i];
        i = 0;
        for (Customer c: base) {
            if (c.card >= a && c.card <= b) {
                res[i] = c;
                i++;
            }
        }
        return res;
    }

    public static void sort(Customer[] array) {
        if (array.length == 1) return;
        int mid = array.length / 2;
        Customer[] left = Arrays.copyOfRange(array, 0, mid);
        Customer[] right = Arrays.copyOfRange(array, mid, array.length);

        sort(left);
        sort(right);

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) > 0) {
                array[k] = left[i];
                i++;
            }
            else {
                array[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {
            array[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            array[k] = right[j];
            j++;
            k++;
        }
    };
}

class Customer implements Comparable<Customer> {
    protected String first_name;
    protected String last_name;
    protected String address;
    protected int card;
    protected int bank_account;

    public Customer(String first, String last, String address, int card, int account) {
        this.first_name = first;
        this.last_name = last;
        this.address = address;
        this.card = card;
        this.bank_account = account;
    }

    public Customer(Customer c) {
        this.first_name = c.first_name;
        this.last_name = c.last_name;
        this.address = c.address;
        this.card = c.card;
        this.bank_account = c.bank_account;
    }

    public void setFirstName(String first) {this.first_name = first;}

    public void setLastName(String last) {this.last_name = last;}

    public void setAddress(String address) {this.address = address;}

    public void setCard(int card) {this.card = card;}

    public void setBankAccount(int account) {this.bank_account = account;}

    public String getFirstName() {return this.first_name;}

    public String getLastName() {return this.last_name;}

    public String getAddress() {return this.address;}

    public int getCard() {return this.card;}
    
    public int getBankAccount() {return this.bank_account;}

    @Override
    public String toString() {
        return "\nCustomer: " + first_name + " " + last_name + "\n\tAddress: " + address + "\n\tCard: " + card + "\n\tBank account: " + bank_account;
    }

    public boolean equals(Customer other) {
        return (first_name == other.first_name) && (last_name == other.last_name) && (address == other.address) && (card == other.card) && (bank_account == other.bank_account);
    }

    @Override
    public int compareTo(Customer other) {
        String name = first_name + " " + last_name;
        String other_name = other.first_name + " " + other.last_name;
        int res = name.compareTo(other_name);
        if (res < 0) return 1;
        else if (res > 0) return -1;
        else return 0;
    }
}