CREATE TABLE department (
                            id SERIAL PRIMARY KEY,
                            department_name VARCHAR(255) NOT NULL,
                            department_description VARCHAR(255),
                            department_code VARCHAR(255) NOT NULL UNIQUE
);



INSERT INTO department (department_name, department_description, department_code)
VALUES
    ('HR', 'Human Resources Department', 'HR001'),
    ('IT', 'Information Technology Department', 'IT001'),
    ('FIN', 'Finance Department', 'FIN001'),
    ('SALES', 'Sales Department', 'SALES001'),
    ('MARKETING', 'Marketing Department', 'MKTG001');
