package com.wallet.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "order")
public class Order {
    private Long id;
    private Category category;
    private Long amount;
    private String description;
    private Wallet wallet;
    private boolean isExpense;
    private Date createdDate;
}
