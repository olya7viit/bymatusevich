package by.matusevich.model.entity;

public class Operation extends AbstractEntity {

    private double sum;
    private String type;

    public Operation() { }

    public Operation(double summa, String type) {
        this.sum = summa;
        this.type = type;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double summa) {
        this.sum = summa;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return sum == operation.sum &&
                (type == operation.type || (operation != null && operation.equals(operation.getType())));
    }

    @Override
    public int hashCode() {
        return (int) (31 * sum + ((type == null ? 0 : type.hashCode())));
    }


    @Override
    public String toString() {
        return getClass() + "{" +
                "sum=" + sum +
                ", type='" + type + '\'' +
                '}';
    }
}
