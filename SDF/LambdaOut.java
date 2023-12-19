package SDF;

import java.util.function.DoubleFunction;


public class LambdaOut implements Out {

    private final PixelDisplayable displayPixelLambda;
    private final RowCreatable nextRowLambda;

    public LambdaOut(PixelDisplayable displayPixel, RowCreatable nextRow) {
        this.displayPixelLambda = displayPixel;
        this.nextRowLambda = nextRow;
    }

    @Override
    public void displayPixel(float occlusionValue, float distanceFromCamera) {
        this.displayPixelLambda.displayPixel(occlusionValue, distanceFromCamera);
    }

    @Override
    public void nextRow() {
        this.nextRowLambda.nextRow();
    }
}
