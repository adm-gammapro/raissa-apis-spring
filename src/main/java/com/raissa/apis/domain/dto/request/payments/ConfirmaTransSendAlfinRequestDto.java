package com.raissa.apis.domain.dto.request.payments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ConfirmaTransSendAlfinRequestDto {
    @JsonProperty("ClienteBaaS")
    private String clienteBaaS;

    @JsonProperty("CuentaBaaS")
    private String cuentaBaaS;

    @JsonProperty("Moneda")
    private Integer moneda;

    @JsonProperty("Importe")
    private Double importe;

    @JsonProperty("TransferenciaId")
    private String transferenciaId;

    @JsonProperty("MPE001IDL")
    private Integer mpe001idl;
}
