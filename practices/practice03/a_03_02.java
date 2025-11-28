package java_course.practices.practice03;

public class a_03_02 {
    public static void main(String[] args) {
        ConnectionManager m = new ConnectionManager();
    }
}

class ConnectionManager {
    Connection[] array;

    public ConnectionManager() {
        array = Connection.cr_array();
    }

    private class Connection {

        public Connection() {
            ;
        }

        // @Override
        // public String toString() {
        //     return "Connection";
        // }

        public static Connection[] cr_array() {
            Connection array = new Connection[10];
            for (int i = 0; i < 10; i++) {
                array[i] = Connection();
            }
        }
    }

    public Connection getConnection(int i) {
        if (i > -1 && i < array.size) return array[i];
    }
}