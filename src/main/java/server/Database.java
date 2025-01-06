package server;

public class Database {

    private String[] data;

    public Database() {
        data = new String[1000];
        for (int i = 0; i < 1000; i++) {
            data[i] = "";
        }
    }

    public String set(int index, String text) {
        if (index < 1 || index > 1000) {
            return "ERROR";
        }
        data[index - 1] = text;
        return "OK";
    }

    public String get(int index) {
        if (index < 1 || index > 1000 || data[index - 1].isEmpty()) {
            return "ERROR";
        }
        return data[index - 1];
    }

    public String delete(int index) {
        if (index < 1 || index > 1000) {
            return "ERROR";
        }
        data[index - 1] = "";
        return "OK";
    }
}


