import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class NominalsManager {
    public Nominal[] nominals;

    NominalsManager(
            Nominal[] nominals
    ) {
        Arrays.sort(nominals, Comparator.comparingInt((Nominal a) -> -a.nominalValue));
        this.nominals = nominals;
    }

    public void calculateFor(double value) {
        int representation = Nominal.toRepresentation(value);
        while (representation > 0) {
            for (Nominal nominal : nominals) {
                if (representation >= nominal.nominalValue) {
                    nominal.nominalCount++;
                    representation -= nominal.nominalValue;
                }
            }
        }
    }

    public void printResults() {
        for (Nominal nominal : nominals) {
            System.out.println(Nominal.fromRepresentation(nominal.nominalValue) + "zł: " + nominal.nominalCount);
        }
    }
}
