package backend;

import model.InputData;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CalculationRepository implements CalculationRepositoryInterface {

    private final Map<String, LinkedList<InputData>> inputData = new HashMap<>();
    private final Map<String, LinkedList<InputData>> newValuesPoint = new HashMap<>();
    private final Map<String, LinkedList<InputData>> finalValue = new HashMap<>();

    @Override
    public Map<String, LinkedList<InputData>> getInputData() {
        return inputData;
    }

    @Override
    public void addInputData(String key, LinkedList<InputData> value) {
        inputData.put(key, value);
    }

    @Override
    public Map<String, LinkedList<InputData>> getNewValues() {
        return newValuesPoint;
    }

    @Override
    public void addNewValues(String key, LinkedList<InputData> value) {
        newValuesPoint.put(key, value);
    }

    @Override
    public Map<String, LinkedList<InputData>> getFinalValue() {
        return finalValue;
    }

    @Override
    public void addFinalValue(String key, LinkedList<InputData> value) {
        finalValue.put(key, value);
    }
}