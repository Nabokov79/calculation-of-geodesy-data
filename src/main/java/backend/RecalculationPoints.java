package backend;

import model.InputData;
import model.NewDataObjects;
import java.util.LinkedList;

public class RecalculationPoints  extends DataInput {

    private final NewDataObjects newData = new NewDataObjects();

    private int delta;
    private int transition;
    private InputData values;
    private InputData value;

    public RecalculationPoints(CalculationRepositoryInterface repository) {
        super(repository);
    }

    public void conversionByTransition() {
        for (String key : repository.getInputData().keySet()) {
            LinkedList<InputData> valueList = new LinkedList<>();
            for (InputData val : repository.getInputData().get(key)) {
                values = val;
                getNewValues();
                valueList.addLast(value);
            }
            repository.addNewValues(key, valueList);
            transition = 0;
            delta = 0;
        }
    }

    private void getNewValues() {
        if (values.getTransition() != 0) {
            transition = values.getTransition();
            getNewObject();
            getDelta();
        } else {
            getNewObject();
        }
    }

    private void getNewObject() {
        switch (values.getFlag()) {
            case 1:
                value = newData.getNewFullPoint(values, values.getPoint() + delta, values.getControlPoint() + delta);
                break;
            case 2:
                value = newData.getNewOnlyFullPoint(values, values.getPoint() + delta, values.getControlPoint() + delta);
                break;
            case 3:
                value = newData.getNewPointValue(values, values.getPoint() + delta);
                break;
            case 4:
                value = newData.getNewOnlyPointValue(values, values.getPoint() + delta);
                break;
            case 5:
                value = newData.getNewValueByControlPoint(values, values.getControlPoint() + delta);
                break;
        }
    }

    private void getDelta() {
        if (values.getPoint() != 0) {
            delta += values.getPoint() - transition;
        } else {
            delta += values.getControlPoint() - transition;
        }
    }

}
