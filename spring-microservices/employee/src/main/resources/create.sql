CREATE TABLE employee (
                          id SERIAL PRIMARY KEY,
                          first_name VARCHAR(255) NOT NULL,
                          last_name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          department_code VARCHAR(255)
);

INSERT INTO employee (first_name, last_name, email, department_code)
VALUES
    ('John', 'Doe', 'john.doe@example.com', 'HR001'),
    ('Jane', 'Smith', 'jane.smith@example.com', 'IT001'),
    ('Michael', 'Johnson', 'michael.johnson@example.com', 'FIN001'),
    ('Emily', 'Williams', 'emily.williams@example.com', 'SALES001'),
    ('David', 'Brown', 'david.brown@example.com', 'MKTG001');
