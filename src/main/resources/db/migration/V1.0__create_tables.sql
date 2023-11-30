CREATE TABLE purchase_transaction
(
    id          UUID                     NOT NULL PRIMARY KEY,
    description VARCHAR(50)              NOT NULL,
    date        TIMESTAMP WITH TIME ZONE NOT NULL,
    amount      INTEGER                  NOT NULL,
    CONSTRAINT amount_positive CHECK (amount > 0),
    CONSTRAINT description_not_empty CHECK (length(trim(description)) > 0),
    CONSTRAINT date_not_future CHECK (date < NOW())
);
