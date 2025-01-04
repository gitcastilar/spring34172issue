package spring.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ServiceDao {

    private static final Logger logger = LoggerFactory.getLogger(ServiceDao.class);

    @Value("${spring.database.owner:dba}")
    protected String databaseOwner;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EdadEntModel getFecnac(String fechaNac) {
        try {
            logger.info("Calculating age for birth date: {}", fechaNac);

            Map<String, Object> params = new HashMap<>();
            params.put("pin_fechanac", fechaNac);

            Map<String, Object> resultMap = execStoreProcedureMap("pkg_calcular_edad.p_obtieneedad", params);

            if (resultMap == null || !resultMap.containsKey("OUT_VDATOS")) {
                throw new IllegalStateException("Stored procedure did not return expected output");
            }

            List<Map<String, Object>> listOutput = (List<Map<String, Object>>) resultMap.get("OUT_VDATOS");
            if (listOutput == null || listOutput.isEmpty()) {
                throw new IllegalStateException("No data returned from stored procedure");
            }

            Map<String, Object> row = listOutput.get(0);
            EdadEntModel data = new EdadEntModel();
            data.setAnos(row.getOrDefault("V_ANOS", 0) instanceof Integer ? (Integer) row.get("V_ANOS") : Integer.parseInt(String.valueOf(row.get("V_ANOS"))));
            data.setMes(row.getOrDefault("V_MES", 0) instanceof Integer ? (Integer) row.get("V_MES") : Integer.parseInt(String.valueOf(row.get("V_MES"))));
            data.setDias(row.getOrDefault("V_DIAS", 0) instanceof Integer ? (Integer) row.get("V_DIAS") : Integer.parseInt(String.valueOf(row.get("V_DIAS"))));

            return data;

        } catch (Exception e) {
            logger.error("Error while fetching age data: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to execute stored procedure", e);
        }
    }

    public Map<String, Object> execStoreProcedureMap(String storeProc, Map<String, Object> params) {
        try {
            logger.info("Executing stored procedure: {}", storeProc);

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
            jdbcCall.withSchemaName(databaseOwner);
            String[] sQuery = storeProc.split("\\.");
            if (sQuery.length > 1) {
                jdbcCall.withCatalogName(sQuery[0]).withProcedureName(sQuery[1]);
            } else {
                jdbcCall.withProcedureName(sQuery[0]);
            }

            SqlParameterSource paramMap = new MapSqlParameterSource();
            params.forEach(((MapSqlParameterSource) paramMap)::addValue);

            return jdbcCall.execute(paramMap);

        } catch (Exception e) {
            logger.error("Error executing stored procedure: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to execute stored procedure", e);
        }
    }
}
