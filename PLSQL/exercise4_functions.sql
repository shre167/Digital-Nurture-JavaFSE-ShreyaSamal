CREATE OR REPLACE FUNCTION CalculateAge(p_dob DATE) RETURN NUMBER AS
    l_age NUMBER;
BEGIN
    l_age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN l_age;
END;
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount NUMBER,
    p_interest_rate NUMBER,
    p_years NUMBER
) RETURN NUMBER AS
    l_monthly_rate NUMBER;
    l_months NUMBER;
BEGIN
    l_months := p_years * 12;
    l_monthly_rate := p_interest_rate / 100 / 12;
    RETURN p_loan_amount * l_monthly_rate / (1 - POWER(1 + l_monthly_rate, -l_months));
END;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id NUMBER,
    p_amount NUMBER
) RETURN BOOLEAN AS
    l_balance NUMBER;
BEGIN
    SELECT Balance INTO l_balance FROM Accounts WHERE AccountID = p_account_id;
    RETURN l_balance >= p_amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
END;
/
