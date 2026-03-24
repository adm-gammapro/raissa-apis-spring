package com.raissa.apis.service.commons;


import com.raissa.apis.domain.entity.Account;
import com.raissa.apis.domain.entity.Session;

public interface ValidationService {
    /**
     * Valida credenciales de acceso (key_access y secret_access)
     *
     * @param keyAccess llave o usuario de acceso
     * @param secretAccess contraseña sin encriptar de la llave o usuario
     * return {@link Account}
     */
    Account validateCredentials(String keyAccess, String secretAccess);

    /**
     * Valida y obtiene una sesión activa por transactionId
     *
     * @param transactionId id de la transaccion
     * return {@link Session}
     */
    Session validateSession(String transactionId);
}
