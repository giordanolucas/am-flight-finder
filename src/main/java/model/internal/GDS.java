package model.internal;

public class GDS {
    private String name;
    private String code;

    public GDS(String name, String code){
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static GDS amadeus(){
        return new GDS("Amadeus", "AMA");
    }

    public static GDS worldspan(){
        return new GDS("Worldspan", "WOR");
    }

    public static GDS sabre(){
        return new GDS("Sabre", "SAB");
    }

}