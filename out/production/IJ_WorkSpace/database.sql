
DELIMITER Ultimate DELIMITER v13.1.1 (64 bit)
MySQL - 8.0: Database - xsl
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`xsl` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `xsl`;

/*Table structure for table `passwd_data` */

drop table if exists `passwd_date`;
create table passwd_date
(
    account varchar(20) not null,
    passwd  varchar(20) not null,
    primary key (account, passwd)
);

insert into xsl.passwd_date (account, passwd) values ('12345678', '12345678');
insert into xsl.passwd_date (account, passwd) values ('123456789', '11243224');
insert into xsl.passwd_date (account, passwd) values ('2034576972', 'xsl203457');
insert into xsl.passwd_date (account, passwd) values ('xuishilong', '12345678');



drop table if exists `teacher_salary`;
create table teacher_salary
(
    id              double      not null,
    name            varchar(20) not null,
    sex             varchar(5)  not null,
    birth           int         not null,
    age             int         not null,
    marriage_status varchar(5)  not null,
    address         varchar(40) not null,
    position        varchar(15) not null,
    tootle_salary   double      null,
    average_salary  double      null,
    primary key (id, name, sex, birth, age, marriage_status, address, position)
);

insert into xsl.teacher_salary (id, name, sex, birth, age, marriage_status, address, position, tootle_salary, average_salary) values (11111111, '张三', '33', 1999, 55, '不知', '得得的我的胃纷纷恶风', '高级教师', 3000, 4000);
insert into xsl.teacher_salary (id, name, sex, birth, age, marriage_status, address, position, tootle_salary, average_salary) values (22222222, '李四', '33', 1999, 55, '不知', '得得的我的胃纷纷恶风', '特级教师', 3000, 4000);
insert into xsl.teacher_salary (id, name, sex, birth, age, marriage_status, address, position, tootle_salary, average_salary) values (33333333, '王五', '33', 1999, 55, '不知', '得得的我的胃纷纷恶风', '一级教师', 3333, 4000);
insert into xsl.teacher_salary (id, name, sex, birth, age, marriage_status, address, position, tootle_salary, average_salary) values (44444444, '李逵', '33', 1999, 55, '不知', '得得的我的胃纷纷恶风', '一级教师', 3333, 4000);
insert into xsl.teacher_salary (id, name, sex, birth, age, marriage_status, address, position, tootle_salary, average_salary) values (55555555, '张飞', '33', 1999, 55, '不知', '得得的我的胃纷纷恶风', '特级教师', 3333, 4000);
insert into xsl.teacher_salary (id, name, sex, birth, age, marriage_status, address, position, tootle_salary, average_salary) values (66666666, '宋江', '33', 1999, 55, '不知', '得得的我的胃纷纷恶风', '一级教师', 3333, 4000);
insert into xsl.teacher_salary (id, name, sex, birth, age, marriage_status, address, position, tootle_salary, average_salary) values (77777777, '林冲', '33', 1999, 55, '不知', '得得的我的胃纷纷恶风', '高级教师', 3333, 4000);



