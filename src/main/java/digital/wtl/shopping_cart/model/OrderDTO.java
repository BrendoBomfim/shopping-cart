package digital.wtl.shopping_cart.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String orderName;

    private List<Long> products;

}
