public enum Unit {
    mililitros("mililitro"),
    litros("litro"),
    gramas("grama"),
    quilogramas("quilograma"),
    centimetros("centimetros"),
    metros("metros"),
    quilometros("quilometros");

    private String name;

    private Unit(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "";
    }
}