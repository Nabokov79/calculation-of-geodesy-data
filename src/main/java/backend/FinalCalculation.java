package backend;

import model.InputData;

public class FinalCalculation {
    private int flag;
    private String address;
    private int number;
    private int point;
    private int controlPoint;
    private int oldDeviation;
    private int newDeviation;
    private int precipitation;
    private int controlPointDeviation;
    private String neighboring;
    private String diagonal;


    public void addObjectsPoint(InputData value, int newDeviation, int precipitation) {
        this.address = value.getAddress();
        this.number = value.getNumber();
        this.point = value.getPoint();
        this.oldDeviation = value.getOldDeviation();
        this.newDeviation = newDeviation;
        this.precipitation = precipitation;
        this.flag = value.getFlag();
    }

    public void addObjectsControlPoint(InputData value, int controlPointDeviation, String neighboring, String diagonal) {
        this.controlPoint = value.getControlPoint();
        this.controlPointDeviation = controlPointDeviation;
        this.neighboring = neighboring;
        this.diagonal = diagonal;
        this.flag = value.getFlag();
    }

    public InputData getFinalObjects() {
        InputData objects = null;
        switch (flag) {
            case 1:
                objects = new InputData.InputDataBuilder(flag, address, number)
                        .point(point)
                        .controlPoint(controlPoint)
                        .oldDeviation(oldDeviation)
                        .newDeviation(newDeviation)
                        .precipitation(precipitation)
                        .controlPointDeviation(controlPointDeviation)
                        .neighboring(neighboring)
                        .diagonal(diagonal)
                        .build();
                break;
            case 2:
                objects = new InputData.InputDataBuilder(flag, address, number)
                        .point(point)
                        .controlPoint(controlPoint)
                        .oldDeviation(oldDeviation)
                        .newDeviation(newDeviation)
                        .controlPointDeviation(controlPointDeviation)
                        .neighboring(neighboring)
                        .diagonal(diagonal)
                        .build();
                break;
            case 3:
                objects = new InputData.InputDataBuilder(flag, address, number)
                        .point(point)
                        .oldDeviation(oldDeviation)
                        .newDeviation(newDeviation)
                        .precipitation(precipitation)
                        .build();
                break;
            case 4:
                objects = new InputData.InputDataBuilder(flag, address, number)
                        .point(point)
                        .newDeviation(newDeviation)
                        .build();
                break;
            case 5:
                objects = new InputData.InputDataBuilder(flag, address, number)
                        .controlPoint(controlPoint)
                        .controlPointDeviation(controlPointDeviation)
                        .neighboring(neighboring)
                        .diagonal(diagonal)
                        .build();

                break;
        }
        return objects;
    }
}
