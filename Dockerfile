FROM orangehrm/docker-oracle-xe-11g
COPY init-scripts/ /etc/entrypoint-initdb.d/
