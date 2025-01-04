create or replace PACKAGE BODY PKG_CALCULAR_EDAD IS

  /************************************************************************/
  --*** FECHA MODIFICACION: 01/07/2011
  --*** AUTOR CREACION    : EUGENIO CLAVERO P.
  --*** MODIFICACION      : SE CAMBIA LA FORMA DE OBTENER LOS ANOS, MESES Y DIAS A PARTIDR FECHA NACIMIENTO     
  /**********************************************************************************/

  PROCEDURE p_obtieneedad(pin_fechanac IN VARCHAR2,
                          out_vdatos   OUT cursor_generico) IS
  
    v_anhos            NUMBER;
    v_mes              NUMBER;
    v_dias             NUMBER;
    
    f_nacimiento date; --fecha de nacimiento de la persona
    f_calculo date; --fecha a la cual deseamos saber su edad
    solo_meses number;
  
    v_minutos_al_mes   NUMBER;
    v_minutos_por_dia  NUMBER;
    v_cantidad_minutos NUMBER;
    v_residuo_mes      NUMBER;
    v_residuo_dias     NUMBER;
    v_dias_al_anho     NUMBER;
    v_hora_del_dia     NUMBER;
    v_minutos_de_hora  NUMBER;
    v_minutos_al_anho  NUMBER;
    v_meses_del_anho   NUMBER;
    v_fecha_actual     DATE;
  
  BEGIN
  
--datos iniciales
f_nacimiento := to_date(pin_fechanac, 'dd/mm/yyyy');
f_calculo :=  to_date(to_char(sysdate, 'dd/mm/yyyy'),'dd/mm/yyyy');
 
--todo a meses
solo_meses := months_between(f_calculo, f_nacimiento);
--
v_anhos := trunc(solo_meses / 12);
v_mes := trunc(mod(solo_meses, 12));
v_dias := f_calculo - add_months(f_nacimiento, trunc(solo_meses));                           
  
    OPEN out_vdatos FOR
      SELECT v_anhos AS v_anos
            ,v_mes   AS v_mes
            ,v_dias  AS v_dias
        FROM dual;
  
  END p_obtieneedad;

END pkg_calcular_edad;