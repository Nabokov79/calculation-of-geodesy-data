package backend;

public class GeneralMethods implements GeneralMethodsInterface {

    private int minValuePoint;
    private int minValueControlPoint;

    @Override
    public int getMinValuePoint() {
        return minValuePoint;
    }

    @Override
    public int getMinValueControlPoint() {
        return minValueControlPoint;
    }

    @Override
    public void getMinValuePoint(int valuePoint) {
        minValuePoint = getMinValue(minValuePoint, valuePoint);
    }

    @Override
    public void getMinValueControlPoint(int valueControlPoint) {
        minValueControlPoint = getMinValue(minValueControlPoint, valueControlPoint);
    }

    private int getMinValue(int valueFirst, int valueSecond) {
        if (valueFirst == 0 || valueFirst > valueSecond) {
            return valueSecond;
        } else {
            return valueFirst;
        }
    }
}
