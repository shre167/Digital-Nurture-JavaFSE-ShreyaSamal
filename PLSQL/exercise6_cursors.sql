SET SERVEROUTPUT ON;

DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT t.TransactionID, t.AccountID, t.TransactionDate, t.Amount, t.TransactionType
        FROM Transactions t
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE);
BEGIN
    FOR rec IN GenerateMonthlyStatements LOOP
        DBMS_OUTPUT.PUT_LINE('Statement for Account ' || rec.AccountID || ': ' || rec.TransactionType || ' ' || rec.Amount);
    END LOOP;
END;
/

DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID FROM Accounts;
BEGIN
    FOR acc IN ApplyAnnualFee LOOP
        UPDATE Accounts
        SET Balance = Balance - 10
        WHERE AccountID = acc.AccountID;
    END LOOP;
    COMMIT;
END;
/

DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate FROM Loans;
BEGIN
    FOR loan IN UpdateLoanInterestRates LOOP
        UPDATE Loans
        SET InterestRate = InterestRate + 0.5
        WHERE LoanID = loan.LoanID;
    END LOOP;
    COMMIT;
END;
/
