-- 1. FUNCIÓN: Calcular Valor (Separación de responsabilidad)
CREATE OR REPLACE FUNCTION FUNC_CALCULAR_VALOR(p_metros INT, p_zona VARCHAR2) 
RETURN NUMBER IS
BEGIN
    IF p_zona = 'CENTRO' THEN RETURN p_metros * 2000;
    ELSIF p_zona = 'PERIFERIA' THEN RETURN p_metros * 1000;
    ELSE RETURN p_metros * 500;
    END IF;
END;
/

-- 2. PROCEDIMIENTO: Crear Notificación (Insertar en tabla)
CREATE OR REPLACE PROCEDURE PROC_CREAR_NOTIFICACION(p_ciudadano_id INT, p_valor NUMBER) IS
BEGIN
    INSERT INTO NOTIFICACIONES (ID, CIUDADANO_ID, MENSAJE, FECHA_ENVIO, ESTADO)
    VALUES (SEQ_NOTIF.NEXTVAL, p_ciudadano_id, 
            'Su inmueble ha sido valorado en: ' || p_valor, SYSDATE, 'PENDIENTE');
END;
/

-- 3. PROCEDIMIENTO ORQUESTADOR (El que llama Java)
CREATE OR REPLACE PROCEDURE PROCESO_COMPLETO_VALORACION(p_inmueble_id IN NUMBER) IS
    v_metros NUMBER;
    v_zona VARCHAR2(50);
    v_ciudadano_id NUMBER;
    v_valor_calculado NUMBER;
BEGIN
    -- Obtener datos
    SELECT METROS_CUADRADOS, ZONA, CIUDADANO_ID 
    INTO v_metros, v_zona, v_ciudadano_id
    FROM INMUEBLES WHERE ID = p_inmueble_id;

    -- Paso 1: Calcular
    v_valor_calculado := FUNC_CALCULAR_VALOR(v_metros, v_zona);

    -- Paso 2: Actualizar Inmueble
    UPDATE INMUEBLES SET VALOR_CATASTRAL = v_valor_calculado WHERE ID = p_inmueble_id;

    -- Paso 3: Notificar
    PROC_CREAR_NOTIFICACION(v_ciudadano_id, v_valor_calculado);

    COMMIT;
END;
/