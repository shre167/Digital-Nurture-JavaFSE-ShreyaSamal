SET SERVEROUTPUT ON;

DECLARE
    l_customer_id Customers.CustomerID%TYPE;
    l_age NUMBER;
    l_balance NUMBER;
    l_due_date DATE;
BEGIN
    FOR c IN (SELECT CustomerID, DOB, Balance FROM Customers) LOOP
        l_age := TRUNC(MONTHS_BETWEEN(SYSDATE, c.DOB) / 12);

        IF l_age > 60 THEN
            DBMS_OUTPUT.PUT_LINE('Customer ' || c.CustomerID || ' gets 1% loan discount.');
        END IF;

        IF c.Balance > 10000 THEN
            UPDATE Customers
            SET Balance = Balance,
                LastModified = SYSDATE
            WHERE CustomerID = c.CustomerID;
        END IF;
    END LOOP;

    FOR loan IN (SELECT l.LoanID, c.CustomerID, c.Name, l.EndDate
                 FROM Loans l
                 JOIN Customers c ON c.CustomerID = l.CustomerID
                 WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder for ' || loan.Name || ': loan ' || loan.LoanID || ' is due on ' || loan.EndDate);
    END LOOP;
END;
/