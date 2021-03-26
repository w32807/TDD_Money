package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import currency.Bank;
import currency.Expression;
import currency.Money;
import currency.Sum;

public class CurrencyTest {

    @Test
    public void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10) ,five.times(2));
        assertEquals(Money.dollar(15) ,five.times(3));
    }
    
    @Test
    public void testFrancMultiplication() {
        Money five = Money.franc(5);
        assertEquals(Money.franc(10) ,five.times(2));
        assertEquals(Money.franc(15) ,five.times(3));
    }
    
    @Test
    public void testEquality() {
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        assertFalse(Money.dollar(5).equals(Money.franc(5)));
    }
    
    @Test
    public void testSimpleAddition() {
        Expression sum = (Expression) Money.dollar(5).plus(Money.dollar(5));
        //assertEquals(Money.dollar(10) ,sum);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10) ,reduced);
    }
    
    @Test
    public void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five ,sum.augend);
        assertEquals(five ,sum.addend);
    }
    
    @Test
    public void testeReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7) ,result);
        
    }
    
    @Test
    public void testeReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1) ,result);
    }
    
    @Test
    public void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(Money.dollar(1), result);
    }
    
    @Test
    public void testIdentityRate() {
        assertEquals(1,  new Bank().rate("USD", "USD"));
    }
}

