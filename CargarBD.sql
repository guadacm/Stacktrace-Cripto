
INSERT INTO `usuarios` (`userid`, `dni`, `sexo`, `nombre`, `apellido`, `email`, `telefono`, `password`) 
VALUES 
    (NULL, '12345678', 'FEMENINO', 'MARIA', 'ROCA', 'MARIA@ROCA', '2664721818', '$argon2id$v=19$m=1024,t=1,p=1$PJAfKiaaPTkFkJqJZPIeGA$7gmdg3/z8TJnjE7qmWsT9ngC1JnxmXEbwptpPv8qs3s'),
    (NULL, '45678936', 'FEMENINO', 'HELENA', 'SANCHEZ', 'HELENA@SANCHEZ', '2664721818', '$argon2id$v=19$m=1024,t=1,p=1$PJAfKiaaPTkFkJqJZPIeGA$7gmdg3/z8TJnjE7qmWsT9ngC1JnxmXEbwptpPv8qs3s'),
    (NULL, '14785236', 'MASCULINO', 'GERMAN', 'SAAVEDRA', 'GERMAN@SAAVEDRA', '2664721818', '$argon2id$v=19$m=1024,t=1,p=1$PJAfKiaaPTkFkJqJZPIeGA$7gmdg3/z8TJnjE7qmWsT9ngC1JnxmXEbwptpPv8qs3s'),
    (NULL, '36985214', 'FEMENINO', 'ZULMA', 'LOBATO', 'ZULMA@LOBATO', '2664721818', '$argon2id$v=19$m=1024,t=1,p=1$PJAfKiaaPTkFkJqJZPIeGA$7gmdg3/z8TJnjE7qmWsT9ngC1JnxmXEbwptpPv8qs3s'),
    (NULL, '25812398', 'MASCULINO', 'RAMON', 'ROCA', 'RAMON@ROCA', '2664721818', '$argon2id$v=19$m=1024,t=1,p=1$PJAfKiaaPTkFkJqJZPIeGA$7gmdg3/z8TJnjE7qmWsT9ngC1JnxmXEbwptpPv8qs3s'),
    (NULL, '35715937', 'MASCULINO', 'JOSE', 'PEREZ', 'JOSE@PEREZ', '2664721818', '$argon2id$v=19$m=1024,t=1,p=1$PJAfKiaaPTkFkJqJZPIeGA$7gmdg3/z8TJnjE7qmWsT9ngC1JnxmXEbwptpPv8qs3s'),
    (NULL, '15915936', 'MASCULINO', 'JORGE', 'SAUCEDO', 'JORGE@SAUCEDO', '2664721818', '$argon2id$v=19$m=1024,t=1,p=1$PJAfKiaaPTkFkJqJZPIeGA$7gmdg3/z8TJnjE7qmWsT9ngC1JnxmXEbwptpPv8qs3s'),
    (NULL, '29736984', 'MASCULINO', 'EFRAIN', 'MEDINA', 'EFRAIN@MEDINA', '2664721818', '$argon2id$v=19$m=1024,t=1,p=1$PJAfKiaaPTkFkJqJZPIeGA$7gmdg3/z8TJnjE7qmWsT9ngC1JnxmXEbwptpPv8qs3s'),
    (NULL, '23745699', 'MASCULINO', 'ANGEL', 'CATTA', 'ANGEL@CATTA', '2664721818', '$argon2id$v=19$m=1024,t=1,p=1$PJAfKiaaPTkFkJqJZPIeGA$7gmdg3/z8TJnjE7qmWsT9ngC1JnxmXEbwptpPv8qs3s'),
    (NULL, '32749255', 'FEMENINO', 'EUGENIA', 'SUAREZ', 'EUGENIA@SUAREZ', '2664721818', '$argon2id$v=19$m=1024,t=1,p=1$PJAfKiaaPTkFkJqJZPIeGA$7gmdg3/z8TJnjE7qmWsT9ngC1JnxmXEbwptpPv8qs3s');


INSERT INTO `divisas` (`divisatipo`, `divisavalor`) 
    VALUES 
        ('PESO', '1'),
        ('BTC', '100'),
        ('ETH', '20'),
        ('BAT', '2'),
        ('ADA', '300');


INSERT INTO `billeteras` (`billeteraid`, `userid`, `saldototal`) 
    VALUES 
        (NULL, '1', '4560'),
        (NULL, '2', '2610'),
        (NULL, '3', '4330'),
        (NULL, '4', '2315'),
        (NULL, '5', '2210'),
        (NULL, '6', '2390.001'),
        (NULL, '7', '3342.001'),
        (NULL, '8', '2560'),
        (NULL, '9', '2860'),
        (NULL, '10', '2311'),
        (NULL, '1', '4290'),
        (NULL, '2', '2213'),
        (NULL, '2', '2430'),
        (NULL, '3', '2210'),
        (NULL, '4', '2884.999'),
        (NULL, '5', '2443'),
        (NULL, '6', '1399');


INSERT INTO `saldosdivisas` (`saldosid`,`billeteraid`, `divisatipo`, `divisasaldo`) 
    VALUES 
        (NULL, '1', 'PESO', '100'),(NULL, '1', 'BTC', '5'),(NULL, '1', 'ETH', '122.5'),(NULL, '1', 'BAT', '5'),(NULL, '1', 'ADA', '5'),
        (NULL, '2', 'PESO', '500'),(NULL, '2', 'BTC', '5'),(NULL, '2', 'ETH', '5'),(NULL, '2', 'BAT', '5'),(NULL, '2', 'ADA', '5'),
        (NULL, '3', 'PESO', '720'),(NULL, '3', 'BTC', '5'),(NULL, '3', 'ETH', '5'),(NULL, '3', 'BAT', '5'),(NULL, '3', 'ADA', '10'),
        (NULL, '4', 'PESO', '5'),(NULL, '4', 'BTC', '5'),(NULL, '4', 'ETH', '5'),(NULL, '4', 'BAT', '105'),(NULL, '4', 'ADA', '5'),
        (NULL, '5', 'PESO', '100'),(NULL, '5', 'BTC', '5'),(NULL, '5', 'ETH', '5'),(NULL, '5', 'BAT', '5'),(NULL, '5', 'ADA', '5'),
        (NULL, '6', 'PESO', '50'),(NULL, '6', 'BTC', '5'),(NULL, '6', 'ETH', '5'),(NULL, '6', 'BAT', '5'),(NULL, '6', 'ADA', '5.76667'),
        (NULL, '7', 'PESO', '532'),(NULL, '7', 'BTC', '5'),(NULL, '7', 'ETH', '0'),(NULL, '7', 'BAT', '5'),(NULL, '7', 'ADA', '7.66667'),
        (NULL, '8', 'PESO', '200'),(NULL, '8', 'BTC', '5'),(NULL, '8', 'ETH', '5'),(NULL, '8', 'BAT', '130'),(NULL, '8', 'ADA', '5'),
        (NULL, '9', 'PESO', '750'),(NULL, '9', 'BTC', '5'),(NULL, '9', 'ETH', '5'),(NULL, '9', 'BAT', '5'),(NULL, '9', 'ADA', '5'),
        (NULL, '10', 'PESO', '1'),(NULL, '10', 'BTC', '7'),(NULL, '10', 'ETH', '5'),(NULL, '10', 'BAT', '5'),(NULL, '10', 'ADA', '5'),
        (NULL, '11', 'PESO', '180'),(NULL, '11', 'BTC', '25'),(NULL, '11', 'ETH', '5'),(NULL, '11', 'BAT', '5'),(NULL, '11', 'ADA', '5'),
        (NULL, '12', 'PESO', '103'),(NULL, '12', 'BTC', '5'),(NULL, '12', 'ETH', '5'),(NULL, '12', 'BAT', '5'),(NULL, '12', 'ADA', '5'),
        (NULL, '13', 'PESO', '720'),(NULL, '13', 'BTC', '1'),(NULL, '13', 'ETH', '5'),(NULL, '13', 'BAT', '5'),(NULL, '13', 'ADA', '5'),
        (NULL, '14', 'PESO', '100'),(NULL, '14', 'BTC', '5'),(NULL, '14', 'ETH', '5'),(NULL, '14', 'BAT', '5'),(NULL, '14', 'ADA', '5'),
        (NULL, '15', 'PESO', '75'),(NULL, '15', 'BTC', '5'),(NULL, '15', 'ETH', '5'),(NULL, '15', 'BAT', '5'),(NULL, '15', 'ADA', '7.33333'),
        (NULL, '16', 'PESO', '333'),(NULL, '16', 'BTC', '5'),(NULL, '16', 'ETH', '5'),(NULL, '16', 'BAT', '5'),(NULL, '16', 'ADA', '5'),
        (NULL, '17', 'PESO', '9'),(NULL, '17', 'BTC', '12.8'),(NULL, '17', 'ETH', '5'),(NULL, '17', 'BAT', '5'),(NULL, '17', 'ADA', '0');

INSERT INTO `intercambios`(`operacionid`, `operacionfechahora`, `billeteraidorigen`, `billeteraiddestino`, `divisatipoorigen`, `divisatipodestino`, `monto`) 
    VALUES 
        (NULL, '2021/10/19 09:28:41', '7','11','ETH','BTC','5'),
        (NULL, '2021/10/19 09:34:34', '17','3','ADA','ADA','5'),
        (NULL, '2021/10/19 09:45:28', '9','8','PESO','BAT','250'),
        (NULL, '2021/10/19 09:48:33', '3','17','PESO','BTC','780'),
        (NULL, '2021/10/19 09:50:11', '13','10','BTC','BTC','2'),
        (NULL, '2021/10/19 09:50:30', '13','11','BTC','BTC','2');
        
INSERT INTO `depositos`(`operacionid`, `operacionfechahora`, `billeteraiddestino`, `divisatipodestino`, `monto`) 
    VALUES 
        (NULL, '2021/10/19 09:55:52','11','BTC','200'),
        (NULL, '2021/10/19 09:57:11','11','BTC','1500'),
        (NULL, '2021/10/19 09:57:36','7','ADA','800'),
        (NULL, '2021/10/19 09:57:57','1','ETH','2350'),
        (NULL, '2021/10/19 09:58:23','15','ADA','700'),
        (NULL, '2021/10/19 10:00:29','2','PESO','500'),
        (NULL, '2021/10/19 10:01:40','6','ADA','230'),
        (NULL, '2021/10/19 10:02:09','4','BAT','200');
