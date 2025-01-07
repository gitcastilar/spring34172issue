# spring34172issue

Minimal sample to reproduce issue 34172

Execute the following `curl` command:

```bash
curl "http://localhost:8080/edad?fechaNac=01/01/1990"

wuth version 3.3.x:
{
  "v_anos": 35,
  "v_mes": 0,
  "v_dias": 3
}
With version 3.4.x:
{
  "timestamp": "2025-01-04T13:27:06.392+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "path": "/edad"
}

For Oracle images use
docker pull orangehrm/oracle-xe-11g:latest


