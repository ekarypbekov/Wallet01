package com.wallet.model;


import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "wallet")
public class Wallet {
    private Long id;
    private String name;
    private User user;
    private boolean isActive;
    private Date createdDate;
}
