package ir.piosis.piosismart;

/**
 * Created by PIOSISLP on 6/17/2017.
 */

public class Key extends Remote {

    String data;
    int whoNumber;

    public Key() {}

    public Key(String name, String description, String colorHex, int weight, String location, String data, int whoNumber) {
        super(name, description, colorHex, weight, location);
        this.data = data;
        this.whoNumber = whoNumber;
    }

}
