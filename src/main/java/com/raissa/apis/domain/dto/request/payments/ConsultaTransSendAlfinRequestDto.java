package com.raissa.apis.domain.dto.request.payments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaTransSendAlfinRequestDto {
    @JsonProperty("ClienteBaaS")
    private String clienteBaaS;

    @JsonProperty("CuentaBaaS")
    private String cuentaBaaS;

    @JsonProperty("Moneda")
    private Integer moneda;

    @JsonProperty("Importe")
    private Double importe;

    @JsonProperty("CodigoTransaccion")
    private Integer codigoTransaccion;

    @JsonProperty("BancoDestino")
    private Integer bancoDestino;

    @JsonProperty("SucursalDestino")
    private Integer sucursalDestino;

    @JsonProperty("Tarjeta")
    private String tarjeta;

    @JsonProperty("CCIBeneficiario")
    private String cciBeneficiario;

    @JsonProperty("MismoTitular")
    private String mismoTitular;

    @JsonProperty("TipoDocumentoOrdenante")
    private Integer tipoDocumentoOrdenante;

    @JsonProperty("DocumentoOrdenante")
    private String documentoOrdenante;

    @JsonProperty("NombreOrdenante")
    private String nombreOrdenante;

    @JsonProperty("ApellidoPaternoOrdenante")
    private String apellidoPaternoOrdenante;

    @JsonProperty("ApellidoMaternoOrdenante")
    private String apellidoMaternoOrdenante;
}
