package com.raissa.apis.service.alfin;

import com.raissa.apis.domain.dto.request.payments.ConfirmaTransRequestDto;
import com.raissa.apis.domain.dto.request.payments.ConsultaTransRequestDto;
import com.raissa.apis.domain.dto.response.payments.ConfirmaTransGetResponseDto;
import com.raissa.apis.domain.dto.response.payments.ConsultaTransGetResponseDto;

import java.util.Map;

public interface ALFINEmpresaService {
    Map<String, Object> login(Map<String, String> credentials,
                              String transactionId);

    Map<String, Object> saldos(Map<String, String> datos,
                               String transactionId);

    Map<String, Object> movimientos(String tokenAlterno,
                                    String sessionToken,
                                    String transactionId,
                                    String usuario,
                                    String numCuenta,
                                    String fechaInicio,
                                    String fechaFin);

    ConsultaTransGetResponseDto consultaTransferencia(ConsultaTransRequestDto datos,
                                                      String transactionId);

    ConfirmaTransGetResponseDto confirmaTransferencia(ConfirmaTransRequestDto datos,
                                                      String transactionId);
}
