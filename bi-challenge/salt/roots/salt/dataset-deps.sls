Ensure build-essential is installed:
  pkg.installed:
    - name: build-essential

Ensure p7zip is installed:
  pkg.installed:
    - name: p7zip-full

Ensure git is installed:
  pkg.installed:
    - name: git

Ensure python is installed:
  pkg.installed:
    - name: python3

Ensure python-pip is installed:
  pkg.installed:
    - name: python-pip

Ensure python3-pip is installed:
  pkg.installed:
    - name: python3-pip

Ensure python3-lxml is installed:
  pkg.installed:
    - name: python3-lxml

Ensure psycopg2-binary is installed:
  pip.installed:
    - name: psycopg2-binary
    - bin_env: /usr/bin/pip3

{% set database_name = pillar['postgresql']['database'] %}

Ensure dataset directory exists:
  file.directory:
    - name: /dataset/{{ database_name }}.stackexchange.com
    - makedirs: True
    - user: vagrant
    - group: vagrant

Ensure dataset 7z file is present:
  cmd.run:
    - name: curl -L https://archive.org/download/stackexchange/{{ database_name }}.stackexchange.com.7z -o /dataset/{{ database_name }}.stackexchange.com.7z

Ensure dataset 7z file is extracted:
  cmd.run:
    - name: 7z x -o/dataset/{{ database_name }}.stackexchange.com /dataset/{{ database_name }}.stackexchange.com.7z
    - creates: /dataset/{{ database_name }}.stackexchange.com/Users.xml

Ensure dataset stackexchange dump to postgres repo is present:
  cmd.run:
    - name: |
        cd /dataset/{{ database_name }}.stackexchange.com
        git init .
        git remote add -t \* -f origin git://github.com/Networks-Learning/stackexchange-dump-to-postgres.git
        git checkout master
    - cwd: /usr/local/src
    - shell: /bin/bash
    - timeout: 300
    - unless: test -x /dataset/{{ database_name }}.stackexchange.com/load_into_pg.py

{% for table in ['Users', 'Posts', 'Badges', 'Tags', 'Votes', 'PostLinks', 'PostHistory', 'Comments'] %}
Ensure dataset {{ table }} table is present:
  cmd.run:
    - name: |
        cd /dataset/{{ database_name }}.stackexchange.com
        echo 'y' | python3 load_into_pg.py --foreign-keys --with-post-body -d {{ database_name }} {{ table }}
    - cwd: /usr/local/src
    - shell: /bin/bash
    - timeout: 300
    - runas: vagrant
    - unless: psql {{ database_name }} -c '\d {{ table }}'
{% endfor %}

Ensure dataset types are present:
  cmd.run:
    - name: |
        cd /dataset/{{ database_name }}.stackexchange.com
        psql {{ database_name }} < ./sql/final_post.sql
    - cwd: /usr/local/src
    - shell: /bin/bash
    - timeout: 300
    - runas: vagrant
    - unless: psql {{ database_name }} -c '\d PostTypes'
