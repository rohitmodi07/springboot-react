DROP TABLE IF EXISTS EMPLOYEE;

CREATE TABLE EMPLOYEE (
  employee_id INT AUTO_INCREMENT  PRIMARY KEY,
  employee_mail_id VARCHAR(250) NOT NULL,
  level VARCHAR(250) NOT NULL,
  designation VARCHAR(250) NOT NULL,
  date_of_joining VARCHAR(250) NOT NULL,
  date_of_resignation VARCHAR(250) DEFAULT NULL,
  last_working_day VARCHAR(250) DEFAULT NULL,
  resignation_reason VARCHAR(250) DEFAULT NULL,
  manager_email_id VARCHAR(250)
);