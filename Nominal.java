public class Nominal {
    public int nominalValue;
    public int nominalCount = 0;

    public static int toRepresentation(double valueZlotys) {
        return (int) (valueZlotys * 100);
    }

    public static double fromRepresentation(int representation) {
        return ((double) representation) / 100;
    }

    Nominal(double nominalZlotys) {
        this.nominalValue = Nominal.toRepresentation(nominalZlotys);
    }


}
