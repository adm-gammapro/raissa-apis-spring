package com.raissa.apis.controller.alfin;

import com.raissa.apis.domain.dto.request.payments.ConfirmaTransRequestDto;
import com.raissa.apis.domain.dto.request.payments.ConsultaTransRequestDto;
import com.raissa.apis.domain.dto.response.payments.ConfirmaTransGetResponseDto;
import com.raissa.apis.domain.dto.response.payments.ConsultaTransGetResponseDto;
import com.raissa.apis.domain.entity.RequestInformation;
import com.raissa.apis.domain.entity.Session;
import com.raissa.apis.exception.AlfinException;
import com.raissa.apis.service.alfin.ALFINEmpresaService;
import com.raissa.apis.service.commons.LoggingService;
import com.raissa.apis.service.commons.ValidationService;
import com.raissa.apis.util.ResponseGeneric;
import com.raissa.comun.util.Constante;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/alfin-empresa-api")
@RequiredArgsConstructor
@Slf4j
public class ALFINEmpresaController {
    private final LoggingService loggingService;
    private final ValidationService validationService;
    private final ALFINEmpresaService alfinEmpresaService;

    /**
     * Login ALFIN
     *
     * @param credentials datos de acceso a la plataforma
     * @param transactionId id de transaccion
     * @param request datos de la peticion
     * @return {@link Map} respuesta de logueo
     */
    @PostMapping("/login/{transactionId}")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials,
                                                     @PathVariable String transactionId,
                                                     HttpServletRequest request) {

        log.info("Solicitud login ALFIN recibida");
        String clientIp = ResponseGeneric.getClientIp(request);
        String userAgent = request.getHeader(Constante.KEY_USER_AGENT);
        Session session = validationService.validateSession(transactionId);

        RequestInformation logRequest = loggingService.logRequest(session, clientIp, Constante.TIPO_REQUEST_LOGIN_ALFIN, userAgent);

        try {
            Map<String, Object> alfinResult = alfinEmpresaService.login(credentials, transactionId);

            if (!(boolean) alfinResult.get(Constante.KEY_SUCCESS_CODE)) {
                loggingService.updateResponseStatus(logRequest.getId(), Constante.RESP_REQUEST_ERROR);

                throw new AlfinException("Error en login ALFIN: " + alfinResult.get(Constante.KEY_MESSAGE));
            }

            loggingService.updateResponseStatus(logRequest.getId(), Constante.RESP_REQUEST_EXITO);

            log.info("Login ALFIN exitoso para: {}, transactionId: {}", session.getAccount().getFullName(), transactionId);

            return ResponseEntity.ok(alfinResult);

        } catch (Exception e) {
            log.error("Error en login ALFIN: {}", e.getMessage());

            loggingService.updateResponseStatus(logRequest.getId(), Constante.RESP_REQUEST_ERROR);
            Map<String, Object> errorResponse = ResponseGeneric.buildSuccessResponse(transactionId, e.getMessage(), false);

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    /**
     * Obtener saldo de ALFIN
     *
     * @param transactionId id de transaccion
     * @param request datos de la peticion
     * @return {@link Map} datos con cuentas y saldos
     */
    @PostMapping("/saldo/{transactionId}")
    public ResponseEntity<Map<String, Object>> obtenerSaldo(@PathVariable String transactionId,
                                                            @RequestBody Map<String, String> datos,
                                                            HttpServletRequest request) {

        log.info("Solicitud saldo ALFIN recibida, transactionId: {}", transactionId);

        String clientIp = ResponseGeneric.getClientIp(request);
        String userAgent = request.getHeader(Constante.KEY_USER_AGENT);
        Session session = validationService.validateSession(transactionId);

        RequestInformation logRequest = loggingService.logRequest(session, clientIp, Constante.TIPO_REQUEST_OBTENER_SALDO_ALFIN, userAgent);

        try {

            Map<String, Object> resp = alfinEmpresaService.saldos(datos, transactionId);

            loggingService.updateResponseStatus(logRequest.getId(), Constante.RESP_REQUEST_EXITO);

            log.info("Saldo ALFIN obtenido exitosamente, transactionId: {}", transactionId);

            return ResponseEntity.ok(resp);

        } catch (Exception e) {
            log.error("Error obteniendo saldo ALFIN: {}", e.getMessage());

            loggingService.updateResponseStatus(logRequest.getId(), Constante.RESP_REQUEST_ERROR);
            Map<String, Object> errorResponse = ResponseGeneric.buildSuccessResponse(transactionId, e.getMessage(), false);

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    /**
     * Obtener movimientos de ALFIN
     *
     * @param transactionId id de transaccion
     * @param numCuenta numero de cuenta
     * @param fechaInicio fecha de inicio para búsqueda
     * @param fechaFin fecha de fin par abúsqueda
     * @param request datos de la peticion
     * @return {@link Map} datos con los movimientos de la cuenta solicitada
     */
    @PostMapping("/transacciones/{numCuenta}/{transactionId}")
    public ResponseEntity<Map<String, Object>> obtenerTransacciones(@PathVariable String transactionId,
                                                                    @PathVariable String numCuenta,
                                                                    @RequestParam String tokenAlterno,
                                                                    @RequestParam String sessionToken,
                                                                    @RequestParam String usuario,
                                                                    @RequestParam String fechaInicio,
                                                                    @RequestParam String fechaFin,
                                                                    HttpServletRequest request) {

        log.info("Solicitud transacciones ALFIN recibida, transactionId: {}", transactionId);

        String clientIp = ResponseGeneric.getClientIp(request);
        String userAgent = request.getHeader(Constante.KEY_USER_AGENT);
        Session session = validationService.validateSession(transactionId);

        RequestInformation logRequest = loggingService.logRequest(session, clientIp, Constante.TIPO_REQUEST_OBTENER_MOV_ALFIN, userAgent);

        try {
            Map<String, Object> resp = alfinEmpresaService.movimientos(tokenAlterno, sessionToken, transactionId, usuario, numCuenta, fechaInicio, fechaFin);

            loggingService.updateResponseStatus(logRequest.getId(), Constante.RESP_REQUEST_EXITO);

            log.info("Movimientos ALFIN obtenido exitosamente, transactionId: {}", transactionId);

            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            log.error("Error  ALFIN: {}", e.getMessage());

            loggingService.updateResponseStatus(logRequest.getId(), Constante.RESP_REQUEST_ERROR);
            Map<String, Object> errorResponse = ResponseGeneric.buildSuccessResponse(transactionId, e.getMessage(), false);

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    /**
     * Consultar transferencia inmediata
     *
     * @param transactionId id de transaccion
     * @param request datos de la peticion
     * @return {@link Map} datos con cuentas y saldos
     */
    @PostMapping("/consultar-transferencia/{transactionId}")
    public ResponseEntity<ConsultaTransGetResponseDto> consultarTransferencia(@PathVariable String transactionId,
                                                                              @RequestBody ConsultaTransRequestDto datos,
                                                                              HttpServletRequest request) {

        log.info("Solicitud para consulta de transferencia inmediata ALFIN recibida, transactionId: {}", transactionId);

        String clientIp = ResponseGeneric.getClientIp(request);
        String userAgent = request.getHeader(Constante.KEY_USER_AGENT);
        Session session = validationService.validateSession(transactionId);

        RequestInformation logRequest = loggingService.logRequest(session, clientIp, Constante.TIPO_REQUEST_CONSULTAR_TRANSFERENCIA_ALFIN, userAgent);

        ConsultaTransGetResponseDto resp = alfinEmpresaService.consultaTransferencia(datos, transactionId);

        if (resp.getBtoutreq().getEstado().equals(Constante.ESTADO_ALFIN_ERROR)){
            loggingService.updateResponseStatus(logRequest.getId(), Constante.RESP_REQUEST_ERROR);
        } else {
            loggingService.updateResponseStatus(logRequest.getId(), Constante.RESP_REQUEST_EXITO);
        }

        log.info("Consulta transferencia inmediata realizado exitosamente, transactionId: {}", transactionId);

        return ResponseEntity.ok(resp);
    }

    /**
     * Ejecutar transferencia inmediata
     *
     * @param transactionId id de transaccion
     * @param request datos de la peticion
     * @return {@link Map} datos con cuentas y saldos
     */
    @PostMapping("/confirmar-transferencia/{transactionId}")
    public ResponseEntity<ConfirmaTransGetResponseDto> confirmarTransferencia(@PathVariable String transactionId,
                                                                              @RequestBody ConfirmaTransRequestDto datos,
                                                                              HttpServletRequest request) {

        log.info("Solicitud para confirmar de transferencia inmediata ALFIN recibida, transactionId: {}", transactionId);

        String clientIp = ResponseGeneric.getClientIp(request);
        String userAgent = request.getHeader(Constante.KEY_USER_AGENT);
        Session session = validationService.validateSession(transactionId);

        RequestInformation logRequest = loggingService.logRequest(session, clientIp, Constante.TIPO_REQUEST_CONSULTAR_TRANSFERENCIA_ALFIN, userAgent);

        ConfirmaTransGetResponseDto resp = alfinEmpresaService.confirmaTransferencia(datos, transactionId);

        if (resp.getBtoutreq().getEstado().equals(Constante.ESTADO_ALFIN_ERROR)){
            loggingService.updateResponseStatus(logRequest.getId(), Constante.RESP_REQUEST_ERROR);
        } else {
            loggingService.updateResponseStatus(logRequest.getId(), Constante.RESP_REQUEST_EXITO);
        }

        log.info("confirmacion transferencia inmediata realizado exitosamente, transactionId: {}", transactionId);

        return ResponseEntity.ok(resp);
    }
}