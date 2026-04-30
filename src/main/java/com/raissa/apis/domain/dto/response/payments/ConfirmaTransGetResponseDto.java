package com.raissa.apis.domain.dto.response.payments;

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
public class ConfirmaTransGetResponseDto {
    @JsonProperty("MovimientoUId")
    private Long movimientoUid;

    @JsonProperty("CodRespuesta")
    private String codRespuesta;

    @JsonProperty("DscRespuesta")
    private String dscRespuesta;

    @JsonProperty("Erroresnegocio")
    private ErroresNegocioDto erroresNegocio;

    @JsonProperty("Btoutreq")
    private BtoutreqDto btoutreq;
}
