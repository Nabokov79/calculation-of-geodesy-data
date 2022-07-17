package model;

public class NewDataObjects {

    public InputData getNewFullPoint(InputData value, int point, int controlPoint) {
        return new InputData.InputDataBuilder(1,value.getAddress(), value.getNumber())
                .point(point)
                .controlPoint(controlPoint)
                .oldDeviation(value.getOldDeviation())
                .build();
    }

    public InputData getNewOnlyFullPoint(InputData value, int point, int controlPoint) {
        return new InputData.InputDataBuilder(2,value.getAddress(), value.getNumber())
                .point(point)
                .controlPoint(controlPoint)
                .build();
    }

    public InputData getNewPointValue(InputData value, int point) {
        return new InputData.InputDataBuilder(3,value.getAddress(), value.getNumber())
                .point(point)
                .oldDeviation(value.getOldDeviation())
                .build();
    }

    public InputData getNewOnlyPointValue(InputData value, int point) {
        return new InputData.InputDataBuilder(4,value.getAddress(), value.getNumber())
                .point(point)
                .build();
    }

    public InputData getNewValueByControlPoint(InputData value, int pointValue) {
        return new InputData.InputDataBuilder(5, value.getAddress(), value.getNumber())
                                                                .controlPoint(pointValue)
                                                                .build();
    }

}