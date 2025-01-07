CREATE OR REPLACE PACKAGE PKG_CALCULAR_EDAD IS
   TYPE cursor_generico IS REF CURSOR;

   PROCEDURE p_obtieneedad(
      pin_fechanac IN VARCHAR2,
      out_vdatos   OUT cursor_generico
   );
END PKG_CALCULAR_EDAD;
/

CREATE OR REPLACE PACKAGE BODY PKG_CALCULAR_EDAD IS

   PROCEDURE p_obtieneedad(
      pin_fechanac IN VARCHAR2,
      out_vdatos   OUT cursor_generico
   ) IS
      v_anhos            NUMBER;
      v_mes              NUMBER;
      v_dias             NUMBER;
      
      f_nacimiento DATE; -- Fecha de nacimiento de la persona
      f_calculo DATE;    -- Fecha a la cual deseamos saber su edad
      solo_meses NUMBER;

   BEGIN
      -- Datos iniciales
      f_nacimiento := TO_DATE(pin_fechanac, 'dd/mm/yyyy');
      f_calculo := TO_DATE(TO_CHAR(SYSDATE, 'dd/mm/yyyy'), 'dd/mm/yyyy');

      -- Todo a meses
      solo_meses := MONTHS_BETWEEN(f_calculo, f_nacimiento);

      -- Calcular años, meses y días
      v_anhos := TRUNC(solo_meses / 12);
      v_mes := TRUNC(MOD(solo_meses, 12));
      v_dias := f_calculo - ADD_MONTHS(f_nacimiento, TRUNC(solo_meses));

      -- Abrir el cursor de salida
      OPEN out_vdatos FOR
        SELECT v_anhos AS v_anos,
               v_mes   AS v_mes,
               v_dias  AS v_dias
          FROM dual;

   END p_obtieneedad;

END PKG_CALCULAR_EDAD;
/
