{% set postgresql =  pillar['postgresql'] %}

Ensure postgresql repository is managed:
  pkgrepo.managed:
    - name: deb http://apt.postgresql.org/pub/repos/apt {{ grains['oscodename'] }}-pgdg main
    - file: /etc/apt/sources.list.d/postgresql.list
    - key_url: https://www.postgresql.org/media/keys/ACCC4CF8.asc

Ensure postgresql {{ postgresql['version'] }} is installed:
  pkg.installed:
    - name: postgresql-{{ postgresql['version'] }}
    - refresh: True

Ensure postgresql-contrib is installed:
  pkg.installed:
{%- if postgresql['version'] >= 10 %}
    - name: postgresql-contrib
{%- else %}
    - name: postgresql-contrib-{{ postgresql['version'] }}
{%- endif %}

Ensure conf.d directory exists:
  file.directory:
    - name: /etc/postgresql/{{ postgresql['version'] }}/main/conf.d
    - user: postgres
    - group: postgres

Ensure postgresql.conf includes files from conf.d:
  file.uncomment:
    - name: /etc/postgresql/{{ postgresql['version'] }}/main/postgresql.conf
    - regex: include_dir
    - listen_in:
      - service: postgresql

Ensure postgresql listens on all interfaces:
  file.managed:
    - name: /etc/postgresql/{{ postgresql['version'] }}/main/conf.d/10-listen.conf
    - contents: listen_addresses = '*'
    - user: postgres
    - group: postgres
    - listen_in:
      - service: postgresql

Ensure {{ postgresql['database'] }} database is accessible in the network for postgresql vagrant user:
  file.blockreplace:
    - name: /etc/postgresql/{{ postgresql['version'] }}/main/pg_hba.conf
    - marker_start: '# -- start vagrant --'
    - marker_end: '# -- end vagrant --'
    - append_if_not_found: True
    - content: |
        host     {{ postgresql['database'] }}        vagrant         10.0.2.2/32         md5
    - listen_in:
      - service: postgresql

Ensure data center {{ postgresql['user'] }} user is present:
  postgres_user.present:
    - name: {{ postgresql['user'] }}
    - password: {{ postgresql['user'] }}
    - createroles: True

Ensure {{ postgresql['database'] }} database is present:
  postgres_database.present:
    - name: {{ postgresql['database'] }}
    - owner: {{ postgresql['user'] }}

Ensure postgresql is running:
  service.running:
    - name: postgresql
    - enable: True
