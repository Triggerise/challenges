Ensure bashrc is setup correctly:
  file.blockreplace:
    - name: /home/vagrant/.bashrc
    - marker_start: '# start custom changes'
    - marker_end: '# end custom changes'
    - append_if_not_found: True
    - content: |
        export LC_ALL=en_US.UTF-8
        export PS1='\n\[\e[1;37m\]\[\e[1;32m\]\u\[\e[0;39m\]@\h:\[\e[1;33m\]\w\[\e[0;39m\]\[\e[1;35m\]$(__git_ps1 " (%s)")\[\e[0;39m\] \[\e[1;37m\]\[\e[0;39m\]\n$ '
        GIT_PS1_SHOWDIRTYSTATE=true
        cd /vagrant
