# spring34172issue
Minimal sample to reproduce issue 34172
execute curl "http://localhost:8080/edad?fechaNac=01/01/1990"  
with ver 3.3.7
result {"v_anos":35,"v_mes":0,"v_dias":3}
with ver 3.4.1
{"timestamp":"2025-01-04T13:27:06.392+00:00","status":500,"error":"Internal Server Error","path":"/edad"}
