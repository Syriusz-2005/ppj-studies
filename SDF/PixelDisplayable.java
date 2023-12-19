package SDF;

@FunctionalInterface
public interface PixelDisplayable {
    void displayPixel(float occlusionValue, float distanceFromCamera);
}
