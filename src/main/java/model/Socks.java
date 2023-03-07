package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Socks {
private DescptionSocks colors;
private SizeSocks size;
private Integer cotton;
private Integer units;

}
