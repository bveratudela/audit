USE audit;

INSERT INTO TABLE audit_history SELECT * FROM hue;
INSERT INTO TABLE audit_history SELECT * FROM navhdfs;
INSERT INTO TABLE audit_history SELECT * FROM navhive;
INSERT INTO TABLE audit_history SELECT * FROM navhue;
INSERT INTO TABLE audit_history SELECT * FROM navsentry;
INSERT INTO TABLE audit_history SELECT * FROM navimpala;
INSERT INTO TABLE audit_history SELECT * FROM navms;
