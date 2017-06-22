package ir.piosis.piosismart;

import com.orm.SugarRecord;

/**
 * Created by PIOSISLP on 6/17/2017.
 */

public class Remote extends SugarRecord {

    String name;
    String description;
    String colorHex;
    //  وزن به منظور اندازه ریموت در layout تعریف شده
    int weight;
    //  مکان به منظور تعیین موقعیت ریموت در layout تعریف شده
    String location;

    public Remote() {
    }

    public Remote(String name, String description, String colorHex, int weight, String location) {
        this.name = name;
        this.description = description;
        this.colorHex = colorHex;
        this.weight = weight;
        this.location = location;
    }




}
