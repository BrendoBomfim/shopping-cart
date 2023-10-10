package digital.wtl.shopping_cart.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {

    private Long id;

    @NotNull
    private Double price;

    @NotNull
    @Size(max = 255)
    private String name;

    private String description;

}
