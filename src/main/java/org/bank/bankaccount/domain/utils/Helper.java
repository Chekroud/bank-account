package org.bank.bankaccount.domain.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.bank.bankaccount.domain.enumeration.OperationTypeEnum;

import java.util.function.DoubleFunction;
import java.util.function.IntSupplier;

/**
 * Helpers
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Helper {

    public static final DoubleFunction<String> evaluateOperationType = operation ->
            operation > 0 ? OperationTypeEnum.DEPOSIT.name() : OperationTypeEnum.WITHDRAWAL.name();


    public static final IntSupplier generateAccountNumber = () -> RandomUtils.nextInt(1, Integer.MAX_VALUE);

}
