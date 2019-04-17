# BI Challenge


## Tools Setup

### MacOS

Install [brew](https://brew.sh/)

Install virtualbox and vagrant
```
brew cask install virtualbox
brew cask install vagrant
```

### Windows

Download and install Virtualbox from https://www.virtualbox.org/wiki/Downloads

Download and install Vagrant from https://www.vagrantup.com/downloads.html

### Linux (Debian based)

Install virtualbox and vagrant using:
```
sudo apt install virtualbox
sudo apt install vagrant
```

## Dataset Setup

Setup the dataset by running `vagrant up` on the repository folder (will take some minutes to download the base files).
Check if there are no errors while setting up the VM (if the dataset download fails run `vagrant provision`).

You can access the dataset using a PostgreSQL client (like [DBeaver](https://dbeaver.io/) or [pgAdmin](https://www.pgadmin.org/)) on:
 * Host: `localhost`
 * Port: `65432`
 * DB name: `worldbuilding`
 * Username: `vagrant`
 * Password: `vagrant`

Diagram for quick reference (excluding types tables):
![db_diagram](https://github.com/Triggerise/bi-challenge/blob/master/bi-challenge-diagram.png?raw=true)

## Challenge

Go to [Challenge](Challenge.md) for the actual Challenge!
