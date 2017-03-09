CREATE TABLE IF NOT EXISTS address
(
  id           VARCHAR(255) PRIMARY KEY NOT NULL,
  street       VARCHAR(255)             NOT NULL,
  number       INT                      NOT NULL,
  cep          VARCHAR(8)               NOT NULL,
  city         VARCHAR(255)             NOT NULL,
  uf           CHAR(2)                  NOT NULL,
  neighborhood VARCHAR(255)             NULL,
  complement   VARCHAR(255)             NULL
);
CREATE UNIQUE INDEX IF NOT EXISTS "address_id_uindex"
  ON address (id);
CREATE UNIQUE INDEX IF NOT EXISTS "ADDRESS_STREET_CEP_NUMBER_uindex"
  ON PUBLIC.ADDRESS (STREET, CEP, NUMBER);


MERGE INTO address (id, street, number, cep, city, uf, neighborhood, complement)
VALUES
  ('bb79a9f2-0ebc-4537-a0df-e39591039008', 'Av. Paulista', 1340, '01234456', 'São Paulo', 'SP', 'Bela Vista',
   'apto 51');

MERGE INTO address (id, street, number, cep, city, uf, neighborhood, complement)
VALUES
  ('49fe190c-405b-4535-90bd-b4f33a4cbe6a', 'Av. Paulista', 1541, '01234560', 'São Paulo', 'SP', 'Bela Vista',
   'apto 51');

MERGE INTO address (id, street, number, cep, city, uf, neighborhood, complement)
VALUES
  ('6b6c0c3e-6127-4792-90d9-89a32bf43578', 'Av. Brigadeiro Faria Lima', 1340, '01234450', 'São Paulo', 'SP',
   'Pinheiros',
   'apto 43');