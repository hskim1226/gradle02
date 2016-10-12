truncate table APPL;
truncate table APPL_ACAD;
truncate table APPL_CHG;
truncate table APPL_CHG_ITEM;
truncate table APPL_CHK;
truncate table APPL_DOC;
truncate table APPL_DOC_RSLT;
truncate table APPL_ERROR;
truncate table APPL_ETC;
truncate table APPL_EXPR;
truncate table APPL_FORN;
truncate table APPL_GENE;
truncate table APPL_LANG;
truncate table APPL_PAY_CS;
truncate table APPL_PAY_TR;
truncate table APPL_REC;

truncate table XPAY_PAY_RSLT;
truncate table XPAY_PAY_REQ;
truncate table XPAY_CERT_RSLT;
truncate table XPAY_CERT_REQ;
truncate table XPAY_CANC_RSLT;
truncate table XPAY_CANC_REQ;

-- truncate table USER;
-- truncate table authorities;
delete * from USER where USER_ID != 'Apex1234';
delete * from authorities where username != 'Apex1234';

truncate table STUD_NO;

truncate table QNA;

update APPL_ID_SEQ SET APPL_ID_SEQ = 0;