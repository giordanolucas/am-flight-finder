package model.internal;

public class GDS {
    private String name;
    private String code;

    public GDS(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static GDS getByCode(String code) {
        if(amadeus().getCode().equalsIgnoreCase(code))
            return amadeus();

        if(worldspan().getCode().equalsIgnoreCase(code))
            return worldspan();

        if(sabre().getCode().equalsIgnoreCase(code))
            return sabre();

        return null;
    }

    public static GDS amadeus() {
        return new GDS("Amadeus", "AMA");
    }

    public static GDS worldspan() {
        return new GDS("Worldspan", "WOR");
    }

    public static GDS sabre() {
        return new GDS("Sabre", "SAB");
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

}
