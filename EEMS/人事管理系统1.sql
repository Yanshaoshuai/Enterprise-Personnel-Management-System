

-----------人事管理系统表----------------------------------------------------------------------------------------
create table partment(
pid varchar(4) not null primary key,
pname varchar(20) not null,
ppid varchar(4) default(0) not null --父部门
);
create table employee(
  eid varchar(20) not null primary key ,
  ename varchar(20) not null,
  esex  char(2) not null,
  eIn_Date date not null,
  eAge number(2) not null,
  Pid references partment(pid),
  eJob varchar(20) not null,
  eSalary number (9) not null
);


create table absence(
eid references employee(eid) ,
abDate date not null,
abReason varchar(20) default('无理由') not null,
 primary key(eid,abDate)
);
create table salary(
eid references employee(eid) ,
abcount number(4) not null,
sYearMonth varchar(10) not null,
primary key(eid,sYearMonth)
);
create table manager(
mid varchar(20) not null,
mpwd varchar(20) not null,
mname varchar(20) not null
);
