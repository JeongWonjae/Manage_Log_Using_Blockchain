# Log BlockChain Database
___

## Data Defination Language Command

### #1 Create Database

create database logbck_project;

### #2 Create Public Tables

create table logchain_wtmp(
blockID bigint NOT NULL,
ip varchar(30) NOT NULL,
log text NOT NULL,
hash varchar(100) NOT NULL,
pHash varchar(100) NOT NULL,
timeStamp bigint NOT NULL,
valid int not null,
CONSTRAINT wtmpValidCheck check (valid in (0, 1, 4)),
PRIMARY KEY (hash, pHash)
);

create table logchain_messages(
blockID bigint NOT NULL,
ip varchar(30) NOT NULL,
log text NOT NULL,
hash varchar(100) NOT NULL,
pHash varchar(100) NOT NULL,
timeStamp bigint NOT NULL,
valid int not null,
CONSTRAINT messagesValidCheck check (valid in (0, 1, 4)),
PRIMARY KEY (hash, pHash)
);

create table logchain_auth(
blockID bigint NOT NULL,
ip varchar(30) NOT NULL,
log text NOT NULL,
hash varchar(100) NOT NULL,
pHash varchar(100) NOT NULL,
timeStamp bigint NOT NULL,
valid int not null,
CONSTRAINT authValidCheck check (valid in (0, 1, 4)),
PRIMARY KEY (hash, pHash)
);

create table logchain_daemon(
blockID bigint NOT NULL,
ip varchar(30) NOT NULL,
log text NOT NULL,
hash varchar(100) NOT NULL,
pHash varchar(100) NOT NULL,
timeStamp bigint NOT NULL,
valid int not null,
CONSTRAINT daemonValidCheck check (valid in (0, 1, 4)),
PRIMARY KEY (hash, pHash)
);

create table logchain_user(
blockID bigint NOT NULL,
ip varchar(30) NOT NULL,
log text NOT NULL,
hash varchar(100) NOT NULL,
pHash varchar(100) NOT NULL,
timeStamp bigint NOT NULL,
valid int not null,
CONSTRAINT userValidCheck check (valid in (0, 1, 4)),
PRIMARY KEY (hash, pHash)
);

create table logchain_boot(
blockID bigint NOT NULL,
ip varchar(30) NOT NULL,
log text NOT NULL,
hash varchar(100) NOT NULL,
pHash varchar(100) NOT NULL,
timeStamp bigint NOT NULL,
valid int not null,
CONSTRAINT bootValidCheck check (valid in (0, 1, 4)),
PRIMARY KEY (hash, pHash)
);

create table logchain_btmp(
blockID bigint NOT NULL,
ip varchar(30) NOT NULL,
log text NOT NULL,
hash varchar(100) NOT NULL,
pHash varchar(100) NOT NULL,
timeStamp bigint NOT NULL,
valid int not null,
CONSTRAINT btmpValidCheck check (valid in (0, 1, 4)),
PRIMARY KEY (hash, pHash)
);

create table logchain_utmp(
blockID bigint NOT NULL,
ip varchar(30) NOT NULL,
log text NOT NULL,
hash varchar(100) NOT NULL,
pHash varchar(100) NOT NULL,
timeStamp bigint NOT NULL,
valid int not null,
CONSTRAINT utmpValidCheck check (valid in (0, 1, 4)),
PRIMARY KEY (hash, pHash)
);

create table logchain_dpkg(
blockID bigint NOT NULL,
ip varchar(30) NOT NULL,
log text NOT NULL,
hash varchar(100) NOT NULL,
pHash varchar(100) NOT NULL,
timeStamp bigint NOT NULL,
valid int not null,
CONSTRAINT dpkgValidCheck check (valid in (0, 1, 4)),
PRIMARY KEY (hash, pHash)
);

create table logchain_secure(
blockID bigint NOT NULL,
ip varchar(30) NOT NULL,
log text NOT NULL,
hash varchar(100) NOT NULL,
pHash varchar(100) NOT NULL,
timeStamp bigint NOT NULL,
valid int not null,
CONSTRAINT secureValidCheck check (valid in (0, 1, 4)),
PRIMARY KEY (hash, pHash)
);

create table node(
ip varchar(30) NOT NULL,
pw varchar(50) NOT NULL,
description varchar(30),
serverPath varchar(30) NOT NULL,
errorPath varchar(30) NOT NULL,
PRIMARY KEY (ip)
);

### #3 Create Private Tables

create table filehash(
filePath varchar(30) NOT NULL,
hash varchar(100) NOT NULL,
PRIMARY KEY (filePath)
);

create table serverActivate(
startup varchar(100) NOT NULL,
shutdown varchar(100) NOT NULL
)

## Data Manipulation Language Command
### #1 Common 
insert into node values("192.168.11.104", "1234", "HR-TEAM-PC-1", "/jsp/bckProject/storeLog.jsp", "/jsp/bckProject/failLog.jsp");
insert into node values("192.168.11.105", "randd", "R&D-TEAM-PC-1", "/serv/storeLog.jsp", "/serv/failLog.jsp");
insert into node values("192.168.11.8", "1111", "HR-TEAM-PC-2", "/bckProject/storeLog.jsp", "/bckProject/failLog.jsp");
### #2 Not Common
192.168.11.104 :
```sh
insert into serverActivate values("c:\tomcat\bin\startup.bat", "c:\tomcat\bin\shutdown.bat")
```
192.168.11.105 :
```sh
insert into serverActivate values("/root/serv/tomcat/bin/startup.sh", "/root/serv/tomcat/bin/shutdown.sh")
```
192.168.11.8 :
```sh
insert into serverActivate values("/usr/server/tomcat/bin/startup.sh", "/usr/server/tomcat/bin/shutdown.sh")
```
### #3 Assistant
delete from logchain_wtmp;
delete from logchain_utmp;
delete from logchain_btmp;
delete from logchain_dpkg;
delete from logchain_auth;
delete from logchain_user;
delete from logchain_daemon;
delete from logchain_boot;
delete from logchain_messages;