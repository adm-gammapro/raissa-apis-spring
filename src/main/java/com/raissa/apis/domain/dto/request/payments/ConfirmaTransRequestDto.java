package com.raissa.apis.domain.dto.request.payments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ConfirmaTransRequestDto {
    @JsonProperty("ClienteBaaS")
    private String clienteBaaS;

    @JsonProperty("CuentaBaaS")
    private String cuentaBaaS;

    @JsonProperty("Moneda")
    private String moneda;

    @JsonProperty("Importe")
    private BigDecimal importe;

    @JsonProperty("TransferenciaId")
    private String transferenciaId;

    @JsonProperty("MPE001IDL")
    private String mpe001idl;

    private String tokenAlterno;

    private String sessionToken;
}
