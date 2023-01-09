

package entity;

import lombok.Builder;
import lombok.Data;


@Data
@Builder

public class CategoryRoom {

    Integer id;
    String kind;

}