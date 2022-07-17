package backend;

import model.InputData;

import java.util.LinkedList;
import java.util.Map;

public interface CalculationRepositoryInterface {

    void addInputData(String key, LinkedList<InputData> value);
    Map<String, LinkedList<InputData>> getInputData();

    void addNewValues(String key, LinkedList<InputData> value);
    Map<String, LinkedList<InputData>> getNewValues();
    Map<String, LinkedList<InputData>> getFinalValue();
    void addFinalValue(String key, LinkedList<InputData> value);

}
