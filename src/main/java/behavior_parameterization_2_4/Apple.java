package behavior_parameterization_2_4;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Apple {

    private int weight;
    private String color;

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }
}
