package model;

public class InputData {
    private final int flag;
    private final String address;
    private final int number;
    private final int point;
    private final int controlPoint;
    private final int transition;
    private final int oldDeviation;
    private final int newDeviation;
    private final int precipitation;
    private final int controlPointDeviation;
    private final String neighboring;
    private final String diagonal;


    private InputData(InputDataBuilder data) {
        this.flag = data.flag;
        this.address = data.address;
        this.number = data.number;
        this.point = data.point;
        this.controlPoint = data.controlPoint;
        this.transition = data.transition;
        this.oldDeviation = data.oldDeviation;
        this.newDeviation = data.newDeviation;
        this.precipitation = data.precipitation;
        this.neighboring = data.neighboring;
        this.diagonal = data.diagonal;
        this.controlPointDeviation = data.controlPointDeviation;
    }

    public int getFlag() {
        return flag;
    }

    public String getAddress() {
        return address;
    }

    public int getNumber() {
        return number;
    }

    public int getPoint() {
        return point;
    }

    public int getControlPoint() {
        return controlPoint;
    }

    public int getTransition() {
        return transition;
    }

    public int getOldDeviation() {
        return oldDeviation;
    }

    public int getNewDeviation() {
        return newDeviation;
    }

    public int getControlPointDeviation() {
        return controlPointDeviation;
    }

    public int getPrecipitation() {
        return precipitation;
    }

    public String getNeighboring() {
        return neighboring;
    }

    public String getDiagonal() {
        return diagonal;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "flag=" + flag +
                ", address='" + address + '\'' +
                ", number=" + number +
                ", point=" + point +
                ", controlPoint=" + controlPoint +
                ", transition=" + transition +
                ", oldDeviation=" + oldDeviation +
                ", newDeviation=" + newDeviation +
                ", controlPointDeviation=" + controlPointDeviation +
                ", precipitation=" + precipitation +
                ", neighboring=" + neighboring +
                ", diagonal=" + diagonal +
                '}';
    }

    public static class InputDataBuilder {

        private final int flag;
        private final String address;
        private final int number;
        private int transition;
        private int point;
        private int controlPoint;
        private int oldDeviation;
        private int newDeviation;
        private int precipitation;
        private int controlPointDeviation;
        private String neighboring;
        private String diagonal;

        public InputDataBuilder(int flag, String address, int number) {
            this.flag = flag;
            this.address = address;
            this.number = number;
        }

        public InputDataBuilder point(int point) {
            this.point = point;
            return this;
        }

        public InputDataBuilder controlPoint(int controlPoint) {
            this.controlPoint = controlPoint;
            return this;
        }

        public InputDataBuilder transition(int transition) {
            this.transition = transition;
            return this;
        }

        public InputDataBuilder oldDeviation(int oldDeviation) {
            this.oldDeviation = oldDeviation;
            return this;
        }

        public InputDataBuilder newDeviation(int newDeviation) {
            this.newDeviation = newDeviation;
            return this;
        }

        public InputDataBuilder precipitation(int precipitation) {
            this.precipitation = precipitation;
            return this;
        }

        public InputDataBuilder controlPointDeviation(int controlPointDeviation) {
            this.controlPointDeviation = controlPointDeviation;
            return this;
        }

        public InputDataBuilder neighboring(String  neighboring) {
            this.neighboring = neighboring;
            return this;
        }

        public InputDataBuilder diagonal(String diagonal) {
            this.diagonal = diagonal;
            return this;
        }

        public InputData build() {
            return new InputData(this);
        }
    }
}
