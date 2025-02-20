package org.example.storesg.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor  // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los campos
@ToString
public class OrdersBuyDTO {
    private Integer idOrder;
    private Integer idUser;
    private String status;
    private Instant date;
    private String paymentMethod;
    private BigDecimal total;

}

