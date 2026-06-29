CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (LogID, Message)
    VALUES (AuditLog_seq.NEXTVAL, 'Transaction inserted: ' || :NEW.TransactionID);
END;
/

CREATE TABLE AuditLog (
    LogID NUMBER PRIMARY KEY,
    Message VARCHAR2(200)
);

CREATE SEQUENCE AuditLog_seq START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    l_balance NUMBER;
BEGIN
    SELECT Balance INTO l_balance FROM Accounts WHERE AccountID = :NEW.AccountID;

    IF :NEW.TransactionType = 'Withdrawal' AND :NEW.Amount > l_balance THEN
        RAISE_APPLICATION_ERROR(-20004, 'Withdrawal exceeds balance.');
    ELSIF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20005, 'Deposit amount must be positive.');
    END IF;
END;
/
