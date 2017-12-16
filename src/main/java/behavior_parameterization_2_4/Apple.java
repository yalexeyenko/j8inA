package behavior_parameterization_2_4;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Apple extends Fruit implements Comparable<Apple> {

    private int weight;
    private String color;

    public Apple() {
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    @Override
    public int compareTo(Apple o) {
        if (weight < o.getWeight()) {
            return -1;
        } else if (weight > o.getWeight()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
