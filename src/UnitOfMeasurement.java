public abstract class UnitOfMeasurement {
    public static String format(String unit) {
        if (unit == Unit.mililitros.getName())
            return "mL";
        if (unit == Unit.litros.getName())
            return "L";
        if (unit == Unit.gramas.getName())
            return "g";
        if (unit == Unit.quilogramas.getName())
            return "Kg";
        if (unit == Unit.centimetros.getName())
            return "cm";
        if (unit == Unit.metros.getName())
            return "m";
        if (unit == Unit.quilometros.getName())
            return "Km";

        return unit;
    }
}