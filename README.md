# DBKit #

A simple database cli client for most of the databases via JDBC. 


### Features
* Support Unix pipeline
* Support interactive mode like MySQL's cli client


### Examples

#### example 1
```sh
echo "select 1 + 2 as c1 " | dbkit-cli
```

Result:
```
c1 | c2
-------
3  | 7 
```

#### example 2
```
$ dbkit-cli -i

dbkit=>show tables;

Tables_in_xyz         
-------------------------
task
user

dbkit=>desc user;

Field    | Type        | Null | Key | Default | Extra         
--------------------------------------------------------------
id       | int(11)     | NO   | PRI | null    | auto_increment
username | varchar(45) | NO   | UNI | null    |               
password | varchar(45) | NO   |     | null    |               


```